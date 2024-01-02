package com.mrbysco.telepass.registration;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

/**
 * Telepass class for registering items
 */
public class TeleItems {

    /**
     * The provider for items
     */
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item> GOLD_TELEPASS = ITEMS.register("gold_telepass", () -> Services.PLATFORM.createCompass(itemBuilder(), CompassMaterial.GOLD));
    public static final RegistryObject<Item> DIAMOND_TELEPASS = ITEMS.register("diamond_telepass", () -> Services.PLATFORM.createCompass(itemBuilder(), CompassMaterial.DIAMOND));

    private static Item.Properties itemBuilder() {
        return new Item.Properties();
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
