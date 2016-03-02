package com.fabbe50.compressedblocks.entities.renders;

import com.fabbe50.compressedblocks.entities.EnumCorgiTypes;
import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import com.fabbe50.compressedblocks.lib.DataCorgi;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public class RenderCorgi extends RenderLiving{
    private ResourceLocation corgiPath = new ResourceLocation(Textures.RESOURCE_PATH_ENTITY + "/corgi/");

    //Corgis
    //private ResourceLocation fallBackWolfTexture = new ResourceLocation(corgiPath + "corgi_wolf.png");
    private ResourceLocation corgiTextures = new ResourceLocation(corgiPath + "corgi_normal.png");
    private ResourceLocation superCorgi = new ResourceLocation(corgiPath + "corgi_super.png");

    //Util Textures
    private ResourceLocation tamedCorgiTextures = new ResourceLocation(corgiPath + "corgi_tame.png");
    private ResourceLocation anrgyCorgiTextures = new ResourceLocation(corgiPath + "corgi_angry.png");
    private ResourceLocation corgiCollarTextures = new ResourceLocation(corgiPath + "corgi_collar.png");

    public RenderCorgi(ModelBase p_i1269_1_, ModelBase p_i1269_2_, float p_i1269_3_) {
        super(p_i1269_1_, p_i1269_3_);
        this.setRenderPassModel(p_i1269_2_);
    }

    /**
     *Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityCorgi p_77044_1_, float p_77044_2_) {
        return p_77044_1_.getTailRotation();
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityCorgi p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_2_ == 0 && p_77032_1_.getCorgiShaking()) {
            float f1 = p_77032_1_.getBrightness(p_77032_3_) * p_77032_1_.getShadingWhileShaking(p_77032_3_);
            this.bindTexture(getCorgiTexture(p_77032_1_));
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (p_77032_2_ == 1 && p_77032_1_.isTamed()) {
            this.bindTexture(corgiCollarTextures);
            int j = p_77032_1_.getCollarColor();
            GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else {
            return -1;
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCorgi p_110775_1_) {
        return p_110775_1_.isTamed() ? getCorgiTexture((EntityCorgi) p_110775_1_) : (p_110775_1_.isAngry() ? getCorgiTexture((EntityCorgi) p_110775_1_) : getCorgiTexture((EntityCorgi) p_110775_1_));
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((EntityCorgi)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
        return this.handleRotationFloat((EntityCorgi)p_77044_1_, p_77044_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return this.getEntityTexture((EntityCorgi)p_110775_1_);
    }

    protected ResourceLocation getCorgiTexture(EntityCorgi corgi)  {
        switch (corgi.getCorgiType())
        {
            case 0:
                return corgiTextures;
            case 1:
                return superCorgi;
            default:
                return corgiTextures;
        }
    }
}
