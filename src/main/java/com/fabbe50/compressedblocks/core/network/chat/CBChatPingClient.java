package com.fabbe50.compressedblocks.core.network.chat;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

/**
 * Created by fabbe50 on 23/03/2016.
 */
public class CBChatPingClient {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void pingPong (ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.length() > 4) {
            message = message.substring(message.length() - 4);
        }

        if (message.equalsIgnoreCase("ping") && ConfigurationHandler.pinginchat) {
            FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText("pong"));
        }
    }
}
