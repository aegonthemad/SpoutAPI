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
package org.spout.api.event.player;

import java.util.List;

import org.spout.api.chat.ChatArguments;
import org.spout.api.event.Cancellable;
import org.spout.api.entity.Player;

/**
 * Called when a player gets kicked from the server
 */
public class PlayerKickEvent extends PlayerLeaveEvent implements Cancellable {
	private ChatArguments kickReason;

	public PlayerKickEvent(Player p, Object[] leaveMessage, Object... reason) {
		super(p, leaveMessage);
		kickReason = new ChatArguments(reason);
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	@Override
	public boolean isKick() {
		return true;
	}

	/**
	 * Gets the reason why the player is getting kicked
	 *
	 * @return string kick reason
	 */
	public ChatArguments getKickReason() {
		return kickReason;
	}

	/**
	 * Sets the reason why the player is getting kicked
	 *
	 * @param kickReason kick reason
	 */
	public void setKickReason(Object... kickReason) {
		if (kickReason.length == 1 && kickReason[0] instanceof List<?>) {
			kickReason = ((List<?>) kickReason[0]).toArray();
		}
		this.kickReason = new ChatArguments(kickReason);
	}
}
