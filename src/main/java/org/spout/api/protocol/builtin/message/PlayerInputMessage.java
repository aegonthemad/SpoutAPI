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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.spout.api.protocol.Message;
import org.spout.api.util.SpoutToStringStyle;

public class PlayerInputMessage extends Message {
	private final boolean fwd, back, left, right;
	private final boolean mouseWheelUp, mouseWheelDown;
	private final short mouseDx, mouseDy;

	public PlayerInputMessage(boolean fwd, boolean back, boolean left, boolean right, boolean mouseWheelUp, boolean mouseWheelDown, short mouseDx, short mouseDy) {
		this.fwd = fwd;
		this.back = back;
		this.left = left;
		this.right = right;
		this.mouseWheelUp = mouseWheelUp;
		this.mouseWheelDown = mouseWheelDown;
		this.mouseDx = mouseDx;
		this.mouseDy = mouseDy;
	}

	public boolean isFwd() {
		return fwd;
	}

	public boolean isBack() {
		return back;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isMouseWheelUp() {
		return mouseWheelUp;
	}

	public boolean isMouseWheelDown() {
		return mouseWheelDown;
	}

	public short getMouseDx() {
		return mouseDx;
	}

	public short getMouseDy() {
		return mouseDy;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SpoutToStringStyle.INSTANCE)
				.append("fwd", fwd)
				.append("back", back)
				.append("left", left)
				.append("right", right)
				.append("mouseWheelUp", mouseWheelUp)
				.append("mouseWheelDown", mouseWheelDown)
				.append("mouseDx", mouseDx)
				.append("mouseDy", mouseDy)
				.toString();
	}
}
