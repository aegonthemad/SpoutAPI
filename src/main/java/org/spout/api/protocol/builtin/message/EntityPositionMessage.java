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
package org.spout.api.protocol.builtin.message;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.spout.api.Spout;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.api.protocol.Message;
import org.spout.api.util.SpoutToStringStyle;

public class EntityPositionMessage extends Message {
	private final int entityId;
	private final UUID worldUid;
	private final Vector3 pos;
	private final Quaternion rotation;
	private final Vector3 scale;

	public EntityPositionMessage(int entityId, Transform transform) {
		this.entityId = entityId;
		this.worldUid = transform.getPosition().getWorld().getUID();
		this.pos = transform.getPosition();
		this.rotation = transform.getRotation();
		this.scale = transform.getScale();
	}

	public EntityPositionMessage(int entityId, UUID worldUid, Vector3 pos, Quaternion rotation, Vector3 scale) {
		this.entityId = entityId;
		this.worldUid = worldUid;
		this.pos = pos;
		this.rotation = rotation;
		this.scale = scale;
	}

	public int getEntityId() {
		return entityId;
	}

	public UUID getWorldUid() {
		return worldUid;
	}

	public Vector3 getPosition() {
		return pos;
	}

	public Quaternion getRotation() {
		return rotation;
	}

	public Vector3 getScale() {
		return scale;
	}

	public Transform getTransform() {
		return new Transform(new Point(pos, Spout.getEngine().getWorld(worldUid)), rotation, scale);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SpoutToStringStyle.INSTANCE)
				.append("entityId", entityId)
				.append("worldUid", worldUid)
				.append("pos", pos)
				.append("rotation", rotation)
				.append("scale", scale)
				.toString();
	}
}
