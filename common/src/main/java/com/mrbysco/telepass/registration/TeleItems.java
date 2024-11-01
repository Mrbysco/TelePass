package com.mrbysco.telepass.registration;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * Telepass class for registering items
 */
public class TeleItems {

	/**
	 * The provider for items
	 */
	public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);

	public static final RegistryObject<Item> GOLD_TELEPASS = registerTelepass("gold_telepass", CompassMaterial.GOLD);
	public static final RegistryObject<Item> DIAMOND_TELEPASS = registerTelepass("diamond_telepass", CompassMaterial.DIAMOND);

	public static RegistryObject<Item> registerTelepass(String name, CompassMaterial material) {
		return ITEMS.register(name, () -> Services.PLATFORM.createCompass(itemBuilder().setId(getKey(name)), material));
	}

	private static ResourceKey<Item> getKey(String name) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
	}

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
