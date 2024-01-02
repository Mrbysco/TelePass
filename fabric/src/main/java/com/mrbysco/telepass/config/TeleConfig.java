package com.mrbysco.telepass.config;

import com.mrbysco.telepass.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
public class TeleConfig implements ConfigData {

	@CollapsibleObject
	public General general = new General();

	public static class General {
		@Comment("Defines the amount of uses the Gold TelePass has [Default: 15]")
		public int goldDurability = 15;

		@Comment("Defines the amount of uses the Diamond TelePass has [Default: 1000]")
		public int diamondDurability = 1000;

	}
}