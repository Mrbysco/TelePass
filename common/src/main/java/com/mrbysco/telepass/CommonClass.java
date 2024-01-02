package com.mrbysco.telepass;

import com.mrbysco.telepass.registration.TeleItems;
import com.mrbysco.telepass.registration.TeleTabs;

public class CommonClass {

	public static void init() {
		TeleItems.loadClass();
		TeleTabs.loadClass();
	}
}