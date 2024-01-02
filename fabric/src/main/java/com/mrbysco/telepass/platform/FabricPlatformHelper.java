package com.mrbysco.telepass.platform;

import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.FabricTeleCompass;
import com.mrbysco.telepass.item.TeleCompass;
import com.mrbysco.telepass.platform.services.IPlatformHelper;
import com.mrbysco.telepass.registration.TeleItems;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FabricPlatformHelper implements IPlatformHelper {


	@Override
	public CreativeModeTab buildCreativeTab() {
		return new FabricItemGroupBuilderImpl()
				.icon(() -> new ItemStack(TeleItems.GOLD_TELEPASS.get()))
				.title(Component.translatable("itemGroup.telepass"))
				.displayItems((displayParameters, output) -> {
					List<ItemStack> stacks = TeleItems.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
					output.acceptAll(stacks);
				}).build();
	}

	@Override
	public TeleCompass createCompass(Item.Properties properties, CompassMaterial material) {
		return new FabricTeleCompass(properties, material);
	}

	@Override
	public int goldDurability() {
		return 0;
	}

	@Override
	public int diamondDurability() {
		return 0;
	}

	@Override
	public boolean notFakePlayer(Player player) {
		return true;
	}
}
