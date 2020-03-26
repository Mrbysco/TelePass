package com.mrbysco.telepass.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtil {
    @Nullable
    public static PlayerEntity getPlayerEntityByName(World worldIn, String name)
    {
        List<? extends PlayerEntity> playerEntities = worldIn.getPlayers();
        for (int j2 = 0; j2 < playerEntities.size(); ++j2)
        {
            PlayerEntity playerEntity = playerEntities.get(j2);

            if (name.equals(playerEntity.getName().getUnformattedComponentText()))
            {
                return playerEntity;
            }
        }

        return null;
    }
}
