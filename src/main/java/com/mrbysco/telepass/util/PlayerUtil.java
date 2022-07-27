package com.mrbysco.telepass.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PlayerUtil {
	@Nullable
	public static Player getPlayerEntityByName(Level worldIn, String name) {
		return worldIn.players().stream().filter(player -> player.getName().getString().equals(name)).findFirst().orElse(null);
	}
}
