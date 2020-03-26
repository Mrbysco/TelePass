package com.mrbysco.telepass.init;

import com.mrbysco.telepass.Reference;
import com.mrbysco.telepass.item.CompassMaterial;
import com.mrbysco.telepass.item.ItemTeleCompass;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TeleItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> GOLD_TELEPASS = ITEMS.register("gold_telepass", () -> new ItemTeleCompass(itemBuilder(), CompassMaterial.GOLD));
    public static final RegistryObject<Item> DIAMOND_TELEPASS = ITEMS.register("diamond_telepass", () -> new ItemTeleCompass(itemBuilder(), CompassMaterial.DIAMOND));

    private static Item.Properties itemBuilder()
    {
        return new Item.Properties();
    }
}
