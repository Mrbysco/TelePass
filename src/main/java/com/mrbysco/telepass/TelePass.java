package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import com.mrbysco.telepass.init.TeleItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class TelePass {
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	public TelePass() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(Type.COMMON, TeleConfig.commonSpec);
		FMLJavaModLoadingContext.get().getModEventBus().register(TeleConfig.class);

		TeleItems.ITEMS.register(eventBus);
	}
}
