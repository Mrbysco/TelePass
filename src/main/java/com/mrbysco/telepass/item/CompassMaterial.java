package com.mrbysco.telepass.item;

import com.mrbysco.telepass.config.TeleConfig;

import java.util.function.Supplier;

public enum CompassMaterial {
    GOLD(() -> TeleConfig.COMMON.goldDurability.get()),
    DIAMOND(() -> TeleConfig.COMMON.diamondDurability.get());

    private final Supplier<Integer> durability;

    CompassMaterial(Supplier<Integer> durability) {
        this.durability = durability;
    }

    public int getMaxUses() {
        return this.durability.get();
    }
}
