package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public class FluidHelper {
    public static void writeFluidStackToPacket(FluidStack fluid, DataOutput data) throws IOException {
        if (!isValidFluidStack(fluid)) {
            data.writeShort(-1);
        }
        else {
            CompressedStreamTools.write(fluid.writeToNBT(new NBTTagCompound()), data);
        }
    }

    public static FluidStack readFluidStackFromPacket(DataInput data) throws IOException {
        short length = data.readShort();
        if (length < 0) {
            return null;
        }
        else {
            byte[] byteData = new byte[length];
            data.readFully(byteData);
            return FluidStack.loadFluidStackFromNBT(CompressedStreamTools.read(data, new NBTSizeTracker(2097152L)));
        }
    }

    public static boolean isValidFluidStack(FluidStack fluid) {
        return fluid != null && FluidRegistry.getFluidName(fluid) != null;
    }
}
