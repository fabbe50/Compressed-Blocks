package com.fabbe50.compressedblocks.common.tileentities;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;

/**
 * Created by fabbe50 on 20/04/2016.
 */
public class TileEntityBlazeGen extends TileEntity implements ISidedInventory, IEnergyConnection {
    private String localizedName;

    public static HashMap<String, Double> config_double = new HashMap();

    public static int facing = 2;

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_side = new int[]{1};

    private ItemStack[] slots = new ItemStack[3];

    public int burnSpeed;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public void setGuiDisplayName(String displayName) {
        this.localizedName = displayName;
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.localizedName : "container.blazegen";
    }

    public boolean hasCustomInventoryName() {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    public void openInventory() {}
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return getItemBurnTime(itemstack) > 0;
    }

    public void inputRotation(double rotation, int side)
    {
        if(side!=ForgeDirection.OPPOSITES[facing])
            return;
        int output = (int) (getDouble("energy_output") * rotation);
        for(int i=0; i<6; i++)
        {
            ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[i];
            TileEntity te = worldObj.getTileEntity(xCoord+fd.offsetX, yCoord+fd.offsetY, zCoord+fd.offsetZ);
            if(te instanceof IEnergyReceiver)
            {
                IEnergyReceiver ier = (IEnergyReceiver)te;
                output -= ier.receiveEnergy(fd.getOpposite(), output, false);
            }
        }
    }

    private static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }else {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (item == Items.coal) return 800;
                if (block == Blocks.coal_block) return 14400;

                return GameRegistry.getFuelValue(itemstack);
            }
        }
        return 0;
    }

    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_side);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    public static void setDouble(String key, double d)
    {
        config_double.put(key, d);
    }
    public static double getDouble(String key)
    {
        Double d = config_double.get(key);
        return d!=null?d.floatValue():0;
    }

    public void readCustomNBT(NBTTagCompound nbt, boolean descPacket)
    {
        facing = nbt.getInteger("facing");
        if(descPacket && worldObj!=null)
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    public void writeCustomNBT(NBTTagCompound nbt, boolean descPacket)
    {
        nbt.setInteger("facing", facing);
    }
}
