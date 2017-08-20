package com.fabbe50.compressedblocks.core.model;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.render.RenderSuperShulkerBox;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 24/07/2017 - 4:39 PM.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class ModelBakery {
    private static final ModelResourceLocation MODEL_RESOURCE_WHITE_SUPER_SHULKER_BOX = new ModelResourceLocation(BlockRegistry.WHITE_SHULKER_BOX.getRegistryName(), "inventory");

    public static void init() {
    }

    public static void registerBlockModel(Block block, int meta, String path, String variant) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID + ":" + path, variant));
    }

    public static void registerBlockModel(Block block, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
    }

    public static void registerBlockModel(Block block, int meta, IStringSerializable variant) {
        registerBlockModel(block, meta, "variant=" + variant.getName());
    }

    public static void registerItemModel(Item item, int meta, String path) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + path, "inventory"));
    }

    public static void registerItemModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {}

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        IBakedModel whiteShulkerBox = event.getModelRegistry().getObject(MODEL_RESOURCE_WHITE_SUPER_SHULKER_BOX);

        event.getModelRegistry().putObject(MODEL_RESOURCE_WHITE_SUPER_SHULKER_BOX, new RenderSuperShulkerBox(whiteShulkerBox));
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event) {}

    @SubscribeEvent
    public static void dumpAtlas(ArrowLooseEvent event) {}
}
