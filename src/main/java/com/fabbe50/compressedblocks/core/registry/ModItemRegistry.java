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
    @GameRegistry.ObjectHolder("extrautils2:passivegenerator") //Meta 6 = CREATIVE MILL
    public static Block PASSIVEGENERATOR = null;
    @GameRegistry.ObjectHolder("extrautils2:rainbowgenerator")
    public static Block RAINBOWGENERATOR = null;
    @GameRegistry.ObjectHolder("extrautils2:decorativebedrock")
    public static Block DECORATIVEBEDROCK = null;
    @GameRegistry.ObjectHolder("actuallyadditions:block_misc") //Meta 3 = BLACK QUARTZ ORE
    public static Block BLACK_QUARTZ = null;
    @GameRegistry.ObjectHolder("appliedenergistics2:quartz_ore")
    public static Block AE2_QUARTZ_ORE = null;
    @GameRegistry.ObjectHolder("appliedenergistics2:charged_quartz_ore")
    public static Block AE2_CHARGED_QUARTZ_ORE = null;
    @GameRegistry.ObjectHolder("deepresonance:resonating_ore")
    public static Block RESONATING_ORE = null;
    @GameRegistry.ObjectHolder("draconicevolution:draconium_ore")
    public static Block DRACONIUM_ORE = null;
    @GameRegistry.ObjectHolder("bigreactors:brore")
    public static Block YELLORITE_ORE = null;

    public static void init() {}
}
