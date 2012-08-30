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
package org.spout.api.geo;

import org.spout.api.generator.biome.BiomeManager;
import org.spout.api.geo.cuboid.ChunkSnapshot;

/**
 * A variant of World that has client-specific functions
 */
public interface ClientWorld extends World {
	/**
	 * Adds a chunk to the world from a chunk snapshot
	 * @param c The snapshot
	 */
	public void addChunk(ChunkSnapshot c);

	/**
	 * Load a chunk from its raw data
	 *
	 * @param x The chunk's x coordinate
	 * @param y The chunk's y coordinate
	 * @param z The chunk's z coordinate
	 * @param blockIds The block ids for the chunk
	 * @param blockData The block data for the chunk
	 * @param blockLight The block lighting data
	 * @param skyLight The sky lighting data
	 * @param biomes The biome data
	 */
	public void addChunk(int x, int y, int z, short[] blockIds, short[] blockData, byte[] blockLight, byte[] skyLight, BiomeManager biomes);
}
