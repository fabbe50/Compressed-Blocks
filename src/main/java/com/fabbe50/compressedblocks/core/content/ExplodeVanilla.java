package com.fabbe50.compressedblocks.core.content;

import com.fabbe50.compressedblocks.common.entities.EntityCompressedTNT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 02/12/2017 - 11:27 AM.
 */
public class ExplodeVanilla {
    public static float[] position = new float[] {-15, -10.5f, -7, -3.5f, 0, 3.5f, 7, 10.5f, 15};

    public static void explode(BlockPos pos, World world, EntityPlayer player) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int l = 0; l < 3; l++) {
                    EntityCompressedTNT entityTNT = new EntityCompressedTNT(world, (double) ((float) pos.getX() + 0.5F + position[i]), (double) pos.getY() + (l * -5), (double) ((float) pos.getZ() + 0.5F + position[j]), player, 9);
                    world.spawnEntity(entityTNT);
                }
            }
        }
    }
}
