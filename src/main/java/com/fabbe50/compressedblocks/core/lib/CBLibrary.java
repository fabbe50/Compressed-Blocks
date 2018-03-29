package com.fabbe50.compressedblocks.core.lib;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class CBLibrary {
    //These are blocks that both have an item and a block.
    public static List<String> compressedBlocks = new ArrayList<>();
    public static Map<Block, Block> replacementBlock = new HashMap<>();

    public static void init() {
        initCompressedBlocks();
        initReplacementBlocks();
    }

    private static void initCompressedBlocks() {
        compressedBlocks.add("potato");
        compressedBlocks.add("iron");
        compressedBlocks.add("gold");
        compressedBlocks.add("diamond");
        compressedBlocks.add("emerald");
    }

    private static void initReplacementBlocks() {
        replacementBlock.put(Blocks.GRASS, Blocks.DIRT);
        replacementBlock.put(Blocks.LEAVES, BlockRegistry.DEAD_LEAVES);
        replacementBlock.put(Blocks.LEAVES2, BlockRegistry.DEAD_LEAVES);
        replacementBlock.put(Blocks.LOG, Blocks.COAL_BLOCK);
        replacementBlock.put(Blocks.LOG2, Blocks.COAL_BLOCK);
        replacementBlock.put(Blocks.SAPLING, Blocks.DEADBUSH);
        replacementBlock.put(Blocks.TALLGRASS, Blocks.AIR);
        replacementBlock.put(Blocks.YELLOW_FLOWER, Blocks.AIR);
        replacementBlock.put(Blocks.RED_FLOWER, Blocks.AIR);
        replacementBlock.put(Blocks.BROWN_MUSHROOM, Blocks.AIR);
        replacementBlock.put(Blocks.RED_MUSHROOM, Blocks.AIR);
        replacementBlock.put(Blocks.VINE, Blocks.AIR);
        replacementBlock.put(Blocks.WATERLILY, Blocks.AIR);
        replacementBlock.put(Blocks.DOUBLE_PLANT, Blocks.AIR);
        replacementBlock.put(Blocks.CHORUS_PLANT, Blocks.AIR);
        replacementBlock.put(Blocks.CHORUS_FLOWER, Blocks.AIR);
        replacementBlock.put(Blocks.WHEAT, Blocks.AIR);
        replacementBlock.put(Blocks.REEDS, Blocks.AIR);
        replacementBlock.put(Blocks.CACTUS, Blocks.AIR);
        replacementBlock.put(Blocks.POTATOES, Blocks.AIR);
        replacementBlock.put(Blocks.CARROTS, Blocks.AIR);
        replacementBlock.put(Blocks.NETHER_WART, Blocks.AIR);
        replacementBlock.put(Blocks.BEETROOTS, Blocks.AIR);
        replacementBlock.put(Blocks.MELON_BLOCK, Blocks.COAL_BLOCK);
        replacementBlock.put(Blocks.MELON_STEM, Blocks.AIR);
        replacementBlock.put(Blocks.PUMPKIN_STEM, Blocks.COAL_BLOCK);
        replacementBlock.put(Blocks.PUMPKIN, Blocks.AIR);
        replacementBlock.put(Blocks.LIT_PUMPKIN, Blocks.COAL_BLOCK);
    }
}
