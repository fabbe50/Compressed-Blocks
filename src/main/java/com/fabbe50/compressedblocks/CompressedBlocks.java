package com.fabbe50.compressedblocks;

import com.fabbe50.compressedblocks.event.RegisterCraftingEvent;
import com.fabbe50.compressedblocks.handler.AchievementHandler;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.init.*;
import com.fabbe50.compressedblocks.item.ItemCorgiFood;
import com.fabbe50.compressedblocks.lib.DataCompressed;
import com.fabbe50.compressedblocks.init.ModEntities;
import com.fabbe50.compressedblocks.proxy.ServerProxy;
import com.fabbe50.compressedblocks.reference.Dependencies;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.fabbe50.compressedblocks.utility.ToolTipHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies = Dependencies.dependencies)
public class CompressedBlocks {
    @Mod.Instance(Reference.MOD_ID)
    public static CompressedBlocks instance;

    ToolTipHelper toolTipEvent = new ToolTipHelper();

    @SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        LogHelper.info("Pre-Initializing");
        //PreInit Starts Here
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        DataCompressed.init();
        ModItems.init();
        ModBlocks.init();
        ModFallbackBlocks.init();
        ModEntities.mainRegistry();
        //PreInit Ends Here
        LogHelper.info("Pre-Initialization Complete");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogHelper.info("Initializing");
        //Init Starts Here
        ItemImport.init();
        AchievementHandler.init();
        ItemCorgiFood.registerFoodTypes();
        //ModRecipes.init();
        RecipeDynamic.init();
        Recipes.init();
        FMLCommonHandler.instance().bus().register(new RegisterCraftingEvent());
        MinecraftForge.EVENT_BUS.register(toolTipEvent);
        //Init Ends Here
        LogHelper.info("Initialization Complete");
    }
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        LogHelper.info("Post-Initializing");
        //PostInit Starts Here
        proxy.registerRenders();
        RecipeReplacers.init();
        //PostInit Ends Here
        LogHelper.info("Post-Initialization Complete");
    }
}
