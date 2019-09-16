package com.mrbysco.telepass.item;

import com.mrbysco.telepass.config.TeleConfig;

public enum CompassMaterial {
	GOLD(TeleConfig.general.goldDurability),
    DIAMOND(TeleConfig.general.diamondDurability);
	
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
