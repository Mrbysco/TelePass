package com.mrbysco.telepass.datagen.server;

import com.mrbysco.telepass.TelepassFabric;
import com.mrbysco.telepass.registration.TeleItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TeleRecipeProvider extends FabricRecipeProvider {
	public TeleRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput recipeOutput) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TeleItems.GOLD_TELEPASS.get())
				.pattern(" # ").pattern("#X#").pattern(" # ")
				.define('#', TelepassFabric.GOLD_INGOTS)
				.define('X', Items.ENDER_EYE)
				.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TeleItems.DIAMOND_TELEPASS.get())
				.pattern(" # ").pattern("#X#").pattern(" # ")
				.define('#', TelepassFabric.DIAMONDS)
				.define('X', Items.ENDER_EYE)
				.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(recipeOutput);
	}
}