package com.mrbysco.telepass.datagen.data;

import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;

public class TeleRecipeRunner extends RecipeProvider {
	public TeleRecipeRunner(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
		super(recipeOutput, advancementOutput);
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
