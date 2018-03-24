package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.blocks.*;
import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.common.blocks.base.BlockBeaconBase;
import com.fabbe50.compressedblocks.common.blocks.meta.MetaCompressedBase;
import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.common.items.base.ItemBlockVariants;
import com.fabbe50.compressedblocks.common.tileentities.*;
import com.fabbe50.compressedblocks.core.lib.EnumCompressed;
import com.fabbe50.compressedblocks.core.reference.Reference;

import com.fabbe50.compressedblocks.development.common.block.BlockWorkbenchTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

public class BlockRegistry {
    //NonRenderList - Used for builtin blocks that have their own rendering code.
    private static List<Block> nonRenderBlocks = new ArrayList<>();

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
    public static final Block FUSEROCK = new BlockFuseRock(Material.WOOD, MapColor.GRAY, "fuserock", 2.0f, 10.0f, null);
    public static final Block FUSEROCKNETHER = new BlockFuseRock(Material.WOOD, MapColor.GRAY, "fuserocknether", 2.0f, 10.0f, null);
    public static final Block FUSEROCKEND = new BlockFuseRock(Material.WOOD, MapColor.GRAY, "fuserockend", 2.0f, 10.0f, null);
    public static final Block MININGEXPLOSIVES = new BlockMiningExplosives(Material.TNT, MapColor.RED, "miningexplosives", 0.7f, 0.2f, null);
    public static final Block ONLINEDETECTOR = new BlockOnlineDetector(Material.REDSTONE_LIGHT, MapColor.BLUE, "onlinedetector", 1.3f, 6f, null).setCreativeTab(null);
    public static final Block ENDERBLOCK = new BlockEnder(Material.CAKE, MapColor.CYAN, "enderblock", 2.3f, 12.2f, null);
    public static final Block FUSIONPEDESTAL = new BlockFusionPedestal(Material.IRON, MapColor.BLUE, "fusionpedestal", 5.0f, 2000.0f, null);
    public static final Block NUKE = new BlockNuke(Material.TNT, MapColor.YELLOW, "nuke", 0.3f, 0.2f, null);
    public static final Block BIN = new BlockMagicalWashingBin(Material.ROCK, MapColor.GRAY, "bin", 2.3f, 6f, null);

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
    public static final Block COMPRESSED_FURNACE_IDLE = new BlockFurnaceCompr("furnacecompr", 0, null, false);
    public static final Block COMPRESSED_FURNACE_ACTIVE = new BlockFurnaceCompr("furnacecompr", 0.875f, null, true).setCreativeTab(null);
    public static final Block BEACON_XRAY = new BlockBeaconXRAY(Material.GLASS, MapColor.DIAMOND, Reference.MOD_ID, "beacon_xray", 3.0f, 13.5f, CBTab.blockTab);
    public static final Block BREWER = new BlockBrewer().setRegistryName(Reference.MOD_ID, "brewer").setUnlocalizedName(Reference.MOD_ID + ":brewer").setHardness(0.5F).setLightLevel(0.125F);
    public static final Block WHITE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.WHITE, Material.ROCK, MapColor.SNOW, "white_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block ORANGE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.ORANGE, Material.ROCK, MapColor.ADOBE, "orange_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block MAGENTA_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.MAGENTA, Material.ROCK, MapColor.MAGENTA, "magenta_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block LIGHT_BLUE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.LIGHT_BLUE, Material.ROCK, MapColor.LIGHT_BLUE, "light_blue_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block YELLOW_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.YELLOW, Material.ROCK, MapColor.YELLOW, "yellow_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block LIME_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.LIME, Material.ROCK, MapColor.LIME, "lime_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block PINK_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.PINK, Material.ROCK, MapColor.PINK, "pink_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block GRAY_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.GRAY, Material.ROCK, MapColor.GRAY, "gray_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block SILVER_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.SILVER, Material.ROCK, MapColor.SILVER, "silver_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block CYAN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.CYAN, Material.ROCK, MapColor.CYAN, "cyan_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block PURPLE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.PURPLE, Material.ROCK, MapColor.PURPLE, "purple_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block BLUE_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BLUE, Material.ROCK, MapColor.BLUE, "blue_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block BROWN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BROWN, Material.ROCK, MapColor.BROWN, "brown_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block GREEN_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.GREEN, Material.ROCK, MapColor.GREEN, "green_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block RED_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.RED, Material.ROCK, MapColor.RED, "red_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block BLACK_SHULKER_BOX = new BlockSuperShulkerBox(EnumDyeColor.BLACK, Material.ROCK, MapColor.BLACK, "black_super_shulker_box", 2.0f, 10.0f, null).setCreativeTab(CBTab.compressedBlocksTab);
    public static final Block DRAWBRIDGE = new BlockDrawbridge(Material.ROCK).setRegistryName(Reference.MOD_ID, "drawbridge").setUnlocalizedName(Reference.MOD_ID+":drawbridge").setHardness(2.0f).setResistance(10.0f).setCreativeTab(CBTab.blockTab);
    public static final Block CHUNK_SCANNER = new BlockChunkScanner(Material.TNT).setRegistryName(Reference.MOD_ID, "chunkscanner").setUnlocalizedName(Reference.MOD_ID+":chunkscanner").setHardness(2.0f).setResistance(10.0f).setCreativeTab(CBTab.blockTab);

    //Test blocks
    public static final Block WORKBENCH_TEST = new BlockWorkbenchTest();

    /*  If more data on a block is needed
     *  Example 1:
     *  public static final Block compressedCobbleWithData = new CompressedBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null).setLightLevel(0.8f);
     *  Using Ex1, you can easily add one or two things without having to make an extra class.
     *  Example 2:
     *  public static final Block compressedCobbleWithData = new CompressedCobbleBlock(Material.ROCK, MapColor.GRAY, Reference.MOD_ID, "cobble_compr", 2.0f, 10.0f, null);
     *  Using Ex2, you extend the base class and add your extra data in the constructor of that class.
     */

	public static void init() {
		registerBlock(POTATO_BLOCK);
        registerBlock(HIGH_QUALITY_CAKE);
        registerBlock(FALLTRAPBLOCK);
        registerBlock(OVERLAYTEST);
        registerBlock(SURPRISEBLOCK);
        registerBlock(COLORBLOCK);
        registerBlock(NETHER_STONE);
        registerBlock(NETHER_STAR_BLOCK);
        registerBlock(ENDGAMIUMBLOCK);
        registerBlock(ENDGAMIUMBLOCKC);
        registerBlock(BLACKHOLE);
        registerBlock(SINGLECOMPRESSSEDTNT);
        registerBlock(DOUBLECOMPRESSSEDTNT);
        registerBlock(TRIPLECOMPRESSSEDTNT);
        registerBlock(FUSEROCK);
        registerBlock(FUSEROCKNETHER);
        registerBlock(FUSEROCKEND);
        registerBlock(MININGEXPLOSIVES);
        registerBlock(ONLINEDETECTOR);
        registerBlock(ENDERBLOCK);
        registerBlock(FUSIONPEDESTAL);
        registerBlock(NUKE);
        registerBlock(BIN);

        registerBlock(COMPRESSED_FURNACE_IDLE);
        registerBlock(COMPRESSED_FURNACE_ACTIVE);
        registerBlock(BEACON_XRAY);
        registerBlock(BREWER);
        registerBlock(DRAWBRIDGE);
        registerBlock(CHUNK_SCANNER);

        registerBlock(LIGHTBLOCK);
        registerBlock(LIGHTBLOCK1);
        registerBlock(LIGHTBLOCK2);
        registerBlock(LIGHTBLOCK3);
        registerBlock(LIGHTBLOCK4);
        registerBlock(LIGHTBLOCK5);
        registerBlock(LIGHTBLOCK6);
        registerBlock(LIGHTBLOCK7);
        registerBlock(LIGHTBLOCK8);
        registerBlock(LIGHTBLOCK9);
        registerBlock(LIGHTBLOCK10);
        registerBlock(LIGHTBLOCK11);
        registerBlock(LIGHTBLOCK12);
        registerBlock(LIGHTBLOCK13);
        registerBlock(LIGHTBLOCK14);
        registerBlock(LIGHTBLOCK15);

        registerBlock(WHITE_SHULKER_BOX);
        registerBlock(ORANGE_SHULKER_BOX);
        registerBlock(MAGENTA_SHULKER_BOX);
        registerBlock(LIGHT_BLUE_SHULKER_BOX);
        registerBlock(YELLOW_SHULKER_BOX);
        registerBlock(LIME_SHULKER_BOX);
        registerBlock(PINK_SHULKER_BOX);
        registerBlock(GRAY_SHULKER_BOX);
        registerBlock(SILVER_SHULKER_BOX);
        registerBlock(CYAN_SHULKER_BOX);
        registerBlock(BLUE_SHULKER_BOX);
        registerBlock(BROWN_SHULKER_BOX);
        registerBlock(GREEN_SHULKER_BOX);
        registerBlock(RED_SHULKER_BOX);
        registerBlock(BLACK_SHULKER_BOX);

        registerBlock(COMPRESSED_POTATO, new ItemBlockVariants(COMPRESSED_POTATO));
        registerBlock(COMPRESSED_COBBLESTONE, new ItemBlockVariants(COMPRESSED_COBBLESTONE));
        registerBlock(COMPRESSED_DIRT, new ItemBlockVariants(COMPRESSED_DIRT));
        registerBlock(COMPRESSED_GRASS, new ItemBlockVariants(COMPRESSED_GRASS));
        registerBlock(COMPRESSED_GRASS_EATEN, new ItemBlockVariants(COMPRESSED_GRASS_EATEN));
        registerBlock(COMPRESSED_SAND, new ItemBlockVariants(COMPRESSED_SAND));
        registerBlock(COMPRESSED_GRAVEL, new ItemBlockVariants(COMPRESSED_GRAVEL));
        registerBlock(COMPRESSED_IRON, new ItemBlockVariants(COMPRESSED_IRON));
        registerBlock(COMPRESSED_GOLD, new ItemBlockVariants(COMPRESSED_GOLD));
        registerBlock(COMPRESSED_DIAMOND, new ItemBlockVariants(COMPRESSED_DIAMOND));
        registerBlock(COMPRESSED_EMERALD, new ItemBlockVariants(COMPRESSED_EMERALD));

        if ((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
            //Register all debug blocks here, these will only show up in a development environment!
            registerBlock(WORKBENCH_TEST);
        }
    }
	
	public static void renderInit() {
        registerBuiltIn(WHITE_SHULKER_BOX);
        registerBuiltIn(ORANGE_SHULKER_BOX);
        registerBuiltIn(MAGENTA_SHULKER_BOX);
        registerBuiltIn(LIGHT_BLUE_SHULKER_BOX);
        registerBuiltIn(YELLOW_SHULKER_BOX);
        registerBuiltIn(LIME_SHULKER_BOX);
        registerBuiltIn(PINK_SHULKER_BOX);
        registerBuiltIn(GRAY_SHULKER_BOX);
        registerBuiltIn(SILVER_SHULKER_BOX);
        registerBuiltIn(CYAN_SHULKER_BOX);
        registerBuiltIn(PURPLE_SHULKER_BOX);
        registerBuiltIn(PURPLE_SHULKER_BOX);
        registerBuiltIn(BLUE_SHULKER_BOX);
        registerBuiltIn(GREEN_SHULKER_BOX);
        registerBuiltIn(RED_SHULKER_BOX);
        registerBuiltIn(BLACK_SHULKER_BOX);
	}

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityFurnaceCompr.class, "furnacecompr");
        GameRegistry.registerTileEntity(TileEntityBeaconXray.class, "beaconxray");
        //GameRegistry.registerTileEntity(TileEntityBrewer.class, "brewer");
        GameRegistry.registerTileEntity(TileEntitySuperShulkerBox.class, "supershulkerbox");
        //GameRegistry.registerTileEntity(TileEntityDrawbridge.class, "drawbridge");
        GameRegistry.registerTileEntity(TileEntityChunkScanner.class, "chunkscanner");
    }

    private static void registerBuiltIn(Block block) {
        nonRenderBlocks.add(block);
    }

    public static void registerBlock(Block block) {
        BlockRegistrationHandler.blocks.add(block);
    }

    public static void registerBlock(Block block, ItemBlock itemBlock) {
        ForgeRegistries.BLOCKS.register(block);
        itemBlock.setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(itemBlock);
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class BlockRegistrationHandler {
        static final Set<Block> BLOCK_LIST = new HashSet<>();
        static final Set<Item> ITEM_LIST = new HashSet<>();

        private static final Set<Block> registeredBlockList = new HashSet<>();
        public static final List<Block> blocks = new ArrayList<>();

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> reg = event.getRegistry();

            for (final Block block : blocks) {
                reg.register(block);
                BLOCK_LIST.add(block);
            }
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> reg = event.getRegistry();

            for (final Block item : blocks) {
                ItemBlock itemBlock = (ItemBlock) new ItemBlock(item).setRegistryName(Objects.requireNonNull(item.getRegistryName()));
                if (Block.getBlockFromItem(itemBlock) instanceof BlockSuperShulkerBox)
                    itemBlock.setMaxStackSize(1);
                reg.register(itemBlock);
                ITEM_LIST.add(itemBlock);
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (int i = 0; i < EnumCompressed.values().length; i++) {
                registerBlockModelVariants(BlockRegistry.COMPRESSED_POTATO, i, "potato_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_COBBLESTONE, i, "cobble_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_DIRT, i, "dirt_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_GRASS, i, "grass_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_GRASS_EATEN, i, "dirt_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_SAND, i, "sand_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_GRAVEL, i, "gravel_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_IRON, i, "iron_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_GOLD, i, "gold_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_DIAMOND, i, "diamond_compr_" + EnumCompressed.byMetadata(i).getName());
                registerBlockModelVariants(BlockRegistry.COMPRESSED_EMERALD, i, "emerald_compr_" + EnumCompressed.byMetadata(i).getName());
            }

            registerBlockModel(BlockRegistry.WHITE_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=white"));
            registerBlockModel(BlockRegistry.ORANGE_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=orange"));
            registerBlockModel(BlockRegistry.MAGENTA_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=magenta"));
            registerBlockModel(BlockRegistry.LIGHT_BLUE_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=light_blue"));
            registerBlockModel(BlockRegistry.YELLOW_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=yellow"));
            registerBlockModel(BlockRegistry.LIME_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=lime"));
            registerBlockModel(BlockRegistry.PINK_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=pink"));
            registerBlockModel(BlockRegistry.GRAY_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=gray"));
            registerBlockModel(BlockRegistry.SILVER_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=silver"));
            registerBlockModel(BlockRegistry.CYAN_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=cyan"));
            registerBlockModel(BlockRegistry.PURPLE_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=purple"));
            registerBlockModel(BlockRegistry.BLUE_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=blue"));
            registerBlockModel(BlockRegistry.BROWN_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=brown"));
            registerBlockModel(BlockRegistry.GREEN_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=green"));
            registerBlockModel(BlockRegistry.RED_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=red"));
            registerBlockModel(BlockRegistry.BLACK_SHULKER_BOX, new ModelResourceLocation(Reference.MOD_ID + ":supershulkerbox", "type=black"));

            for (Block block : BLOCK_LIST)
                if (!registeredBlockList.contains(block) && !BlockRegistry.nonRenderBlocks.contains(block))
                    registerBlockModel(block);
        }

        private static void registerBlockModel(final Block block) {
            final String registryName = Objects.requireNonNull(block.getRegistryName()).toString();
            final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            registerBlockModel(block, location);
        }

        private static void registerBlockModel(final Block block, final ModelResourceLocation modelResourceLocation) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
            registeredBlockList.add(block);
        }

        static void registerBlockModelVariants(Block block, int meta, String filename) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, filename), "inventory"));
        }
    }
}
