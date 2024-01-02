package com.mrbysco.telepass.datagen.data;

import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class TeleRecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
	public TeleRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TeleItems.GOLD_TELEPASS.get())
				.pattern(" # ").pattern("#X#").pattern(" # ")
				.define('#', Tags.Items.INGOTS_GOLD)
				.define('X', Items.ENDER_EYE)
				.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(recipeConsumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TeleItems.DIAMOND_TELEPASS.get())
				.pattern(" # ").pattern("#X#").pattern(" # ")
				.define('#', Tags.Items.GEMS_DIAMOND)
				.define('X', Items.ENDER_EYE)
				.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(recipeConsumer);
	}
}
