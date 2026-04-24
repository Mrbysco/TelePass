package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class TelepassFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, TeleConfig.commonSpec);

		CommonClass.init();
	}
}
