package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TeleGroup {
	public static final CreativeModeTab TELEPASS = new CreativeModeTab(Reference.MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TeleItems.GOLD_TELEPASS.get());
		}
	};
}
