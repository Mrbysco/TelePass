package com.mrbysco.telepass.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlayerUtil {
	@Nullable
	public static Player getPlayerEntityByName(Level level, String name) {
		return level.players().stream().filter(player -> player.getName().getString().equals(name)).findFirst().orElse(null);
	}
}
