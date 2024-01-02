package com.mrbysco.telepass;

import com.mrbysco.telepass.config.TeleConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TelepassFabric implements ModInitializer {
	public static final TagKey<Item> GOLD_INGOTS = createTag("gold_ingots");
	public static final TagKey<Item> DIAMONDS = createTag("diamonds");

	public static ConfigHolder<TeleConfig> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(TeleConfig.class, Toml4jConfigSerializer::new);

		CommonClass.init();
	}

	private static TagKey<Item> createTag(String path) {
		return TagKey.create(Registries.ITEM, new ResourceLocation("c", path));
	}
}
