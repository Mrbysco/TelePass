package com.mrbysco.telepass.item;

import com.mrbysco.telepass.platform.Services;

import java.util.function.Supplier;

public enum CompassMaterial {
	GOLD(Services.PLATFORM::goldDurability),
	DIAMOND(Services.PLATFORM::diamondDurability);

	private final Supplier<Integer> durability;

	CompassMaterial(Supplier<Integer> durability) {
		this.durability = durability;
	}

	public int getMaxUses() {
		return this.durability.get();
	}
}
