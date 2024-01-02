package com.mrbysco.telepass.platform.services;

import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.TeleCompass;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public interface IPlatformHelper {
	/**
	 * Build the creative tab for the mod.
	 *
	 * @return the creative tab
	 */
	CreativeModeTab buildCreativeTab();

	/**
	 * Build the creative tab for the mod.
	 *
	 * @return the creative tab
	 */
	TeleCompass createCompass(Item.Properties properties, CompassMaterial material);

	/**
	 * Get the configured durability for the gold telepass.
	 *
	 * @return durability
	 */
	int goldDurability();

	/**
	 * Get the configured durability for the diamond telepass.
	 *
	 * @return durability
	 */
	int diamondDurability();

	/**
	 * Check if the player is not a fake player.
	 *
	 * @param player The player to check
	 * @return true if the player is not a fake player
	 */
	boolean notFakePlayer(Player player);
}
