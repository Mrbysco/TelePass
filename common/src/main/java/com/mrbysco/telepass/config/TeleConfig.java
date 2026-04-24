package com.mrbysco.telepass.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
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
}
