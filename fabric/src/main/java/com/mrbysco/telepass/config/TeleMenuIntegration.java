package com.mrbysco.telepass.config;

import com.mrbysco.telepass.Constants;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class TeleMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return (Screen screen) -> new ConfigurationScreen(Constants.MOD_ID, screen);
	}
}
