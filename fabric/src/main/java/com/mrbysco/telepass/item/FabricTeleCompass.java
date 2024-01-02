package com.mrbysco.telepass.item;

public class FabricTeleCompass extends TeleCompass {

	public FabricTeleCompass(Properties properties, CompassMaterial material) {
		super(properties.durability(material.getMaxUses()), material);
	}
}
