package com.mrbysco.telepass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mrbysco.telepass.config.TeleConfig;
import com.mrbysco.telepass.init.TeleTab;
import com.mrbysco.telepass.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, 
	name = Reference.MOD_NAME, 
	version = Reference.VERSION, 
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
	dependencies = Reference.DEPENDENCIES)
public class TelePass {
	@Instance(Reference.MOD_ID)
	public static TelePass instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);
		
	public static TeleTab teleTab = new TeleTab();
			
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{		
		logger.info("Registering config");
		MinecraftForge.EVENT_BUS.register(new TeleConfig());

		proxy.Preinit();
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
	{
//		logger.info("Registering event handlers");
//		MinecraftForge.EVENT_BUS.register(EnhancedRecipeEvents.class);
		
		proxy.Init();
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
}
