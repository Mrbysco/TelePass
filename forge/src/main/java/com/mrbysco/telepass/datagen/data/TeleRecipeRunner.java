package com.mrbysco.telepass.datagen.data;

import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TeleRecipeRunner extends RecipeProvider.Runner {
	public TeleRecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future);
	}

	@NotNull
	@Override
	protected RecipeProvider createRecipeProvider(@NotNull HolderLookup.Provider provider, @NotNull RecipeOutput recipeOutput) {
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
					.define('#', Tags.Items.INGOTS_GOLD)
					.define('X', Items.ENDER_EYE)
					.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(this.output);

			shaped(RecipeCategory.TOOLS, TeleItems.DIAMOND_TELEPASS.get())
					.pattern(" # ").pattern("#X#").pattern(" # ")
					.define('#', Tags.Items.GEMS_DIAMOND)
					.define('X', Items.ENDER_EYE)
					.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(this.output);
		}
	}
}
