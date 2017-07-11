package com.fabbe50.compressedblocks.core.event;

import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 01/07/2017.
 */
public class ChatEvent {
    @SubscribeEvent
    public void pinger(ServerChatEvent event) {
        if (!event.getPlayer().world.isRemote) {
            String message = event.getMessage();
            String name = event.getUsername();
            if (message.toLowerCase().contains("what") && message.toLowerCase().contains("my") && message.toLowerCase().contains("ping")) {
                event.getPlayer().sendMessage(new TextComponentString("<" + name + "> " + message));
                event.getPlayer().sendMessage(new TextComponentString(ChatHelper.MAGENTA + "[Server] " + ChatHelper.RESET + "Your ping is " + event.getPlayer().ping + "ms!"));
                event.setCanceled(true);
            }
            else if (message.toLowerCase().contains("ping")) {
                event.getPlayer().sendMessage(new TextComponentString("<" + name + "> " + message));
                event.getPlayer().sendMessage(new TextComponentString(ChatHelper.MAGENTA + "[Server] " + ChatHelper.RESET + "Pong!"));
                event.setCanceled(true);
            }
        }
    }

    boolean botActive = false;
    @SubscribeEvent
    public void chatBot(ServerChatEvent event) {
        if (!event.getPlayer().world.isRemote) {
            String name = event.getUsername();
            String message = event.getMessage();
            if (message.toLowerCase().contains("!wake")) {
                botActive = true;
                event.getPlayer().sendMessage(new TextComponentString("[Compressed Bot] " + "I'm Awake!"));
            }
            else if (message.toLowerCase().contains("!die")) {
                botActive = false;
                event.getPlayer().sendMessage(new TextComponentString("[Compressed Bot] " + "Committing Suicide... :("));
            }
            else {
                //Bot-code goes here...
            }
        }
    }
}
