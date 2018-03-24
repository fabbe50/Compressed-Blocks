package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityChunkScanner;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.BlockHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by fabbe on 11/12/2017 - 12:19 PM.
 */
public class BlockChunkScanner extends BlockContainer implements ITileEntityProvider {
    public static List<Block> blocks = new ArrayList<>();

    public BlockChunkScanner(Material materialIn) {
        super(materialIn);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChunkScanner();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int xx = (pos.getX() % 16);
        int zz = (pos.getZ() % 16);
        int x2;
        int z2;

        if (zz == 0 && pos.getZ() < 0)
            z2 = pos.getZ() - zz + 16;
        else
            z2 = pos.getZ() - zz;

        if (xx == 0 && pos.getX() < 0)
            x2 = pos.getX() - xx + 16;
        else
            x2 = pos.getX() - xx;

        if (z2 < 0)
            z2--;
        if (x2 < 0)
            x2--;

        BlockPos bPos = new BlockPos(x2, 0, z2);
        blocks = new ArrayList<>();

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 255; y++) {
                for (int z = 0; z < 16; z++) {
                    if (worldIn.getBlockState(bPos).getBlock() != Blocks.BEDROCK && worldIn.getBlockState(bPos).getBlock() != BlockRegistry.CHUNK_SCANNER) {
                        if (BlockHelper.isSourceBlock(worldIn, bPos) || !(worldIn.getBlockState(bPos).getBlock() instanceof BlockStaticLiquid))
                            blocks.add(worldIn.getBlockState(bPos).getBlock());
                    }
                    if (x2 > 0 && z2 > 0)
                        bPos = new BlockPos(x2 + x, y, z2 + z);
                    else if (x2 > 0 && z2 < 0)
                        bPos = new BlockPos(x2 + x, y, z2 - z);
                    else if (x2 < 0 && z2 > 0)
                        bPos = new BlockPos(x2 - x, y, z2 + z);
                    else if (x2 < 0 && z2 < 0)
                        bPos = new BlockPos(x2 - x, y, z2 - z);
                }
            }
        }

        if (worldIn.isRemote) {
            playerIn.openGui(CompressedBlocks.instance, Reference.GUI_CHUNKSCANNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    public static class GuiChunkScanner extends GuiScreen {
        Map<Block, Integer> blockIntegerMap = new LinkedHashMap<>();

        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            this.drawDefaultBackground();
            super.drawScreen(mouseX, mouseY, partialTicks);
            GlStateManager.pushMatrix();
            TextureManager manager = mc.getTextureManager();
            manager.bindTexture(new ResourceLocation("textures/gui/demo_background.png"));
            GlStateManager.disableLighting();
            drawTexturedModalRect((float)(width / 2 - 125), (float)(height / 2 - 100), 0, 0, 250, 200);
            drawString(mc.fontRenderer, "Chunk Scanner", (width / 2 - 125 + 8), (height / 2 - 100 + 8), Color.WHITE.getRGB());
            GlStateManager.enableLighting();
            int i = 0;
            int j = 0;
            RenderHelper.disableStandardItemLighting();
            RenderHelper.enableGUIStandardItemLighting();
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.setRenderShadow(false);
            for (Block block : blockIntegerMap.keySet()) {
                if (!(block instanceof BlockAir)) {
                    if (j < 4) {
                        ItemStack stack = new ItemStack(block);
                        if (block instanceof BlockStaticLiquid) {
                            if (block == Blocks.WATER)
                                stack = new ItemStack(Items.WATER_BUCKET);
                            else if (block == Blocks.LAVA)
                                stack = new ItemStack(Items.LAVA_BUCKET);
                        }
                        this.itemRender.zLevel = 1;
                        this.itemRender.renderItemAndEffectIntoGUI(this.mc.player, stack, (width / 2 - 125 + 8) + (j * 62), (height / 2 - 100 + 24) + (i * 17));
                        drawString(mc.fontRenderer, "" + blockIntegerMap.get(block), (width / 2 - 125 + 28) + (j * 62), (height / 2 - 100 + 28) + (i * 17), Color.WHITE.getRGB());
                        i++;
                        if (i == 8) {
                            j++;
                            i = 0;
                        }
                    }
                }
            }
            rendermanager.setRenderShadow(true);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.popMatrix();
        }

        @Override
        public boolean doesGuiPauseGame() {
            return false;
        }

        @Override
        protected void renderToolTip(ItemStack stack, int x, int y) {
            super.renderToolTip(stack, x, y);
        }

        @Override
        public void initGui() {
            super.initGui();

            for (Block block : blocks) {
                blockIntegerMap.merge(block, 1, (a, b) -> a + b);
            }

            blockIntegerMap = sortByValues(blockIntegerMap);
        }

        private static Map<Block, Integer> sortByValues(Map<Block, Integer> map) {
            List list = new LinkedList(map.entrySet());
            list.sort((o2, o1) -> ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue()));
            Map sortedHashMap = new LinkedHashMap();
            for (Object aList : list) {
                Map.Entry entry = (Map.Entry) aList;
                sortedHashMap.put(entry.getKey(), entry.getValue());
            }
            return sortedHashMap;
        }
    }
}
