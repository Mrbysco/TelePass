package com.mrbysco.telepass.registration;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;

/**
 * Telepass class for registering creative tabs
 */
public class TeleTabs {

	/**
	 * The provider for creative tabs
	 */
	public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.MOD_ID);

	public static final RegistryObject<CreativeModeTab> TELE_TAB = CREATIVE_MODE_TABS.register("tab", Services.PLATFORM::buildCreativeTab);


	// Called in the mod initializer / constructor in order to make sure that creative tabs are registered
	public static void loadClass() {
	}
}
