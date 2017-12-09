package com.fabbe50.compressedblocks;

import com.fabbe50.compressedblocks.core.proxy.CommonProxy;
import com.fabbe50.compressedblocks.core.reference.Dependencies;
import com.fabbe50.compressedblocks.core.reference.Reference;

import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = "[1.12.2]", dependencies = Dependencies.dependencies, guiFactory = Reference.GUIFACTORY)
public class CompressedBlocks {
	@Instance(Reference.MOD_ID)
	public static CompressedBlocks instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        LogHelper.setLogger(event.getModLog());
        this.proxy.preInit(event);
	}
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	this.proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	this.proxy.postInit(event);
    }
    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        this.proxy.onServerStarted(event);
    }
}
