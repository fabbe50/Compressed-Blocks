package com.fabbe50.compressedblocks.common.tileentities;

import com.fabbe50.compressedblocks.core.network.PacketBase;
import com.fabbe50.compressedblocks.core.network.PacketHandler;
import com.fabbe50.compressedblocks.core.network.PacketTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.jar.Pack200;

/**
 * Created by fabbe50 on 11/10/2016.
 */
public abstract class TileBase extends TileEntity {
    @Override
    public void onChunkUnload() {
        if (!tileEntityInvalid) {
            invalidate();
        }
    }

    public abstract String getName();

    public abstract int getType();

    public void blockBroken() {

    }

    public void blockDismantled() {
        blockBroken();
    }

    public void blockPlaced() {

    }

    public void markChunkDirty() {
        world.markChunkDirty(pos, this);
    }

    public void callNeighborBlockChange() {
        world.notifyNeighborsOfStateChange(pos, getBlockType(), true);
    }

    public void callNeighborTileChange(BlockPos pos) {

    }

    public int getComparatorInput(int side) {
        return 0;
    }

    public int getLightValue() {
        return 0;
    }

    public boolean canPlayerAccess(EntityPlayer player) {
        return true;
    }

    public boolean canPlayerDismantle(EntityPlayer player) {
        return true;
    }

    public boolean isUsable(EntityPlayer player) {
        return player.getDistanceSq(pos) <= 64D && world.getTileEntity(pos) == this;
    }

    public boolean onWrench(EntityPlayer player, int hitSide) {
        return false;
    }

    protected final boolean timeCheck() {
        return world.getTotalWorldTime() % 32 == 0;
    }

    protected final boolean timeCheckEighth() {
        return world.getTotalWorldTime() % 4 == 0;
    }

    public Packet getDescriptionPacket() {
        return PacketHandler.toMCPacket(getPacket());
    }

    public PacketBase getPacket() {
        return new PacketTile(this);
    }
}
