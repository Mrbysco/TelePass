package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TeleGroup {
	public static final ItemGroup TELEPASS = new ItemGroup(Reference.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(TeleItems.GOLD_TELEPASS.get());
		}
	}.setTabPath("telepass");
}
