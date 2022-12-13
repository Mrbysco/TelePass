package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class TeleGroup {
	private static CreativeModeTab TELEPASS;

	@SubscribeEvent
	public void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		TELEPASS = event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "telepass"), builder ->
				builder.icon(() -> new ItemStack(TeleItems.GOLD_TELEPASS.get()))
						.title(Component.translatable("itemGroup.telepass"))
						.displayItems((features, output, hasPermissions) -> {
							List<ItemStack> stacks = TeleItems.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
	}
}
