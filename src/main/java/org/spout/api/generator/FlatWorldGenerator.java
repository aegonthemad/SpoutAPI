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
package org.spout.api.generator;

import org.spout.api.generator.biome.BiomeManager;
import org.spout.api.generator.biome.EmptyBiomeManager;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.material.BlockMaterial;
import org.spout.api.util.cuboid.CuboidShortBuffer;

public class FlatWorldGenerator implements WorldGenerator {
	public BiomeManager generate(CuboidShortBuffer blockData, int chunkX, int chunkY, int chunkZ) {
		if (chunkY < 0) {
			blockData.flood(BlockMaterial.UNBREAKABLE.getId());
		}
		return new EmptyBiomeManager(chunkX, chunkY, chunkZ);
	}

	public Populator[] getPopulators() {
		return new Populator[0];
	}
	
	public String getName() {
		return "FlatWorld";
	}

	@Override
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ) {
		 return new int[Chunk.BLOCKS.SIZE][Chunk.BLOCKS.SIZE];
	}
}
