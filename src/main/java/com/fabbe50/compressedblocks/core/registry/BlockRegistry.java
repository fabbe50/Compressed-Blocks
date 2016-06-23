package com.fabbe50.compressedblocks.core.registry;

import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.common.blocks.base.CompressedBlock;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.init.TFIBlocks;
import com.thefifthidiot.tficore.render.BlockRenderer;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockRegistry {
    //Using TFICore, basic blocks do not need a block-class on it's own.
	public static final Block potatoBlock = new BlockBase(Material.CAKE, MapColor.YELLOW, "potatoBlock", 2.0f, 10.0f, TFITab.blockTab);
    public static final Block compressedPotato = new CompressedBlock(Material.CAKE, MapColor.BROWN, Reference.MOD_ID, "potato_compr", 2.0f, 10.0f, null);
    public static final Block compressedCobble = new CompressedBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
    public static final Block compressedDirt = new CompressedBlock(Material.GROUND, MapColor.BROWN, Reference.MOD_ID, "dirt_compr", 1.5f, 5.0f, null);
    public static final Block compressedSand = new CompressedBlock(Material.SAND, MapColor.SAND, Reference.MOD_ID, "sand_compr", 1.5f, 3.5f, null);
    public static final Block compressedGravel = new CompressedBlock(Material.GROUND, MapColor.GRAY, Reference.MOD_ID, "gravel_compr", 1.5f, 3.5f, null);

    /*  If more data on a block is needed
     *  Example 1:
     *  public static final Block compressedCobbleWithData = new CompressedBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null).setLightLevel(0.8f);
     *  Using Ex1, you can easily add one or two things without having to make an extra class.
     *  Example 2:
     *  public static final Block compressedCobbleWithData = new CompressedCobbleBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
     *  Using Ex2, you extend the base class and add your extra data in the constructor of that class.
     */

	public static void init() {
		TFIBlocks.addBlock(potatoBlock);

        TFIBlocks.registerMetaBlock(0, new ResourceLocation(Reference.MOD_ID, "potato_compr"), compressedPotato);
        TFIBlocks.registerMetaBlock(1, new ResourceLocation(Reference.MOD_ID, "cobble_compr"), compressedCobble);
        TFIBlocks.registerMetaBlock(2, new ResourceLocation(Reference.MOD_ID, "dirt_compr"), compressedDirt);
        TFIBlocks.registerMetaBlock(3, new ResourceLocation(Reference.MOD_ID, "sand_compr"), compressedSand);
        TFIBlocks.registerMetaBlock(4, new ResourceLocation(Reference.MOD_ID, "gravel_compr"), compressedGravel);
	}
	
	public static void renderInit() {
		BlockRenderer.registerBlock(potatoBlock, 0);

        registerComprBlock(compressedPotato);
        registerComprBlock(compressedCobble);
        registerComprBlock(compressedDirt);
        registerComprBlock(compressedSand);
        registerComprBlock(compressedGravel);
	}

    //TODO: Move to TFICore... with better support for naming
    private static void registerComprBlock (Block block /*, String[] values*/) {
        String[] values = new String[] {"single", "double", "triple", "quadruple", "quintuple", "sextuple", "septuple", "octuple"};

        for (int i = 0; i < values.length; i++) {
            BlockRenderer.registerMetaBlockItem(block, i, values[i]);
        }
    }
}
