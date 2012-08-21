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

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.spout.api.Engine;

/**
 * A {@link SimpleChannelUpstreamHandler} which processes incoming network events.
 *
 */
public class CommonHandler extends SimpleChannelUpstreamHandler {

	/**
	 * The associated session
	 */
	private AtomicReference<Session> session = new AtomicReference<Session>(null);

	private final CommonDecoder decoder;
	private final CommonEncoder encoder;

	private final Engine engine;

	/**
	 * Creates a new network event handler.
	 */
	public CommonHandler(Engine engine, CommonEncoder encoder, CommonDecoder decoder) {
		this.engine = engine;
		this.encoder = encoder;
		this.decoder = decoder;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Channel c = e.getChannel();

		// ctx.getPipeline().addBefore("2", "messagePrinter", new MessagePrintingHandler());

		try {
			engine.getChannelGroup().add(c);
			Session session = engine.newSession(c);
			engine.getSessionRegistry().add(session);
			setSession(session);
			ctx.setAttachment(session);

			engine.getLogger().info("Downstream channel connected: " + c + ".");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Exception thrown when connecting", ex);
		}
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Channel c = e.getChannel();
		engine.getChannelGroup().remove(c);

		Session session = (Session) ctx.getAttachment();
		engine.getSessionRegistry().remove(session);
		session.dispose();

		engine.getLogger().info("Channel disconnected: " + c + ".");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		Session session = (Session) ctx.getAttachment();
		session.messageReceived((Message) e.getMessage());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		Channel c = e.getChannel();
		if (c.isOpen()) {
			engine.getChannelGroup().remove(c);

			Session session = (Session) ctx.getAttachment();
			if (session != null) {
				engine.getSessionRegistry().remove(session);
				session.dispose();
			}

			engine.getLogger().log(Level.WARNING, "Exception caught, closing channel: " + c + "...", e.getCause());
			c.close();
		}
	}

	public void setSession(Session session) {
		if (!this.session.compareAndSet(null, session)) {
			throw new IllegalStateException("Session may not be set more than once");
		}
		decoder.setProtocol(session.getProtocol());
		encoder.setProtocol(session.getProtocol());
	}
}
