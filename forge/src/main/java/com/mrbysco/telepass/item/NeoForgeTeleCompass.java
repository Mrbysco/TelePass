package com.mrbysco.telepass.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class NeoForgeTeleCompass extends TeleCompass {

	public NeoForgeTeleCompass(Properties properties, CompassMaterial material) {
		super(properties.durability(200), material);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return stack.getOrDefault(DataComponents.MAX_DAMAGE, material.getMaxUses());
	}
}
