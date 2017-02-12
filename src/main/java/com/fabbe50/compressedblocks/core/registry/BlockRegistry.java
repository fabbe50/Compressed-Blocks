package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.blocks.*;
import com.fabbe50.compressedblocks.common.blocks.meta.MetaCompressedBase;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBeaconXray;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityFurnaceCompr;
import com.fabbe50.compressedblocks.core.reference.MetaValues;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.thefifthidiot.tficore.init.TFIBlocks;
import com.thefifthidiot.tficore.render.BlockRenderer;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {
    //Using TFICore, basic blocks do not need a block-class on it's own.
	public static final Block POTATO_BLOCK = new BlockBase(Material.CAKE, MapColor.YELLOW, "potatoBlock", 2.0f, 10.0f, null);
    public static final Block HIGH_QUALITY_CAKE = new BlockHQCake();
    public static final Block FALLTRAPBLOCK = new BlockDisappear(Material.ROCK, MapColor.GRASS, "falltrap", 0.7f, 1.2f, null);
    public static final Block OVERLAYTEST = new BlockOverlay(Material.ROCK, MapColor.GRAY, "overlayblock", 1.0f, 1.0f, null).setCreativeTab(null);
    public static final Block SURPRISEBLOCK = new BlockSurprise(Material.IRON, MapColor.YELLOW, "surpriseblock", 0.2f, 100f, null);
    public static final Block COLORBLOCK = new BlockMultiColored(Material.REDSTONE_LIGHT, null, "color_block", 1.3f, 6f, null);
    public static final Block NETHER_STONE = new BlockNetherStone(Material.ROCK, MapColor.PURPLE, "nether_stone", 2.0f, 10.0f, null);
    public static final Block NETHER_STAR_BLOCK = new BlockBase(Material.ROCK, MapColor.SNOW, "netherstarblock", 2.0f, 20f, null);
    public static final Block ENDGAMIUMBLOCK = new BlockBase(Material.IRON, MapColor.DIAMOND, "endgamiumblock", 6.0f, 20f, null);
    public static final Block ENDGAMIUMBLOCKC = new BlockBase(Material.IRON, MapColor.DIAMOND, "endgamiumblockc", 8.0f, 30f, null);
    public static final Block BLACKHOLE = new BlockBlackHole(Material.AIR, MapColor.BLACK, "blackhole", 50f, 50f, null);

    //Compressed Blocks
    public static final Block COMPRESSED_POTATO = new MetaCompressedBase(Material.CAKE, MapColor.BROWN, Reference.MOD_ID, "potato_compr", 2.0f, 10.0f, null);
    public static final Block COMPRESSED_COBBLESTONE = new MetaCompressedBase(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
    public static final Block COMPRESSED_DIRT = new MetaCompressedBase(Material.GROUND, MapColor.BROWN, Reference.MOD_ID, "dirt_compr", 1.5f, 5.0f, null);
    public static final Block COMPRESSED_SAND = new MetaCompressedBase(Material.SAND, MapColor.SAND, Reference.MOD_ID, "sand_compr", 1.5f, 3.5f, null);
    public static final Block COMPRESSED_GRAVEL = new MetaCompressedBase(Material.GROUND, MapColor.GRAY, Reference.MOD_ID, "gravel_compr", 1.5f, 3.5f, null);
    public static final Block COMPRESSED_IRON = new MetaCompressedBase(Material.IRON, MapColor.IRON, Reference.MOD_ID, "iron_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_GOLD = new MetaCompressedBase(Material.IRON, MapColor.GOLD, Reference.MOD_ID, "gold_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_DIAMOND = new MetaCompressedBase(Material.IRON, MapColor.DIAMOND, Reference.MOD_ID, "diamond_compr", 2.5f, 12.0f, null);

    //TileEntity
    public static final Block COMPRESSED_FURNACE_IDLE = new BlockFurnaceDecoy("furnacecompr", 0, null, false);
    public static final Block COMPRESSED_FURNACE_ACTIVE = new BlockFurnaceDecoy("furnacecompr", 0.875f, null, true).setCreativeTab(null);
    public static final Block BEACON_XRAY = new BlockBeaconXRAY(Material.GLASS, MapColor.DIAMOND, Reference.MOD_ID, "beacon_xray", 3.0f, 13.5f, TFITab.blockTab);

    /*  If more data on a block is needed
     *  Example 1:
     *  public static final Block compressedCobbleWithData = new CompressedBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null).setLightLevel(0.8f);
     *  Using Ex1, you can easily add one or two things without having to make an extra class.
     *  Example 2:
     *  public static final Block compressedCobbleWithData = new CompressedCobbleBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
     *  Using Ex2, you extend the base class and add your extra data in the constructor of that class.
     */

	public static void init() {
		TFIBlocks.addBlock(POTATO_BLOCK);
        TFIBlocks.addBlock(HIGH_QUALITY_CAKE);
        TFIBlocks.addBlock(FALLTRAPBLOCK);
        TFIBlocks.addBlock(OVERLAYTEST);
        TFIBlocks.addBlock(SURPRISEBLOCK);
        TFIBlocks.addBlock(COLORBLOCK);
        TFIBlocks.addBlock(NETHER_STONE);
        TFIBlocks.addBlock(NETHER_STAR_BLOCK);
        TFIBlocks.addBlock(ENDGAMIUMBLOCK);
        TFIBlocks.addBlock(ENDGAMIUMBLOCKC);
        TFIBlocks.addBlock(BLACKHOLE);

        TFIBlocks.addBlock(COMPRESSED_FURNACE_IDLE);
        TFIBlocks.addBlock(COMPRESSED_FURNACE_ACTIVE);
        TFIBlocks.addBlock(BEACON_XRAY);

        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "potato_compr"), COMPRESSED_POTATO);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "cobble_compr"), COMPRESSED_COBBLESTONE);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "dirt_compr"), COMPRESSED_DIRT);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "sand_compr"), COMPRESSED_SAND);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "gravel_compr"), COMPRESSED_GRAVEL);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "iron_compr"), COMPRESSED_IRON);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "gold_compr"), COMPRESSED_GOLD);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "diamond_compr"), COMPRESSED_DIAMOND);
    }
	
	public static void renderInit() {
		BlockRenderer.registerBlock(POTATO_BLOCK);
        BlockRenderer.registerBlock(HIGH_QUALITY_CAKE);
        BlockRenderer.registerBlock(FALLTRAPBLOCK);
        BlockRenderer.registerBlock(OVERLAYTEST);
        BlockRenderer.registerBlock(SURPRISEBLOCK);
        BlockRenderer.registerBlock(COLORBLOCK);
        BlockRenderer.registerBlock(NETHER_STONE);
        BlockRenderer.registerBlock(NETHER_STAR_BLOCK);
        BlockRenderer.registerBlock(ENDGAMIUMBLOCK);
        BlockRenderer.registerBlock(ENDGAMIUMBLOCKC);
        BlockRenderer.registerBlock(BLACKHOLE);

        BlockRenderer.registerBlock(COMPRESSED_FURNACE_IDLE);
        BlockRenderer.registerBlock(COMPRESSED_FURNACE_ACTIVE);
        BlockRenderer.registerBlock(BEACON_XRAY);

        BlockRenderer.registerMetaBlock(COMPRESSED_POTATO, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_COBBLESTONE, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_DIRT, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_SAND, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GRAVEL, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_IRON, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GOLD, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_DIAMOND, MetaValues.COMPRESSED);
	}

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityFurnaceCompr.class, "furnacecompr");
        GameRegistry.registerTileEntity(TileEntityBeaconXray.class, "beaconxray");
    }
}
