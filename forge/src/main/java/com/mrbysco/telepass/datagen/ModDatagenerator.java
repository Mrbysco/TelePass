package com.mrbysco.telepass.datagen;

import com.mrbysco.telepass.datagen.client.TeleModelProvider;
import com.mrbysco.telepass.datagen.client.TeleLanguageProvider;
import com.mrbysco.telepass.datagen.data.TeleRecipeRunner;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModDatagenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new TeleRecipeRunner(packOutput, lookupProvider));

		generator.addProvider(true, new TeleLanguageProvider(packOutput));
		generator.addProvider(true, new TeleModelProvider(packOutput));
	}
}