package com.fabbe50.compressedblocks.core.content;

import com.brandon3055.brandonscore.handlers.ProcessHandler;
import com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 02/12/2017 - 11:27 AM.
 */
public class ExplodeDraconic {
    public static void explode(BlockPos pos, World world, EntityPlayer player) {
        ProcessExplosion explosion = new ProcessExplosion(pos, 350, world.getMinecraftServer().worldServerForDimension(player.dimension), 0);
        ProcessHandler.addProcess(explosion);
    }
}
