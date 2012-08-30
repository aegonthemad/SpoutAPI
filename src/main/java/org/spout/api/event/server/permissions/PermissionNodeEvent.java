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
package org.spout.api.event.server.permissions;

import org.spout.api.event.HandlerList;
import org.spout.api.event.Result;
import org.spout.api.event.server.NodeBasedEvent;
import org.spout.api.geo.World;
import org.spout.api.permissions.PermissionsSubject;

/**
 * This event is called when {@link PermissionSubject#hasPermission()} is called.
 */
public class PermissionNodeEvent extends NodeBasedEvent {
	private static final HandlerList handlers = new HandlerList();
	private final World world;
	private final PermissionsSubject subject;
	private Result result = Result.DEFAULT;

	public PermissionNodeEvent(World world, PermissionsSubject subject, String node) {
		super(node);
		this.world = world;
		this.subject = subject;
	}

	/**
	 * The subject being checked for permission.
	 * 
	 * @return subject
	 */
	public PermissionsSubject getSubject() {
		return subject;
	}

	/**
	 * The result of the check.
	 * 
	 * @return result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * Sets the result of the check to the given result.
	 * 
	 * @param result
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * The world this permission check is being done on, or null if global.
	 * 
	 * @return the world, or null if global.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * The name of the world this permission check is being done on, or null if global.
	 * 
	 * @return name of the world, or null if global.
	 */
	public String getWorldName() {
		return world == null ? null : world.getName();
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
