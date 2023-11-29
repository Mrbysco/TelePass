package com.mrbysco.telepass.config;

import com.mrbysco.telepass.TelePass;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class TeleConfig {

	public static class Common {
		public final IntValue goldDurability;
		public final IntValue diamondDurability;

		Common(ModConfigSpec.Builder builder) {
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

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		TelePass.LOGGER.debug("Loaded TelePass' config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		TelePass.LOGGER.warn("TelePass' config just got changed on the file system!");
	}
}
