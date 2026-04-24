package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.registration.TeleItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class TeleItemModelProvider extends FabricModelProvider {
	public TeleItemModelProvider(FabricPackOutput packOutput) {
		super(packOutput);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		itemModelGenerator.generateFlatItem(TeleItems.GOLD_TELEPASS.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(TeleItems.DIAMOND_TELEPASS.get(), ModelTemplates.FLAT_ITEM);
	}
}
