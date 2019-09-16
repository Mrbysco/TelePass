package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TeleTab extends CreativeTabs {
	public TeleTab() {
		super(Reference.MOD_ID);
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.COMPASS);
	}
}
