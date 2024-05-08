package com.mrbysco.telepass.datagen;

import com.mrbysco.telepass.datagen.client.TeleItemModelProvider;
import com.mrbysco.telepass.datagen.client.TeleLanguageProvider;
import com.mrbysco.telepass.datagen.data.TeleRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModDatagenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new TeleRecipeProvider(packOutput, lookupProvider));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new TeleLanguageProvider(packOutput));
			generator.addProvider(event.includeClient(), new TeleItemModelProvider(packOutput, helper));
		}
	}
}