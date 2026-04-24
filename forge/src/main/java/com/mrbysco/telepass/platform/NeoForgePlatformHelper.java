package com.mrbysco.telepass.platform;

import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.NeoForgeTeleCompass;
import com.mrbysco.telepass.item.TeleCompass;
import com.mrbysco.telepass.platform.services.IPlatformHelper;
import com.mrbysco.telepass.registration.TeleItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.FakePlayer;

import java.util.List;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public CreativeModeTab buildCreativeTab() {
		return CreativeModeTab.builder()
				.icon(() -> new ItemStack(TeleItems.GOLD_TELEPASS.get()))
				.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
				.title(Component.translatable("itemGroup.telepass"))
				.displayItems((displayParameters, output) -> {
					List<ItemStack> stacks = TeleItems.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
					output.acceptAll(stacks);
				}).build();
	}

	@Override
	public TeleCompass createCompass(Item.Properties properties, CompassMaterial material) {
		return new NeoForgeTeleCompass(properties, material);
	}

	@Override
	public boolean notFakePlayer(Player player) {
		return !(player instanceof FakePlayer);
	}
}
