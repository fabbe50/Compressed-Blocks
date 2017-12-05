package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 04/12/2017 - 11:06 AM.
 */
public class OreStorage {
    public static List<Block> overworldOre = new ArrayList<>();
    public static List<Integer> overworldMinSize = new ArrayList<>();
    public static List<Integer> overworldMaxSize = new ArrayList<>();
    public static List<Integer> overworldChance = new ArrayList<>();
    public static List<Integer> overworldMinY = new ArrayList<>();
    public static List<Integer> overworldMaxY = new ArrayList<>();
    public static List<Block> overworldSpawnIn = new ArrayList<>();

    public static List<Block> netherOre = new ArrayList<>();
    public static List<Integer> netherMinSize = new ArrayList<>();
    public static List<Integer> netherMaxSize = new ArrayList<>();
    public static List<Integer> netherChance = new ArrayList<>();
    public static List<Integer> netherMinY = new ArrayList<>();
    public static List<Integer> netherMaxY = new ArrayList<>();
    public static List<Block> netherSpawnIn = new ArrayList<>();

    public static List<Block> endOre = new ArrayList<>();
    public static List<Integer> endMinSize = new ArrayList<>();
    public static List<Integer> endMaxSize = new ArrayList<>();
    public static List<Integer> endChance = new ArrayList<>();
    public static List<Integer> endMinY = new ArrayList<>();
    public static List<Integer> endMaxY = new ArrayList<>();
    public static List<Block> endSpawnIn = new ArrayList<>();
}
