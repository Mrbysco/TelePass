package com.mrbysco.telepass.item;

import net.minecraft.world.item.ItemStack;

public class ForgeTeleCompass extends TeleCompass {

	public ForgeTeleCompass(Properties properties, CompassMaterial material) {
		super(properties, material);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getMaxUses();
	}
}
