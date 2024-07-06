package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class TelepassFabric implements ModInitializer {
	public static ConfigHolder<TeleConfig> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(TeleConfig.class, Toml4jConfigSerializer::new);

		CommonClass.init();
	}
}
