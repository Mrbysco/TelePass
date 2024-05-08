package com.mrbysco.telepass.registration;

import com.mojang.serialization.Codec;
import com.mrbysco.telepass.Constants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

/**
 * Telepass class for registering items
 */
public class TeleDataComponents {

	/**
	 * The provider for items
	 */
	public static final RegistrationProvider<DataComponentType<?>> DATA_COMPONENT_TYPES = RegistrationProvider.get(BuiltInRegistries.DATA_COMPONENT_TYPE, Constants.MOD_ID);

	public static final RegistryObject<DataComponentType<String>> OWNER = DATA_COMPONENT_TYPES.register("owner", () ->
			DataComponentType.<String>builder()
					.persistent(Codec.STRING)
					.networkSynchronized(ByteBufCodecs.STRING_UTF8)
					.build());

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
