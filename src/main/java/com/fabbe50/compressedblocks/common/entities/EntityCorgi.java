package com.fabbe50.compressedblocks.common.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 19/06/2016.
 */
public class EntityCorgi extends EntityTameable {
    public EntityCorgi(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}
