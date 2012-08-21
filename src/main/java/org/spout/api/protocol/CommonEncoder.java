/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.protocol;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.spout.api.Client;
import org.spout.api.Spout;

/**
 * A {@link OneToOneEncoder} which encodes Minecraft {@link Message}s into
 * {@link ChannelBuffer}s.
 */
public class CommonEncoder extends PostprocessEncoder {
	private volatile Protocol protocol = null;

	public CommonEncoder() {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel c, Object msg) throws Exception {
		if (msg instanceof Message) {
			if (protocol == null) {
				if (Spout.getEngine() instanceof Client) {
					protocol = ((Client) Spout.getEngine()).getAddress().getProtocol();
				} else {
					protocol = Spout.getEngine().getProtocol(c.getLocalAddress());
				}
			}
			Message message = (Message) msg;

			Class<? extends Message> clazz = message.getClass();
			MessageCodec<Message> codec;

			codec = (MessageCodec<Message>) protocol.getCodecLookupService().find(clazz);
			if (codec == null) {
				throw new IOException("Unknown message type: " + clazz + ".");
			}

			ChannelBuffer messageBuf = codec.encode(message);
			ChannelBuffer headerBuf = protocol.writeHeader(codec, messageBuf);
			return ChannelBuffers.wrappedBuffer(headerBuf, messageBuf);
		}
		return msg;
	}

	void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
}
