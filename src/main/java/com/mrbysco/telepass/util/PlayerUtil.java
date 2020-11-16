package com.mrbysco.telepass.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtil {
    @Nullable
    public static PlayerEntity getPlayerEntityByName(World worldIn, String name) {
        List<? extends PlayerEntity> playerEntities = worldIn.getPlayers();
        for (PlayerEntity playerEntity : playerEntities) {
            if (name.equals(playerEntity.getName().getUnformattedComponentText())) {
                return playerEntity;
            }
        }
        return null;
    }
}
