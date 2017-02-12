package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Created by fabbe50 on 15/09/2016.
 */
public class StatsRegistry {
    public static StatBase HQCAKE_SLICES_EATEN = (new StatBasic("stat.hqCakeSlicesEaten", new TextComponentTranslation("stat.hqCakeSlicesEaten", new Object[0]))).registerStat();
    public static StatBase POWERBEACONINTERACTION = (new StatBasic("stat.powerBeaconInteraction", new TextComponentTranslation("stat.powerBeaconInteraction", new Object[0]))).registerStat();

    public static void init() {}
}
