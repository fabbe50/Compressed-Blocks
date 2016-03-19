package com.fabbe50.compressedblocks;

import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.init.*;
import com.fabbe50.compressedblocks.item.ItemCorgiFood;
import com.fabbe50.compressedblocks.lib.DataCompressed;
import com.fabbe50.compressedblocks.entities.ModEntities;
import com.fabbe50.compressedblocks.proxy.ClientProxy;
import com.fabbe50.compressedblocks.proxy.IProxy;
import com.fabbe50.compressedblocks.reference.Dependencies;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies = Dependencies.dependencies)
public class CompressedBlocks {
    @Mod.Instance(Reference.MOD_ID)
    public static CompressedBlocks instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        LogHelper.info("Pre-Initializing");
        //PreInit Starts Here
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        DataCompressed.init();
        ModItems.init();
        ModBlocks.init();
        ModEntities.mainRegistry();
        //PreInit Ends Here
        LogHelper.info("Pre-Initialization Complete");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogHelper.info("Initializing");
        //Init Starts Here
        ItemImport.init();
        ItemCorgiFood.registerFoodTypes();
        ModRecipes.init();
        Recipes.init();
        //Init Ends Here
        LogHelper.info("Initialization Complete");
    }
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        LogHelper.info("Post-Initializing");
        //PostInit Starts Here
        RecipeReplacers.init();
        ClientProxy.registerRenderingThings();
        //PostInit Ends Here
        LogHelper.info("Post-Initialization Complete");
    }
}
