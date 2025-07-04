package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class TeleModelProvider extends ModelProvider {
	public TeleModelProvider(PackOutput packOutput) {
		super(packOutput, Constants.MOD_ID);
	}

	@Override
	protected void registerModels(@NotNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		itemModels.generateFlatItem(TeleItems.GOLD_TELEPASS.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(TeleItems.DIAMOND_TELEPASS.get(), ModelTemplates.FLAT_ITEM);
	}
}
