package com.mrbysco.telepass.datagen.server;

import com.mrbysco.telepass.registration.TeleItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TeleRecipeRunner extends FabricRecipeProvider {
	public TeleRecipeRunner(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@NotNull
	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
		return new Provider(provider, recipeOutput);
	}

	@NotNull
	@Override
	public String getName() {
		return "Telepass recipes";
	}

	public static class Provider extends RecipeProvider {
		public Provider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
			super(provider, recipeOutput);
		}

		@Override
		public void buildRecipes() {
			shaped(RecipeCategory.TOOLS, TeleItems.GOLD_TELEPASS.get())
					.pattern(" # ").pattern("#X#").pattern(" # ")
					.define('#', ConventionalItemTags.GOLD_INGOTS)
					.define('X', Items.ENDER_EYE)
					.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(this.output);

			shaped(RecipeCategory.TOOLS, TeleItems.DIAMOND_TELEPASS.get())
					.pattern(" # ").pattern("#X#").pattern(" # ")
					.define('#', ConventionalItemTags.DIAMOND_GEMS)
					.define('X', Items.ENDER_EYE)
					.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(this.output);
		}
	}
}
