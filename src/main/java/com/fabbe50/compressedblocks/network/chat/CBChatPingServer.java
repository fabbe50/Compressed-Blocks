package com.fabbe50.compressedblocks.network.chat;


import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

/**
 * Created by fabbe50 on 23/03/2016.
 */
public class CBChatPingServer {
    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void readChat (ServerChatEvent event) {
        String message = event.message;
        String playerName = event.player.getDisplayName();

        if (message.equalsIgnoreCase("ping") && ConfigurationHandler.pinginchat) {
            FMLCommonHandler.instance().getMinecraftServerInstance().addChatMessage(new ChatComponentText("pong"));

            //FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText("pong"));
        }
    }
}
