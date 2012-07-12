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
package org.spout.api.protocol.builtin.handler;

import org.spout.api.player.Player;
import org.spout.api.player.PlayerInputState;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.api.protocol.builtin.message.PlayerInputMessage;

public class PlayerInputMessageHandler extends MessageHandler<PlayerInputMessage> {
	@Override
	public void handleServer(Session session, Player player, PlayerInputMessage message) {
		PlayerInputState input = player.input();
		if (message.isFwd()) {
			input.setForward(1);
		} else if (message.isBack()) {
			input.setForward(-1);
		} else {
			input.setForward(0);
		}

		if (message.isLeft()) {
			input.setHorizantal(1);
		} else if (message.isRight()) {
			input.setHorizantal(-1);
		} else {
			input.setHorizantal(0);
		}

		input.setLookX(message.getMouseDx());
		input.setLookY(message.getMouseDy());
	}
}
