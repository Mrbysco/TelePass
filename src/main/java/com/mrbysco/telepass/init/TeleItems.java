package com.mrbysco.telepass.init;

import java.util.ArrayList;

import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.ItemTeleCompass;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class TeleItems {
	public static ArrayList<Item> ITEMS = new ArrayList<>();
	
	public static Item GOLD_TELEPASS, DIAMOND_TELEPASS;
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		GOLD_TELEPASS = registerItem(new ItemTeleCompass("gold_telepass", CompassMaterial.GOLD));
		DIAMOND_TELEPASS = registerItem(new ItemTeleCompass("diamond_telepass", CompassMaterial.DIAMOND));
				 
		registry.registerAll(ITEMS.toArray(new Item[0]));
    }
    
    public static <T extends Item> T registerItem(T item)
    {
        ITEMS.add(item);
        return item;
    }
}
