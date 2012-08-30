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
package org.spout.api.protocol.fake;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

public class FakeChannelHandlerContext implements ChannelHandlerContext {

	private final List<ChannelEvent> list;
	
	public FakeChannelHandlerContext() {
		this(null);
	}
	
	public FakeChannelHandlerContext(List<ChannelEvent> list) {
		this.list = list;
	}
	
	private final Channel fakeChannel = new FakeChannel();

	@Override
	public Channel getChannel() {
		return fakeChannel;
	}

	@Override
	public ChannelPipeline getPipeline() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public ChannelHandler getHandler() {
		return null;
	}

	@Override
	public boolean canHandleUpstream() {
		return false;
	}

	@Override
	public boolean canHandleDownstream() {
		return false;
	}

	@Override
	public void sendUpstream(ChannelEvent e) {
		list.add(e);
	}

	@Override
	public void sendDownstream(ChannelEvent e) {

	}

	@Override
	public Object getAttachment() {
		return null;
	}

	@Override
	public void setAttachment(Object attachment) {
	}

	private static class FakeChannel implements Channel {

		private final ChannelConfig fakeConfig = new FakeChannelConfig();

		@Override
		public int compareTo(Channel o) {
			return 0;
		}

		@Override
		public Integer getId() {
			return null;
		}

		@Override
		public ChannelFactory getFactory() {
			return null;
		}

		@Override
		public Channel getParent() {
			return null;
		}

		@Override
		public ChannelConfig getConfig() {
			return fakeConfig;
		}

		@Override
		public ChannelPipeline getPipeline() {
			return null;
		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public boolean isBound() {
			return false;
		}

		@Override
		public boolean isConnected() {
			return false;
		}

		@Override
		public SocketAddress getLocalAddress() {
			return null;
		}

		@Override
		public SocketAddress getRemoteAddress() {
			return null;
		}

		@Override
		public ChannelFuture write(Object message) {
			return null;
		}

		@Override
		public ChannelFuture write(Object message, SocketAddress remoteAddress) {
			return null;
		}

		@Override
		public ChannelFuture bind(SocketAddress localAddress) {
			return null;
		}

		@Override
		public ChannelFuture connect(SocketAddress remoteAddress) {
			return null;
		}

		@Override
		public ChannelFuture disconnect() {
			return null;
		}

		@Override
		public ChannelFuture unbind() {
			return null;
		}

		@Override
		public ChannelFuture close() {
			return null;
		}

		@Override
		public ChannelFuture getCloseFuture() {
			return null;
		}

		@Override
		public int getInterestOps() {
			return 0;
		}

		@Override
		public boolean isReadable() {
			return false;
		}

		@Override
		public boolean isWritable() {
			return false;
		}

		@Override
		public ChannelFuture setInterestOps(int interestOps) {
			return null;
		}

		@Override
		public ChannelFuture setReadable(boolean readable) {
			return null;
		}

	}

	private static class FakeChannelConfig implements ChannelConfig {

		private final ChannelBufferFactory fakeBufferFactory = new FakeChannelBufferFactory();

		@Override
		public void setOptions(Map<String, Object> options) {
		}

		@Override
		public boolean setOption(String name, Object value) {
			return false;
		}

		@Override
		public ChannelBufferFactory getBufferFactory() {
			return fakeBufferFactory;
		}

		@Override
		public void setBufferFactory(ChannelBufferFactory bufferFactory) {

		}

		@Override
		public ChannelPipelineFactory getPipelineFactory() {
			return null;
		}

		@Override
		public void setPipelineFactory(ChannelPipelineFactory pipelineFactory) {

		}

		@Override
		public int getConnectTimeoutMillis() {
			return 0;
		}

		@Override
		public void setConnectTimeoutMillis(int connectTimeoutMillis) {

		}

	}

	private static class FakeChannelBufferFactory implements ChannelBufferFactory {

		@Override
		public ChannelBuffer getBuffer(int capacity) {
			return ChannelBuffers.buffer(capacity);
		}

		@Override
		public ChannelBuffer getBuffer(ByteOrder endianness, int capacity) {
			return null;
		}

		@Override
		public ChannelBuffer getBuffer(byte[] array, int offset, int length) {
			return null;
		}

		@Override
		public ChannelBuffer getBuffer(ByteOrder endianness, byte[] array, int offset, int length) {
			return null;
		}

		@Override
		public ChannelBuffer getBuffer(ByteBuffer nioBuffer) {
			return null;
		}

		@Override
		public ByteOrder getDefaultOrder() {
			return null;
		}

	}
}

