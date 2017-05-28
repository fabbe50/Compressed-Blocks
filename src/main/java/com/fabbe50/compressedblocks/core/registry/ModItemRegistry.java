package com.fabbe50.compressedblocks.core.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
    @GameRegistry.ObjectHolder("extrautils2:Teleporter") //Meta 1 = DEEPDARK
    public static Block TELEPORTER = null;
    @GameRegistry.ObjectHolder("extrautils2:Quarry")
    public static Block EUQUARRY = null;
    @GameRegistry.ObjectHolder("extrautils2:DecorativeSolid") //Meta 3 = STONEBURNT
    public static Block DECORATIVE_SOLID = null;

    public static void init() {}
}
