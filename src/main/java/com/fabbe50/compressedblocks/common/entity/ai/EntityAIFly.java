package com.fabbe50.compressedblocks.common.entity.ai;

import com.fabbe50.compressedblocks.common.entity.tamables.corgis.EntityCorgi;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 14/03/2016.
 */
public class EntityAIFly extends EntityAIBase {
    World worldObj;
    EntityCorgi corgi;

    public EntityAIFly (EntityCorgi corgi){
        this.corgi = corgi;
        this.worldObj = corgi.worldObj;
    }

    @Override
    public boolean shouldExecute() {
        if (!corgi.isSitting() && corgi.isTamed())
            return true;
        else
            return false;
    }

    public void startExecuting() {
        //FlyAround.flying();
    }
}
