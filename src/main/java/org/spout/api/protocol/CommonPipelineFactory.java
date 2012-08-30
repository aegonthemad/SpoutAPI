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

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.spout.api.Engine;
import org.spout.api.Server;
import org.spout.api.protocol.dynamicid.DynamicMessageDecoder;
import org.spout.api.protocol.dynamicid.DynamicMessageEncoder;

/**
 * A common {@link ChannelPipelineFactory}
 */
public final class CommonPipelineFactory implements ChannelPipelineFactory {

	/**
	 * The server.
	 */
	private Engine engine;

	/**
	 * Creates a new Minecraft pipeline factory.
	 *
	 * @param engine The engine
	 * @param upstream true for connection to the server
	 */
	public CommonPipelineFactory(Engine engine) {
		this.engine = engine;
	}

	public ChannelPipeline getPipeline() throws Exception {
		CommonEncoder encoder = new CommonEncoder();
		CommonDecoder decoder = new CommonDecoder();
		CommonHandler handler = new CommonHandler(engine, encoder, decoder);
		DynamicMessageDecoder dynamicDecoder = new DynamicMessageDecoder();
		DynamicMessageEncoder dynamicEncoder = new DynamicMessageEncoder();
		return Channels.pipeline(decoder, encoder, dynamicDecoder, dynamicEncoder, handler);
	}
}
