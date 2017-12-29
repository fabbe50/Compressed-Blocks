package com.fabbe50.compressedblocks.core.render;

import com.fabbe50.compressedblocks.common.blocks.BlockSuperShulkerBox;
import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 29/05/2017.
 */
@SideOnly(Side.CLIENT)
public class TileEntityItemStackRenderer extends net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer {
    private static final TileEntitySuperShulkerBox[] SHULKER_BOXES = new TileEntitySuperShulkerBox[16];

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        Item item = itemStackIn.getItem();
        //LogHelper.info("CB2: TileEntityRenderer; Pass");

        //LogHelper.info(item);
        if(Block.getBlockFromItem(item) instanceof BlockSuperShulkerBox) {
            //LogHelper.info("Before Shulker Render");
            TileEntityRendererDispatcher.instance.render(SHULKER_BOXES[BlockSuperShulkerBox.getColorFromItem(item).getMetadata()], 0.0D, 0.0D, 0.0D, 0.0F);
            //LogHelper.info("After Shulker Render");
        }

        super.renderByItem(itemStackIn);
    }

    static {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
            SHULKER_BOXES[enumdyecolor.getMetadata()] = new TileEntitySuperShulkerBox(enumdyecolor);
        }
    }
}
