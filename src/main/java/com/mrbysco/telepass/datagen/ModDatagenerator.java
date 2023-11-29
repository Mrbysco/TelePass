package com.mrbysco.telepass.datagen;

import com.mrbysco.telepass.datagen.client.TeleItemModelProvider;
import com.mrbysco.telepass.datagen.client.TeleLanguageProvider;
import com.mrbysco.telepass.datagen.data.TeleRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new TeleRecipeProvider(packOutput, event.getLookupProvider()));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new TeleLanguageProvider(packOutput));
			generator.addProvider(event.includeClient(), new TeleItemModelProvider(packOutput, helper));
		}
	}
}