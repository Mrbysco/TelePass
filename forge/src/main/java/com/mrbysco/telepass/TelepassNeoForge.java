package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class TelepassNeoForge {

	public TelepassNeoForge(IEventBus eventBus, ModContainer container) {
		container.registerConfig(ModConfig.Type.COMMON, TeleConfig.commonSpec);
		eventBus.register(TeleConfig.class);

		CommonClass.init();
	}
}