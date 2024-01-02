package com.mrbysco.telepass.datagen.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TeleBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public TeleBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider lookupProvider) {

	}
}