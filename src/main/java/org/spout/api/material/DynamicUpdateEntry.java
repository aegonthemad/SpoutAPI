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
package org.spout.api.material;

/**
 * Represents an entry in the dynamic update queue
 */
public interface DynamicUpdateEntry {
	
	/**
	 * 
	 * @return x value where the update is occurring.
	 */
	public int getX();

	/**
	 * 
	 * @return y value where the update is occurring.
	 */
	public int getY();

	/**
	 * 
	 * @return z value where the update is occurring.
	 */
	public int getZ();

	/**
	 * The time when the next update will occur, represented as a long.
	 * 
	 * @return time of the next update
	 */
	public long getNextUpdate();

	/**
	 * The data value given when the update was queued
	 * @return
	 */
	public int getData();

}
