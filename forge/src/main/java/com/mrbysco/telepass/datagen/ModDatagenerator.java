package com.mrbysco.telepass.datagen;

import com.mrbysco.telepass.datagen.client.TeleLanguageProvider;
import com.mrbysco.telepass.datagen.client.TeleModelProvider;
import com.mrbysco.telepass.datagen.data.TeleRecipeRunner;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

@EventBusSubscriber
public class ModDatagenerator {
	@SubscribeEvent
	static void onGatherRegistries(GatherDataRegistryEntriesEvent event) {
		event.recipe(TeleRecipeRunner::new);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		event.createProvider(TeleLanguageProvider::new);
		event.createProvider(TeleModelProvider::new);
	}
}