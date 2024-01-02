package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.registration.TeleItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class TeleLanguageProvider extends FabricLanguageProvider {
	public TeleLanguageProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder builder) {
		builder.add("itemGroup.telepass", "TelePass");

		builder.add(TeleItems.GOLD_TELEPASS.get(), "Gold TelePass");
		builder.add(TeleItems.DIAMOND_TELEPASS.get(), "Diamond TelePass");

		builder.add("config.telepass.gold_durability", "Defines the amount of uses the Gold TelePass has [default: 15]");
		builder.add("config.telepass.diamond_durability", "Defines the amount of uses the Diamond TelePass has [default: 1000]");

		builder.add("item.telepass.offline", "§cLinked player§r %s §cis offline, teleport was canceled§r");
		builder.add("item.telepass.self", "§cYou can't teleport to yourself!§r");
		builder.add("item.telepass.dimension", "§cLinked player§r %s §cis in another dimension, teleport was canceled§r");

		builder.add("text.autoconfig.telepass.title", "Telepass");
		builder.add("text.autoconfig.telepass.option.general", "General");
		builder.add("text.autoconfig.telepass.option.general.goldDurability", "Gold Durability");
		builder.add("text.autoconfig.telepass.option.general.diamondDurability", "Diamond Durability");
	}
}
