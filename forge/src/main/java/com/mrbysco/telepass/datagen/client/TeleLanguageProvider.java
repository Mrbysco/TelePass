package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

public class TeleLanguageProvider extends LanguageProvider {
	public TeleLanguageProvider(PackOutput packOutput) {
		super(packOutput, Constants.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.telepass", "TelePass");

		addItem(TeleItems.GOLD_TELEPASS, "Gold TelePass");
		addItem(TeleItems.DIAMOND_TELEPASS, "Diamond TelePass");

		add("item.telepass.offline", "§cLinked player§r %s §cis offline, teleport was canceled§r");
		add("item.telepass.self", "§cYou can't teleport to yourself!§r");
		add("item.telepass.dimension", "§cLinked player§r %s §cis in another dimension, teleport was canceled§r");

		addConfig("general", "General", "General Settings");
		addConfig("goldDurability", "Gold Durability", "Defines the amount of uses the Gold TelePass has [default: 15]");
		addConfig("diamondDurability", "Diamond Durability", "Defines the amount of uses the Diamond TelePass has [default: 1000]");
	}

	/**
	 * Add the translation for a config entry
	 *
	 * @param path        The path of the config entry
	 * @param name        The name of the config entry
	 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
	 */
	private void addConfig(String path, String name, @Nullable String description) {
		this.add(Constants.MOD_ID + ".configuration." + path, name);
		if (description != null && !description.isEmpty())
			this.add(Constants.MOD_ID + ".configuration." + path + ".tooltip", description);
	}
}
