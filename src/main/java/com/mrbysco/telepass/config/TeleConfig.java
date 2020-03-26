package com.mrbysco.telepass.config;

import com.mrbysco.telepass.TelePass;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

//@Config(modid = Reference.MOD_ID, category = "", name = "TelePass")
public class TeleConfig {

	public static class Server {
		public final IntValue goldDurability;
		public final IntValue diamondDurability;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Server settings")
					.push("Server");

			goldDurability = builder
					.comment("Defines the amount of uses the Gold TelePass has [default: 15]")
					.defineInRange("diamondDurability", 15, 0, Integer.MAX_VALUE);

			diamondDurability = builder
					.comment("Defines the amount of uses the Diamond TelePass has [default: 1000]")
					.defineInRange("diamondDurability", 1000, 0, Integer.MAX_VALUE);

			builder.pop();
		}
	}
	public static final ForgeConfigSpec serverSpec;
	public static final Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
		TelePass.logger.debug("Loaded TelePass config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading configEvent) {
		TelePass.logger.debug("TelePass' config just got changed on the file system!");
	}
}
