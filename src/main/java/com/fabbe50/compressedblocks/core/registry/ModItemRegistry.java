package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by fabbe50 on 17/09/2016.
 */
public class ModItemRegistry {
    @GameRegistry.ObjectHolder("extrautils2:CompressedCobblestone")
    public static Block EU2_COBBLE = null;
    @GameRegistry.ObjectHolder("extrautils2:CompressedDirt")
    public static Block EU2_DIRT = null;
    @GameRegistry.ObjectHolder("extrautils2:CompressedGravel")
    public static Block EU2_GRAVEL = null;
    @GameRegistry.ObjectHolder("extrautils2:CompressedNetherrack")
    public static Block EU2_NETHERRACK = null;
    @GameRegistry.ObjectHolder("extrautils2:CompressedSand")
    public static Block EU2_SAND = null;


    public static void init() {}
}
