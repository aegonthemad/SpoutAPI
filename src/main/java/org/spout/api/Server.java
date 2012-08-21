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
package org.spout.api;

import java.util.Collection;
import java.util.List;

import org.spout.api.protocol.PortBinding;

/**
 * Represents the server-specific implementation of Minecraft.
 */
public interface Server extends Engine {

	/**
	 * Returns true if this server is using a whitelist.
	 *
	 * @return whitelist enabled
	 */
	public boolean isWhitelist();

	/**
	 * Sets the whitelist value of this server.
	 *
	 * @param whitelist value to set
	 */
	public void setWhitelist(boolean whitelist);

	/**
	 * Reads the whitelist file from the disk, updating the players that are
	 * allowed to join where nessecary.
	 */
	public void updateWhitelist();

	/**
	 * Gets an array of all of the player names that are on the whitelist.
	 *
	 * @return whitelisted player names
	 */
	public String[] getWhitelistedPlayers();

	/**
	 * Adds the given player to the list of whitelisted players
	 *
	 * @param player to whitelist
	 */
	public void whitelist(String player);

	/**
	 * Removes the given player from the list of whitelisted players
	 *
	 * @param player to remove from whitelist
	 */
	public void removeFromWhitelist(String player);

	/**
	 * True if this server does not check if players are flying or not.
	 *
	 * If disabled, the server will attempt to verify that players are not
	 * flying, and kick any players that are flying.
	 *
	 * @return allow flight
	 */
	public boolean allowFlight();

	/**
	 * Returns all IP addresses being listened to.
	 * The returned collection is unmodifiable.
	 *
	 * @return address
	 */
	public List<PortBinding> getBoundAddresses();

	/**
	 * Binds the server to a certain address
	 *
	 * @param binding The address and protocol to bind to.
	 * @return true if successful
	 */
	public boolean bind(PortBinding binding);

	/**
	 * Bans the specified player
	 *
	 * @param player Player to ban
	 */
	public void banPlayer(String player);

	/**
	 * Unbans the specified player
	 *
	 * @param player Player to ban
	 */
	public void unbanPlayer(String player);

	/**
	 * Bans the specified IP
	 *
	 * @param address Player to ban
	 */
	public void banIP(String address);

	/**
	 * Unbans the specified IP
	 *
	 * @param address Player to ban
	 */
	public void unbanIP(String address);

	/**
	 * Gets a collection of all banned IP's, in string format.
	 *
	 * @return banned IP addresses
	 */
	public Collection<String> getIPBans();

	/**
	 * Returns a collection of all banned players
	 *
	 * @return banned players
	 */
	public Collection<String> getBannedPlayers();

	/**
	 * Returns true if the player or address is banned.
	 *
	 * @param player Player name to check
	 * @param address Address to check
	 * @return If either is banned
	 */
	public boolean isBanned(String player, String address);

	/**
	 * Returns true if the address is banned.
	 *
	 * @param address Address to check
	 * @return If the address is banned
	 */
	public boolean isIPBanned(String address);

	/**
	 * Returns true if the player is banned.
	 *
	 * @param player Player name to check
	 * @return If the player is banned
	 */
	public boolean isPlayerBanned(String player);

	/**
	 * Gets the ban message for the player
	 *
	 * @return the ban message
	 */
	public String getBanMessage(String player);

	/**
	 * Gets the ban message for the IP
	 *
	 * @return the ban message
	 */
	public String getIPBanMessage(String address);

	/**
	 * Maps a port for both TCP and UDP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @return the session
	 */
	public void mapUPnPPort(int port);

	/**
	 * Maps a port for both TCP and UDP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @param description the description for this mapping
	 * @return the session
	 */
	public void mapUPnPPort(int port, String description);

	/**
	 * Maps a port for TCP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @return the session
	 */
	public void mapTCPPort(int port);

	/**
	 * Maps a port for TCP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @param description the description for this mapping
	 * @return the session
	 */
	public void mapTCPPort(int port, String description);

	/**
	 * Maps a port for TCP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @return the session
	 */
	public void mapUDPPort(int port);

	/**
	 * Maps a port for TCP communication for Universal Plug and Play enabled InternetGatewayDevices
	 *
	 * @param port the port to be mapped
	 * @param description the description for this mapping
	 * @return the session
	 */
	public void mapUDPPort(int port, String description);
}
