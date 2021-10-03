package com.mrbysco.telepass.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtil {
    @Nullable
    public static Player getPlayerEntityByName(Level worldIn, String name) {
        List<? extends Player> playerEntities = worldIn.players();
        for (Player playerEntity : playerEntities) {
            if (name.equals(playerEntity.getName().getContents())) {
                return playerEntity;
            }
        }
        return null;
    }
}
