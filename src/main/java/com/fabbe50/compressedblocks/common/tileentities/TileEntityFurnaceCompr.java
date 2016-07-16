package com.fabbe50.compressedblocks.common.tileentities;

import com.fabbe50.compressedblocks.common.blocks.BlockFurnaceDecoy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by fabbe50 on 11/07/2016.
 */
public class TileEntityFurnaceCompr extends TileEntityLockable implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    private ItemStack[] tileItemStacks = new ItemStack[3];
    private int tileBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String tileCustomName;

    public int getSizeInventory() {
        return this.tileItemStacks.length;
    }

    @Nullable
    public ItemStack getStackInSlot(int index) {
        return this.tileItemStacks[index];
    }

    @Nullable
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.tileItemStacks, index, count);
    }

    @Nullable
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.tileItemStacks, index);
    }

    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(this.tileItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.tileItemStacks[index]);
        this.tileItemStacks[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        if (index == 0 && !flag) {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    public String getName() {
        return this.hasCustomName() ? this.tileCustomName : "container.tile";
    }

    public boolean hasCustomName() {
        return this.tileCustomName != null && !this.tileCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_) {
        this.tileCustomName = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.tileItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.tileItemStacks.length) {
                this.tileItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }

        this.tileBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.tileItemStacks[1]);

        if (compound.hasKey("CustomName", 8)) {
            this.tileCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.tileBurnTime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.tileItemStacks.length; ++i) {
            if (this.tileItemStacks[i] != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                this.tileItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.tileCustomName);
        }

        return compound;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning() {
        return this.tileBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

    public void update() {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning()) {
            --this.tileBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.isBurning() || this.tileItemStacks[1] != null && this.tileItemStacks[0] != null) {
                if (!this.isBurning() && this.canSmelt()) {
                    this.currentItemBurnTime = this.tileBurnTime = getItemBurnTime(this.tileItemStacks[1]);

                    if (this.isBurning()) {
                        flag1 = true;

                        if (this.tileItemStacks[1] != null) {
                            --this.tileItemStacks[1].stackSize;

                            if (this.tileItemStacks[1].stackSize == 0) {
                                this.tileItemStacks[1] = tileItemStacks[1].getItem().getContainerItem(tileItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.tileItemStacks[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                BlockFurnaceDecoy.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public int getCookTime(@Nullable ItemStack stack) {
        return 20;
    }

    private boolean canSmelt() {
        if (this.tileItemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.tileItemStacks[0]);
            if (itemstack == null) return false;
            if (this.tileItemStacks[2] == null) return true;
            if (!this.tileItemStacks[2].isItemEqual(itemstack)) return false;
            int result = tileItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.tileItemStacks[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.tileItemStacks[0]);

            if (this.tileItemStacks[2] == null) {
                this.tileItemStacks[2] = itemstack.copy();
            } else if (this.tileItemStacks[2].getItem() == itemstack.getItem()) {
                this.tileItemStacks[2].stackSize += itemstack.stackSize;
            }
            if (this.tileItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.SPONGE) && this.tileItemStacks[0].getMetadata() == 1 && this.tileItemStacks[1] != null && this.tileItemStacks[1].getItem() == Items.BUCKET) {
                this.tileItemStacks[1] = new ItemStack(Items.WATER_BUCKET);
            }

            --this.tileItemStacks[0].stackSize;

            if (this.tileItemStacks[0].stackSize <= 0) {
                this.tileItemStacks[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.WOODEN_SLAB) {
                    return 15;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD) {
                    return 30;
                }

                if (block == Blocks.COAL_BLOCK) {
                    return 1600;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 20;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 20;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 20;
            if (item == Items.STICK) return 10;
            if (item == Items.COAL) return 160;
            if (item == Items.LAVA_BUCKET) return 2000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 10;
            if (item == Items.BLAZE_ROD) return 240;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.tileItemStacks[1];
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (itemstack == null || itemstack.getItem() != Items.BUCKET);
        }
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }

        return true;
    }

    public String getGuiID() {
        return "minecraft:furnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFurnace(playerInventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.tileBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.tileBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount() {
        return 4;
    }

    public void clear() {
        for (int i = 0; i < this.tileItemStacks.length; ++i) {
            this.tileItemStacks[i] = null;
        }
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
