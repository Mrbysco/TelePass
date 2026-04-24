package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Constants.MOD_ID)
public class TelepassNeoForge {

	public TelepassNeoForge(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, TeleConfig.commonSpec);

		CommonClass.init();

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}