package com.fabbe50.compressedblocks.core.utils.helper;

import com.thefifthidiot.tficore.lib.GameInfo;
import net.minecraft.world.GameRules;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe50 on 02/02/2017.
 */
public class GameHelper {
    public static GameRules gameRules;

    @SubscribeEvent
    public static void registerGamerules() {
        gameRules = GameInfo.gameRules;
    }
}
