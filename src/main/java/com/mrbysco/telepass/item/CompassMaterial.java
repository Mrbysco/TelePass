package com.mrbysco.telepass.item;

import com.mrbysco.telepass.config.TeleConfig;

public enum CompassMaterial {
	GOLD(TeleConfig.SERVER.goldDurability.get()),
    DIAMOND(TeleConfig.SERVER.diamondDurability.get());
	
	private final int durability;

	private CompassMaterial(int durability)
    {
        this.durability = durability;
    }
	
	public int getMaxUses()
    {
        return this.durability;
    }
}
