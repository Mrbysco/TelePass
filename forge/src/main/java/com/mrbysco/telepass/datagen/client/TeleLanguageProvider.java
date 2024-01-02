package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class TeleLanguageProvider extends LanguageProvider {
	public TeleLanguageProvider(PackOutput packOutput) {
		super(packOutput, Constants.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.telepass", "TelePass");

		addItem(TeleItems.GOLD_TELEPASS, "Gold TelePass");
		addItem(TeleItems.DIAMOND_TELEPASS, "Diamond TelePass");

		add("config.telepass.gold_durability", "Defines the amount of uses the Gold TelePass has [default: 15]");
		add("config.telepass.diamond_durability", "Defines the amount of uses the Diamond TelePass has [default: 1000]");

		add("item.telepass.offline", "§cLinked player§r %s §cis offline, teleport was canceled§r");
		add("item.telepass.self", "§cYou can't teleport to yourself!§r");
		add("item.telepass.dimension", "§cLinked player§r %s §cis in another dimension, teleport was canceled§r");
	}
}
