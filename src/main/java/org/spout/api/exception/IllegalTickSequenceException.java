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
package org.spout.api.exception;

import org.spout.api.scheduler.TickStage;

public class IllegalTickSequenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalTickSequenceException(int allowedStages, int restrictedStages, Thread t, int actualStage) {
		super(getMessage(allowedStages, restrictedStages, t, actualStage));
	}

	public IllegalTickSequenceException(int allowedStages, int actualStage) {
		super("Method called during (" + TickStage.getAllStages(actualStage) + ") when only (" + TickStage.getAllStages(allowedStages) + ") were allowed");
	}
	
	private static String getMessage(int allowedStages, int restrictedStages, Thread t, int actualStage) {
		if (Thread.currentThread() != t) {
			return "Method called by non-owning thread (" + Thread.currentThread() + ") during (" + TickStage.getAllStages(actualStage) + ") when only calls by (" + t + ") during (" + TickStage.getAllStages(allowedStages) + ") were allowed";
		} else {
			return "Method called during (" + TickStage.getAllStages(actualStage) + ") when only (" + TickStage.getAllStages(restrictedStages) + ") were allowed for owning thread " + t;
		}
	}

}
