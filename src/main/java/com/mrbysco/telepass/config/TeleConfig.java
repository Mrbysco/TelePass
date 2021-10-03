package com.mrbysco.telepass.config;

import com.mrbysco.telepass.TelePass;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class TeleConfig {

	public static class Common {
		public final IntValue goldDurability;
		public final IntValue diamondDurability;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("general");

			goldDurability = builder
					.comment("Defines the amount of uses the Gold TelePass has [Default: 15]")
					.defineInRange("goldDurability", 15, 0, Integer.MAX_VALUE);

			diamondDurability = builder
					.comment("Defines the amount of uses the Diamond TelePass has [Default: 1000]")
					.defineInRange("diamondDurability", 1000, 0, Integer.MAX_VALUE);

			builder.pop();
		}
	}
	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		TelePass.logger.debug("Loaded TelePass' config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		TelePass.logger.debug("TelePass' config just got changed on the file system!");
	}
}
