package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.blocks.*;
import com.fabbe50.compressedblocks.common.blocks.base.BlockBeaconBase;
import com.fabbe50.compressedblocks.common.blocks.meta.MetaCompressedBase;
import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.common.tileentities.*;
import com.fabbe50.compressedblocks.core.model.ModelBakery;
import com.fabbe50.compressedblocks.core.reference.MetaValues;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.thefifthidiot.tficore.init.TFIBlocks;
import com.thefifthidiot.tficore.render.BlockRenderer;

import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

public class BlockRegistry {
    //Using TFICore, basic blocks do not need a block-class on it's own.
	public static final Block POTATO_BLOCK = new BlockBase(Material.CAKE, MapColor.YELLOW, "potatoBlock", 2.0f, 10.0f, null);
    public static final Block HIGH_QUALITY_CAKE = new BlockHQCake();
    public static final Block FALLTRAPBLOCK = new BlockDisappear(Material.ROCK, MapColor.GRASS, "falltrap", 0.7f, 1.2f, null);
    public static final Block OVERLAYTEST = new BlockOverlay(Material.ROCK, MapColor.GRAY, "overlayblock", 1.0f, 1.0f, null).setCreativeTab(null);
    public static final Block SURPRISEBLOCK = new BlockSurprise(Material.IRON, MapColor.YELLOW, "surpriseblock", 0.2f, 100f, null);
    public static final Block COLORBLOCK = new BlockMultiColored(Material.REDSTONE_LIGHT, null, "color_block", 1.3f, 6f, null);
    public static final Block NETHER_STONE = new BlockNetherStone(Material.ROCK, MapColor.PURPLE, "nether_stone", 2.0f, 10.0f, null);
    public static final Block NETHER_STAR_BLOCK = new BlockBeaconBase(Material.ROCK, MapColor.SNOW, "netherstarblock", 2.0f, 20f, null);
    public static final Block ENDGAMIUMBLOCK = new BlockEndgamium(Material.IRON, MapColor.DIAMOND, "endgamiumblock", 6.0f, 20f, null);
    public static final Block ENDGAMIUMBLOCKC = new BlockBeaconBase(Material.IRON, MapColor.DIAMOND, "endgamiumblockc", 8.0f, 30f, CBTab.compressedBlocksTab);
    public static final Block BLACKHOLE = new BlockBlackHole(Material.AIR, MapColor.BLACK, "blackhole", 50f, 50f, null).setCreativeTab(null);
    public static final Block SINGLECOMPRESSSEDTNT = new BlockCompressedTNT(Material.TNT, MapColor.RED, "singlecompressedtnt", 0.7f, 0.2f, CBTab.compressedBlocksTab);
    public static final Block DOUBLECOMPRESSSEDTNT = new BlockCompressedTNT(Material.TNT, MapColor.RED, "doublecompressedtnt", 0.7f, 0.2f, CBTab.compressedBlocksTab);
    public static final Block TRIPLECOMPRESSSEDTNT = new BlockCompressedTNT(Material.TNT, MapColor.RED, "triplecompressedtnt", 0.7f, 0.2f, CBTab.compressedBlocksTab);
    public static final Block MININGEXPLOSIVES = new BlockMiningExplosives(Material.TNT, MapColor.RED, "miningexplosives", 0.7f, 0.2f, null);
    public static final Block ONLINEDETECTOR = new BlockOnlineDetector(Material.REDSTONE_LIGHT, MapColor.BLUE, "onlinedetector", 1.3f, 6f, null).setCreativeTab(null);
    public static final Block ENDERBLOCK = new BlockEnder(Material.CAKE, MapColor.CYAN, "enderblock", 2.3f, 12.2f, null);
    public static final Block FUSIONPEDESTAL = new BlockFusionPedestal(Material.IRON, MapColor.BLUE, "fusionpedestal", 5.0f, 2000.0f, null);
    public static final Block NUKE = new BlockNuke(Material.TNT, MapColor.YELLOW, "nuke", 0.3f, 0.2f, null);

    //Compressed Blocks
    public static final Block COMPRESSED_POTATO = new MetaCompressedBase(Material.CAKE, MapColor.BROWN, Reference.MOD_ID, "potato_compr", 2.0f, 10.0f, null);
    public static final Block COMPRESSED_COBBLESTONE = new MetaCompressedBase(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
    public static final Block COMPRESSED_DIRT = new MetaCompressedBase(Material.GROUND, MapColor.BROWN, Reference.MOD_ID, "dirt_compr", 1.5f, 5.0f, null);
    public static final Block COMPRESSED_SAND = new MetaCompressedBase(Material.SAND, MapColor.SAND, Reference.MOD_ID, "sand_compr", 1.5f, 3.5f, null);
    public static final Block COMPRESSED_GRAVEL = new MetaCompressedBase(Material.GROUND, MapColor.GRAY, Reference.MOD_ID, "gravel_compr", 1.5f, 3.5f, null);
    public static final Block COMPRESSED_IRON = new MetaCompressedBase(Material.IRON, MapColor.IRON, Reference.MOD_ID, "iron_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_GOLD = new MetaCompressedBase(Material.IRON, MapColor.GOLD, Reference.MOD_ID, "gold_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_DIAMOND = new MetaCompressedBase(Material.IRON, MapColor.DIAMOND, Reference.MOD_ID, "diamond_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_EMERALD = new MetaCompressedBase(Material.IRON, MapColor.EMERALD, Reference.MOD_ID, "emerald_compr", 2.5f, 12.0f, null);
    public static final Block COMPRESSED_GRASS = new MetaCompressedBase(Material.GRASS, MapColor.GRASS, Reference.MOD_ID, "grass_compr", 1.6f, 4.0f, null);
    public static final Block COMPRESSED_GRASS_EATEN = new BlockCompressedGrass(Material.GROUND, MapColor.DIRT, Reference.MOD_ID, "grass_eaten_compr", 1.5f, 3.5f, null);

    //LightBlocks
    public static final Block LIGHTBLOCK = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block", 1.3f, 6f, null, 0);
    public static final Block LIGHTBLOCK1 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block1", 1.3f, 6f, null, 0.067f).setCreativeTab(null);
    public static final Block LIGHTBLOCK2 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block2", 1.3f, 6f, null, 0.13f).setCreativeTab(null);
    public static final Block LIGHTBLOCK3 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block3", 1.3f, 6f, null, 0.201f).setCreativeTab(null);
    public static final Block LIGHTBLOCK4 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block4", 1.3f, 6f, null, 0.268f).setCreativeTab(null);
    public static final Block LIGHTBLOCK5 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block5", 1.3f, 6f, null, 0.335f).setCreativeTab(null);
    public static final Block LIGHTBLOCK6 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block6", 1.3f, 6f, null, 0.402f).setCreativeTab(null);
    public static final Block LIGHTBLOCK7 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block7", 1.3f, 6f, null, 0.469f).setCreativeTab(null);
    public static final Block LIGHTBLOCK8 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block8", 1.3f, 6f, null, 0.536f).setCreativeTab(null);
    public static final Block LIGHTBLOCK9 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block9", 1.3f, 6f, null, 0.603f).setCreativeTab(null);
    public static final Block LIGHTBLOCK10 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block10", 1.3f, 6f, null, 0.67f).setCreativeTab(null);
    public static final Block LIGHTBLOCK11 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block11", 1.3f, 6f, null, 0.737f).setCreativeTab(null);
    public static final Block LIGHTBLOCK12 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block12", 1.3f, 6f, null, 0.804f).setCreativeTab(null);
    public static final Block LIGHTBLOCK13 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block13", 1.3f, 6f, null, 0.871f).setCreativeTab(null);
    public static final Block LIGHTBLOCK14 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block14", 1.3f, 6f, null, 0.938f).setCreativeTab(null);
    public static final Block LIGHTBLOCK15 = new BlockAdjustableLight(Material.REDSTONE_LIGHT, MapColor.SNOW, "light_block15", 1.3f, 6f, null, 1).setCreativeTab(null);

    //TileEntity
    public static final Block COMPRESSED_FURNACE_IDLE = new BlockFurnaceDecoy("furnacecompr", 0, null, false);
    public static final Block COMPRESSED_FURNACE_ACTIVE = new BlockFurnaceDecoy("furnacecompr", 0.875f, null, true).setCreativeTab(null);
    public static final Block BEACON_XRAY = new BlockBeaconXRAY(Material.GLASS, MapColor.DIAMOND, Reference.MOD_ID, "beacon_xray", 3.0f, 13.5f, TFITab.blockTab);
    public static final Block BREWER = new BlockBrewer().setRegistryName(Reference.MOD_ID, "brewer").setUnlocalizedName(Reference.MOD_ID + ":brewer").setHardness(0.5F).setLightLevel(0.125F);
    public static final Block WHITE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.WHITE, Material.ROCK, MapColor.SNOW, "white_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block ORANGE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.ORANGE, Material.ROCK, MapColor.ADOBE, "orange_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block MAGENTA_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.MAGENTA, Material.ROCK, MapColor.MAGENTA, "magenta_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block LIGHT_BLUE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.LIGHT_BLUE, Material.ROCK, MapColor.LIGHT_BLUE, "light_blue_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block YELLOW_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.YELLOW, Material.ROCK, MapColor.YELLOW, "yellow_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block LIME_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.LIME, Material.ROCK, MapColor.LIME, "lime_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block PINK_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.PINK, Material.ROCK, MapColor.PINK, "pink_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block GRAY_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.GRAY, Material.ROCK, MapColor.GRAY, "gray_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block SILVER_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.SILVER, Material.ROCK, MapColor.SILVER, "silver_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block CYAN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.CYAN, Material.ROCK, MapColor.CYAN, "cyan_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block PURPLE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.PURPLE, Material.ROCK, MapColor.PURPLE, "purple_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block BLUE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BLUE, Material.ROCK, MapColor.BLUE, "blue_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block BROWN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BROWN, Material.ROCK, MapColor.BROWN, "brown_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block GREEN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.GREEN, Material.ROCK, MapColor.GREEN, "green_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block RED_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.RED, Material.ROCK, MapColor.RED, "red_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block BLACK_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BLACK, Material.ROCK, MapColor.BLACK, "black_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(null);
    public static final Block DRAWBRIDGE = new BlockDrawbridge(Material.ROCK).setRegistryName(Reference.MOD_ID, "drawbridge").setUnlocalizedName(Reference.MOD_ID+":drawbridge").setHardness(2.0f).setResistance(10.0f).setCreativeTab(TFITab.blockTab);

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
        TFIBlocks.addBlock(SINGLECOMPRESSSEDTNT);
        TFIBlocks.addBlock(DOUBLECOMPRESSSEDTNT);
        TFIBlocks.addBlock(TRIPLECOMPRESSSEDTNT);
        TFIBlocks.addBlock(MININGEXPLOSIVES);
        TFIBlocks.addBlock(ONLINEDETECTOR);
        TFIBlocks.addBlock(ENDERBLOCK);
        TFIBlocks.addBlock(FUSIONPEDESTAL);
        TFIBlocks.addBlock(NUKE);

        TFIBlocks.addBlock(COMPRESSED_FURNACE_IDLE);
        TFIBlocks.addBlock(COMPRESSED_FURNACE_ACTIVE);
        TFIBlocks.addBlock(BEACON_XRAY);
        TFIBlocks.addBlock(BREWER);

        TFIBlocks.addBlock(LIGHTBLOCK);
        TFIBlocks.addBlock(LIGHTBLOCK1);
        TFIBlocks.addBlock(LIGHTBLOCK2);
        TFIBlocks.addBlock(LIGHTBLOCK3);
        TFIBlocks.addBlock(LIGHTBLOCK4);
        TFIBlocks.addBlock(LIGHTBLOCK5);
        TFIBlocks.addBlock(LIGHTBLOCK6);
        TFIBlocks.addBlock(LIGHTBLOCK7);
        TFIBlocks.addBlock(LIGHTBLOCK8);
        TFIBlocks.addBlock(LIGHTBLOCK9);
        TFIBlocks.addBlock(LIGHTBLOCK10);
        TFIBlocks.addBlock(LIGHTBLOCK11);
        TFIBlocks.addBlock(LIGHTBLOCK12);
        TFIBlocks.addBlock(LIGHTBLOCK13);
        TFIBlocks.addBlock(LIGHTBLOCK14);
        TFIBlocks.addBlock(LIGHTBLOCK15);

        /*registerItemBlock(WHITE_SHULKER_BOX, new ItemSuperShulkerBox(WHITE_SHULKER_BOX));
        registerItemBlock(ORANGE_SHULKER_BOX, new ItemSuperShulkerBox(ORANGE_SHULKER_BOX));
        registerItemBlock(MAGENTA_SHULKER_BOX, new ItemSuperShulkerBox(MAGENTA_SHULKER_BOX));
        registerItemBlock(LIGHT_BLUE_SHULKER_BOX, new ItemSuperShulkerBox(LIGHT_BLUE_SHULKER_BOX));
        registerItemBlock(YELLOW_SHULKER_BOX, new ItemSuperShulkerBox(YELLOW_SHULKER_BOX));
        registerItemBlock(LIME_SHULKER_BOX, new ItemSuperShulkerBox(LIME_SHULKER_BOX));
        registerItemBlock(PINK_SHULKER_BOX, new ItemSuperShulkerBox(PINK_SHULKER_BOX));
        registerItemBlock(GRAY_SHULKER_BOX, new ItemSuperShulkerBox(GRAY_SHULKER_BOX));
        registerItemBlock(SILVER_SHULKER_BOX, new ItemSuperShulkerBox(SILVER_SHULKER_BOX));
        registerItemBlock(CYAN_SHULKER_BOX, new ItemSuperShulkerBox(CYAN_SHULKER_BOX));
        registerItemBlock(PURPLE_SHULKER_BOX, new ItemSuperShulkerBox(PURPLE_SHULKER_BOX));
        registerItemBlock(BLUE_SHULKER_BOX, new ItemSuperShulkerBox(BLUE_SHULKER_BOX));
        registerItemBlock(BROWN_SHULKER_BOX, new ItemSuperShulkerBox(BROWN_SHULKER_BOX));
        registerItemBlock(GREEN_SHULKER_BOX, new ItemSuperShulkerBox(GREEN_SHULKER_BOX));
        registerItemBlock(RED_SHULKER_BOX, new ItemSuperShulkerBox(RED_SHULKER_BOX));
        registerItemBlock(BLACK_SHULKER_BOX, new ItemSuperShulkerBox(BLACK_SHULKER_BOX));*/

        TFIBlocks.addBlock(WHITE_SHULKER_BOX);

        TFIBlocks.addBlock(DRAWBRIDGE);

        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "potato_compr"), COMPRESSED_POTATO);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "cobble_compr"), COMPRESSED_COBBLESTONE);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "dirt_compr"), COMPRESSED_DIRT);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "sand_compr"), COMPRESSED_SAND);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "gravel_compr"), COMPRESSED_GRAVEL);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "iron_compr"), COMPRESSED_IRON);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "gold_compr"), COMPRESSED_GOLD);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "diamond_compr"), COMPRESSED_DIAMOND);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "emerald_compr"), COMPRESSED_EMERALD);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "grass_compr"), COMPRESSED_GRASS);
        TFIBlocks.registerMetaBlock(new ResourceLocation(Reference.MOD_ID, "grass_eaten_compr"), COMPRESSED_GRASS_EATEN);
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
        BlockRenderer.registerBlock(SINGLECOMPRESSSEDTNT);
        BlockRenderer.registerBlock(DOUBLECOMPRESSSEDTNT);
        BlockRenderer.registerBlock(TRIPLECOMPRESSSEDTNT);
        BlockRenderer.registerBlock(MININGEXPLOSIVES);
        BlockRenderer.registerBlock(ONLINEDETECTOR);
        BlockRenderer.registerBlock(ENDERBLOCK);
        BlockRenderer.registerBlock(FUSIONPEDESTAL);
        BlockRenderer.registerBlock(NUKE);

        BlockRenderer.registerBlock(COMPRESSED_FURNACE_IDLE);
        BlockRenderer.registerBlock(COMPRESSED_FURNACE_ACTIVE);
        BlockRenderer.registerBlock(BEACON_XRAY);
        BlockRenderer.registerBlock(BREWER);
        BlockRenderer.registerBlock(DRAWBRIDGE);

        BlockRenderer.registerBlock(LIGHTBLOCK);
        BlockRenderer.registerBlock(LIGHTBLOCK1);
        BlockRenderer.registerBlock(LIGHTBLOCK2);
        BlockRenderer.registerBlock(LIGHTBLOCK3);
        BlockRenderer.registerBlock(LIGHTBLOCK4);
        BlockRenderer.registerBlock(LIGHTBLOCK5);
        BlockRenderer.registerBlock(LIGHTBLOCK6);
        BlockRenderer.registerBlock(LIGHTBLOCK7);
        BlockRenderer.registerBlock(LIGHTBLOCK8);
        BlockRenderer.registerBlock(LIGHTBLOCK9);
        BlockRenderer.registerBlock(LIGHTBLOCK10);
        BlockRenderer.registerBlock(LIGHTBLOCK11);
        BlockRenderer.registerBlock(LIGHTBLOCK12);
        BlockRenderer.registerBlock(LIGHTBLOCK13);
        BlockRenderer.registerBlock(LIGHTBLOCK14);
        BlockRenderer.registerBlock(LIGHTBLOCK15);

        //registerItemBlockRenderer(WHITE_SHULKER_BOX, "supershulkerbox", true);
        /*TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(ORANGE_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(MAGENTA_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(LIGHT_BLUE_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(YELLOW_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(LIME_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(PINK_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(GRAY_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(SILVER_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(CYAN_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(PURPLE_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(BLUE_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(BROWN_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(GREEN_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(RED_SHULKER_BOX));
        TFIBlocks.registerItemBlockRenderer(Item.getItemFromBlock(BLACK_SHULKER_BOX));*/

        ModelBakery.registerBlockModel(WHITE_SHULKER_BOX, 0, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(WHITE_SHULKER_BOX), 0, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "super_shulker_box_white"), "inventory"));

        //registerItemBlockRenderer(WHITE_SHULKER_BOX, "supershulkerbox", true);
        /*registerItemBlockRenderer(Item.getItemFromBlock(ORANGE_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(MAGENTA_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(LIGHT_BLUE_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(YELLOW_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(LIME_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(PINK_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(GRAY_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(SILVER_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(CYAN_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(PURPLE_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(BLUE_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(BROWN_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(GREEN_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(RED_SHULKER_BOX), "supershulkerbox");
        registerItemBlockRenderer(Item.getItemFromBlock(BLACK_SHULKER_BOX), "supershulkerbox");*/
        
        BlockRenderer.registerMetaBlock(COMPRESSED_POTATO, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_COBBLESTONE, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_DIRT, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_SAND, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GRAVEL, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_IRON, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GOLD, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_DIAMOND, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_EMERALD, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GRASS, MetaValues.COMPRESSED);
        BlockRenderer.registerMetaBlock(COMPRESSED_GRASS_EATEN, MetaValues.COMPRESSED);
	}

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityFurnaceCompr.class, "furnacecompr");
        GameRegistry.registerTileEntity(TileEntityBeaconXray.class, "beaconxray");
        GameRegistry.registerTileEntity(TileEntityBrewer.class, "brewer");
        GameRegistry.registerTileEntity(TileEntitySuperShulkerBox.class, "supershulkerbox");
        GameRegistry.registerTileEntity(TileEntityDrawbridge.class, "drawbridge");
    }

    //INFO: MOVED TO TFICORE
    private static final Map<Block, Item> BLOCK_TO_ITEM = net.minecraftforge.fml.common.registry.GameData.getBlockItemMap();

    private static void registerItemBlock(Block blockIn) {
        registerItemBlock(blockIn, new ItemBlock(blockIn));
    }

    private static void registerItemBlock(Block blockIn, Item itemIn) {
        GameRegistry.register(blockIn);
        registerItem(Block.getIdFromBlock(blockIn), (ResourceLocation)Block.REGISTRY.getNameForObject(blockIn), itemIn);
        BLOCK_TO_ITEM.put(blockIn, itemIn);
    }

    private static void registerItem(int id, String textualID, Item itemIn) {
        registerItem(id, new ResourceLocation(textualID), itemIn);
    }

    private static void registerItem(int id, ResourceLocation textualID, Item itemIn) {
        Item.REGISTRY.register(id, textualID, itemIn);
    }

    private static void registerItemBlockRenderer(Block block, String name, boolean builtin) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, name), "inventory"));
        if (builtin) Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(block);
        LogHelper.info("Registered renderdata for item with registry-name: " + new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, name), "inventory"));
    }
}
