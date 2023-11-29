package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;
import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.TeleCompassItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TeleItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);

	public static final DeferredItem<TeleCompassItem> GOLD_TELEPASS = ITEMS.register("gold_telepass", () -> new TeleCompassItem(itemBuilder(), CompassMaterial.GOLD));
	public static final DeferredItem<TeleCompassItem> DIAMOND_TELEPASS = ITEMS.register("diamond_telepass", () -> new TeleCompassItem(itemBuilder(), CompassMaterial.DIAMOND));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
