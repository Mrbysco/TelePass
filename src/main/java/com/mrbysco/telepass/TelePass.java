package com.mrbysco.telepass;

import com.mojang.logging.LogUtils;
import com.mrbysco.telepass.config.TeleConfig;
import com.mrbysco.telepass.init.TeleGroup;
import com.mrbysco.telepass.init.TeleItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID)
public class TelePass {
	public static final Logger LOGGER = LogUtils.getLogger();

	public TelePass() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TeleConfig.commonSpec);
		FMLJavaModLoadingContext.get().getModEventBus().register(TeleConfig.class);

		TeleItems.ITEMS.register(eventBus);
		TeleGroup.CREATIVE_MODE_TABS.register(eventBus);
	}
}
