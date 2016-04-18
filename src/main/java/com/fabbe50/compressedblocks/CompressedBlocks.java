package com.fabbe50.compressedblocks;

import com.fabbe50.compressedblocks.commands.CBCommand;
import com.fabbe50.compressedblocks.commands.CBCorgicon;
import com.fabbe50.compressedblocks.event.RegisterCraftingEvent;
import com.fabbe50.compressedblocks.handler.AchievementHandler;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.init.*;
import com.fabbe50.compressedblocks.item.ItemCorgiFood;
import com.fabbe50.compressedblocks.lib.DataCompressed;
import com.fabbe50.compressedblocks.init.ModEntities;
import com.fabbe50.compressedblocks.commands.CBPingPong;
import com.fabbe50.compressedblocks.network.chat.CBChatPingClient;
import com.fabbe50.compressedblocks.network.chat.CBChatPingServer;
import com.fabbe50.compressedblocks.proxy.ServerProxy;
import com.fabbe50.compressedblocks.reference.Dependencies;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.fabbe50.compressedblocks.utility.ToolTipHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

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
        Textures.initCorgiTexture();
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
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        //PostInit Ends Here
        LogHelper.info("Post-Initialization Complete");
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.getServer();
        ICommandManager command = server.getCommandManager();
        ServerCommandManager manager = (ServerCommandManager) command;
        manager.registerCommand(new CBPingPong());
        manager.registerCommand(new CBCommand());
        manager.registerCommand(new CBCorgicon());
    }

    @Mod.EventHandler
    public void afterServerStart (FMLServerStartedEvent event) {
        MinecraftForge.EVENT_BUS.register(new CBChatPingServer());
        MinecraftForge.EVENT_BUS.register(new CBChatPingClient());
    }
}
