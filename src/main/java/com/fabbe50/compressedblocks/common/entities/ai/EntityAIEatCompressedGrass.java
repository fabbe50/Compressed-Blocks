package com.fabbe50.compressedblocks.common.entities.ai;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 25/11/2017 - 9:32 PM.
 */
public class EntityAIEatCompressedGrass extends EntityAIBase {
    private final EntityLiving grassEaterEntity;
    private final World entityWorld;
    int eatingGrassTimer;

    public EntityAIEatCompressedGrass(EntityLiving grassEaterEntityIn) {
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }

    public boolean shouldExecute() {
        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
            return this.entityWorld.getBlockState(blockpos.down()).getBlock() == BlockRegistry.COMPRESSED_GRASS;
        }
    }

    public void startExecuting() {
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
        this.grassEaterEntity.getNavigator().clearPath();
    }

    public void resetTask() {
        this.eatingGrassTimer = 0;
    }

    public boolean shouldContinueExecuting() {
        return this.eatingGrassTimer > 0;
    }

    public int getEatingGrassTimer() {
        return this.eatingGrassTimer;
    }

    @SuppressWarnings("deprecation")
    public void updateTask() {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4) {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
            BlockPos blockpos1 = blockpos.down();

            if (this.entityWorld.getBlockState(blockpos1).getBlock() == BlockRegistry.COMPRESSED_GRASS) {
                if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                    this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(BlockRegistry.COMPRESSED_GRASS));
                    this.entityWorld.setBlockState(blockpos1, BlockRegistry.COMPRESSED_GRASS_EATEN.getStateFromMeta(BlockRegistry.COMPRESSED_GRASS_EATEN.getMetaFromState(this.entityWorld.getBlockState(blockpos1))), 2);
                }

                this.grassEaterEntity.eatGrassBonus();
            }

        }
    }
}
