package com.mrbysco.telepass.datagen.client;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;

public class TeleItemModelProvider extends ItemModelProvider {
	public TeleItemModelProvider(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, Constants.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		generatedItem(TeleItems.GOLD_TELEPASS.getId());
		generatedItem(TeleItems.DIAMOND_TELEPASS.getId());
	}

	private void generatedItem(ResourceLocation location) {
		singleTexture(location.getPath(), new ResourceLocation("item/generated"),
				"layer0", new ResourceLocation(location.getNamespace(), "item/" + location.getPath()));
	}
}
