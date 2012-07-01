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
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.spout.api.Spout;
import org.spout.api.protocol.bootstrap.BootstrapProtocol;
import org.spout.api.protocol.replayable.ReplayableError;

/**
 * A {@link ReplayingDecoder} which decodes {@link ChannelBuffer}s into
 * Common {@link org.spout.api.protocol.Message}s.
 */
public class CommonDecoder extends PreprocessReplayingDecoder {
	private volatile CodecLookupService codecLookup = null;
	private int previousOpcode = -1;
	private volatile BootstrapProtocol bootstrapProtocol;
	private final CommonHandler handler;
	private final CommonEncoder encoder;

	public CommonDecoder(CommonHandler handler, CommonEncoder encoder) {
		super(512);
		this.encoder = encoder;
		this.handler = handler;
	}

	@Override
	protected Object decodeProcessed(ChannelHandlerContext ctx, Channel c, ChannelBuffer buf) throws Exception {
		if (codecLookup == null) {
			bootstrapProtocol = Spout.getEngine().getBootstrapProtocol(c.getLocalAddress());
			codecLookup = bootstrapProtocol.getCodecLookupService();
		}

		int opcode;
		
		try {
			opcode = buf.getUnsignedShort(buf.readerIndex());
		}
		catch (ReplayableError e) {
			opcode = buf.getUnsignedByte(buf.readerIndex()) << 8;
		}
		
		MessageCodec<?> codec = codecLookup.find(opcode);
		if (codec == null) {
			if (bootstrapProtocol != null) {
				Protocol protocol = bootstrapProtocol.getDefaultProtocol();
				if (protocol != null) {
					setProtocol(protocol);
					codec = codecLookup.find(opcode);
				}
			}
			if (codec == null) {
				throw new IOException("Unknown operation code: " + opcode + " (previous opcode: " + previousOpcode + ").");
			}
		}

		if (codec.isExpanded()) {
			buf.readShort();
		} else {
			buf.readByte();
		}

		previousOpcode = opcode;

		Message message = codec.decode(buf);

		if (bootstrapProtocol != null) {
			String id = bootstrapProtocol.detectProtocolDefinition(message);
			if (id != null) {
				Protocol protocol = Protocol.getProtocol(id);

				if (protocol != null) {
					setProtocol(protocol);
				} else {
					throw new IllegalStateException("No protocol associated with an id of " + id);
				}
			}
		}

		return message;
	}
	
	private void setProtocol(Protocol protocol) {
		codecLookup = protocol.getCodecLookupService();
		encoder.setProtocol(protocol);
		handler.setProtocol(protocol);
		bootstrapProtocol = null;
	}
}
