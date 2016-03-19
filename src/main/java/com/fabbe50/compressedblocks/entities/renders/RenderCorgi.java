package com.fabbe50.compressedblocks.entities.renders;

import com.fabbe50.compressedblocks.entities.EnumCorgiTypes;
import com.fabbe50.compressedblocks.entities.registry.RegistryCorgi;
import com.fabbe50.compressedblocks.entities.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by fabbe50 on 24/02/2016.
 */
@SideOnly(Side.CLIENT)
public class RenderCorgi extends RenderLiving{
    private ResourceLocation corgiPath = new ResourceLocation(Textures.RESOURCE_PATH_ENTITY + "corgi/");

    EnumCorgiTypes corgis;

    //Corgis
    private ResourceLocation corgiNormal = new ResourceLocation(corgiPath + "corgi_normal.png");
    private ResourceLocation corgiSuper = new ResourceLocation(corgiPath + "corgi_super.png");
    private ResourceLocation corgiSpy = new ResourceLocation(corgiPath + "corgi_spy.png");
    private ResourceLocation corgiSun = new ResourceLocation(corgiPath + "corgi_sunglasses.png");
    private ResourceLocation corgiSuit = new ResourceLocation(corgiPath + "corgi_suit.png");
    private ResourceLocation corgiPirate = new ResourceLocation(corgiPath + "corgi_pirate.png");
    private ResourceLocation corgiMelon = new ResourceLocation(corgiPath + "corgi_melon.png");
    private ResourceLocation corgiIron = new ResourceLocation(corgiPath + "corgi_ironman.png");
    private ResourceLocation corgiFabbe50 = new ResourceLocation(corgiPath + "corgi_fabbe50.png");
    private ResourceLocation corgiZirlian = new ResourceLocation(corgiPath + "corgi_zirlian.png");

    //Util Textures
    private ResourceLocation corgiCollarTextures = new ResourceLocation(corgiPath + "corgi_collar.png");

    public RenderCorgi(ModelBase model, ModelBase model2, float f) {
        super(model, f);
        this.setRenderPassModel(model2);
    }

    /**
     *Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityCorgi corgi, float f) {
        return corgi.getTailRotation();
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityCorgi p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_2_ == 0 && p_77032_1_.getCorgiShaking()) {
            float f1 = p_77032_1_.getBrightness(p_77032_3_) * p_77032_1_.getShadingWhileShaking(p_77032_3_);
            this.bindTexture(getEntityTexture(p_77032_1_));
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
    protected ResourceLocation getEntityTexture(EntityCorgi corgi) {
        try {
            switch (corgi.getCorgiType()) {
                case 0:
                    return corgiNormal;
                case 1:
                    return corgiSuper;
                case 2:
                    return corgiSpy;
                case 3:
                    return corgiSun;
                case 4:
                    return corgiSuit;
                case 5:
                    return corgiPirate;
                case 6:
                    return corgiMelon;
                case 7:
                    return corgiIron;
                case 8:
                    return corgiFabbe50;
                case 9:
                    return corgiZirlian;
                default:
                    return corgiNormal;
            }
        }
        catch (Exception e) {
            LogHelper.error(e);
            return corgiNormal;
        }
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
}
