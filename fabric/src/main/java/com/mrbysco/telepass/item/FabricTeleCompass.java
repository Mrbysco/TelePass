package com.mrbysco.telepass.item;

public class FabricTeleCompass extends TeleCompass {

	public FabricTeleCompass(Properties properties, CompassMaterial material) {
		//Use 200 as a fallback value for durability
		super(properties.durability(200).durability(material.getMaxUses()), material);
	}
}
