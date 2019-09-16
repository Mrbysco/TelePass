package com.mrbysco.telepass.config;

import com.mrbysco.telepass.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, category = "", name = "TelePass")
public class TeleConfig {

	@Config.Comment({"General settings"})
	public static General general = new General();
	
	public static class General{
		@Config.Comment("Defines the amount of uses the Gold TelePass has [default: 15]")
		@Config.Name("Gold Durability")
		public int goldDurability = 15;
		
		@Config.Comment("Defines the amount of uses the Diamond TelePass has [default: 1000]")
		@Config.Name("Diamond Durability")
		public int diamondDurability = 1000;
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
