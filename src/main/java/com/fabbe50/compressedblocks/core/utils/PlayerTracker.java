package com.fabbe50.compressedblocks.core.utils;

import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 23/04/2017.
 */
public class PlayerTracker {
    public static List<String> players = new ArrayList<>();

    @SubscribeEvent
    public void checkPlayerOnline(PlayerEvent.PlayerLoggedInEvent event) {
        MinecraftServer server = event.player.getServer();
        if (server != null) {
            players.add(event.player.getName());
            LogHelper.info("Added Player: " + event.player.getName());
        }
    }

    @SubscribeEvent
    public void checkPlayerOffline(PlayerEvent.PlayerLoggedOutEvent event) {
        MinecraftServer server = event.player.getServer();
        if (server != null) {
            players.remove(event.player.getName());
            LogHelper.info("Removed Player");
        }
    }
}
