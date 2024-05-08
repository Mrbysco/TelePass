package com.mrbysco.telepass;

import com.mrbysco.telepass.registration.TeleDataComponents;
import com.mrbysco.telepass.registration.TeleItems;
import com.mrbysco.telepass.registration.TeleTabs;

public class CommonClass {

	public static void init() {
		TeleDataComponents.loadClass();
		TeleItems.loadClass();
		TeleTabs.loadClass();
	}
}