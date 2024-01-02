package com.mrbysco.telepass.datagen;

import com.mrbysco.telepass.datagen.client.TeleItemModelProvider;
import com.mrbysco.telepass.datagen.client.TeleLanguageProvider;
import com.mrbysco.telepass.datagen.server.TeleBlockTagProvider;
import com.mrbysco.telepass.datagen.server.TeleItemTagProvider;
import com.mrbysco.telepass.datagen.server.TeleRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TeleDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		var pack = generator.createPack();

		pack.addProvider(TeleRecipeProvider::new);
		pack.addProvider(TeleLanguageProvider::new);
		pack.addProvider(TeleItemModelProvider::new);
		var blockTagProvider = pack.addProvider(TeleBlockTagProvider::new);
		pack.addProvider((output, lookup) -> new TeleItemTagProvider(output, lookup, blockTagProvider));
	}
}
