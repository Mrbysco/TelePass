package com.mrbysco.telepass.datagen.server;

import com.mrbysco.telepass.TelepassFabric;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TeleItemTagProvider extends ItemTagsProvider {
	public TeleItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,
							   FabricTagProvider.BlockTagProvider blockTagsProvider) {
		super(output, completableFuture, blockTagsProvider.contentsGetter());
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		tag(TelepassFabric.GOLD_INGOTS).add(Items.GOLD_INGOT);
		tag(TelepassFabric.DIAMONDS).add(Items.DIAMOND);
	}

}