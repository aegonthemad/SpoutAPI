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
package org.spout.api.protocol.builtin;

import org.spout.api.Client;
import org.spout.api.Spout;
import org.spout.api.entity.component.controller.type.ControllerType;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.Protocol;
import org.spout.api.protocol.builtin.message.CommandMessage;
import org.spout.api.protocol.builtin.message.LoginMessage;

/**
 * The protocol used in SpoutClient
 */
public class SpoutProtocol extends Protocol {
	public static final int ENTITY_PROTOCOL_ID = ControllerType.getProtocolId(SpoutProtocol.class.getName());
	public static final DefaultedKey<Integer> PLAYER_ENTITY_ID = new DefaultedKeyImpl<Integer>("playerEntityId", -1);
	public static final int PROTOCOL_VERSION = 0;

	public SpoutProtocol() {
		super("Spout", new SpoutCodecLookupService(), new SpoutHandlerLookupService());
	}

	public Message getKickMessage(Object... message) {
		return new CommandMessage("kick", message);
	}

	public Message getChatMessage(Object... message) {
		if (Spout.getEngine() instanceof Client) {
			return new CommandMessage("say", message);
		} else {
			return new CommandMessage("broadcast", message);
		}
	}

	public Message getIntroductionMessage(String playerName) {
		return new LoginMessage(playerName, PROTOCOL_VERSION);
	}
}
