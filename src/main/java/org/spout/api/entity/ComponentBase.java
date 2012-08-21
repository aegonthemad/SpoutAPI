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
package org.spout.api.entity;

public interface ComponentBase {
	
	/**
	 * Adds a new Component to the Entity.  If the entity already contains a component of that type, then a new component is not 
	 * constructed, and the one already attached is returned
	 * 
	 * @param component Type of component to add
	 * @return The component created, or the one already attached
	 */
	public EntityComponent addComponent(Class<? extends EntityComponent> component);
	
	/**
	 * Removes a component from the list
	 * @param component Type of component to remove
	 * @return True if a component is removed, false if not.  False is also returned if the component doesn't exist.
	 */
	public boolean removeComponent(Class<? extends EntityComponent> component);
	
	/**
	 * Returns an instance of the component attached to the object
	 * @param component the type of component to get
	 * @return The component instance, or NULL if it doesn't exist
	 */
	public EntityComponent getComponent(Class<? extends EntityComponent> component);
	
	/**
	 * Returns True if the type provided is attached or false if not.
	 * @param component
	 * @return
	 */
	public boolean hasComponent(Class<? extends EntityComponent> component);
	
}
