package com.fabbe50.compressedblocks.entities.models;

/**
 * Reformated to fit needs by fabbe50 - (Originally made by Mojang (ModelWolf.java))
 */

import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelCorgi extends ModelBase
{
    /** main box for the corgi head */
    public ModelRenderer corgiHeadMain;
    /** The corgi's body */
    public ModelRenderer corgiBody;
    /** Corgi'se first leg */
    public ModelRenderer corgiLeg1;
    /** Corgi's second leg */
    public ModelRenderer corgiLeg2;
    /** Corgi's third leg */
    public ModelRenderer corgiLeg3;
    /** Corgi's fourth leg */
    public ModelRenderer corgiLeg4;
    /** The corgi's tail */
    ModelRenderer corgiTail;
    /** The corgi's mane */
    ModelRenderer corgiMane;
    private static final String __OBFID = "CL_00000868";

    public ModelCorgi()
    {
        float f = 0.0F;
        float f1 = 13.5F;
        this.corgiHeadMain = new ModelRenderer(this, 0, 0);
        this.corgiHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, f);
        this.corgiHeadMain.setRotationPoint(-1.0F, f1, -7.0F);
        this.corgiBody = new ModelRenderer(this, 18, 14);
        this.corgiBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, f);
        this.corgiBody.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.corgiMane = new ModelRenderer(this, 21, 0);
        this.corgiMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, f);
        this.corgiMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.corgiLeg1 = new ModelRenderer(this, 0, 18);
        this.corgiLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.corgiLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.corgiLeg2 = new ModelRenderer(this, 0, 18);
        this.corgiLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.corgiLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.corgiLeg3 = new ModelRenderer(this, 0, 18);
        this.corgiLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.corgiLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.corgiLeg4 = new ModelRenderer(this, 0, 18);
        this.corgiLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.corgiLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.corgiTail = new ModelRenderer(this, 9, 18);
        this.corgiTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.corgiTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.corgiHeadMain.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.corgiHeadMain.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.corgiHeadMain.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
            this.corgiHeadMain.renderWithRotation(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.corgiBody.render(p_78088_7_);
            this.corgiLeg1.render(p_78088_7_);
            this.corgiLeg2.render(p_78088_7_);
            this.corgiLeg3.render(p_78088_7_);
            this.corgiLeg4.render(p_78088_7_);
            this.corgiTail.renderWithRotation(p_78088_7_);
            this.corgiMane.render(p_78088_7_);
            GL11.glPopMatrix();
        }
        else
        {
            this.corgiHeadMain.renderWithRotation(p_78088_7_);
            this.corgiBody.render(p_78088_7_);
            this.corgiLeg1.render(p_78088_7_);
            this.corgiLeg2.render(p_78088_7_);
            this.corgiLeg3.render(p_78088_7_);
            this.corgiLeg4.render(p_78088_7_);
            this.corgiTail.renderWithRotation(p_78088_7_);
            this.corgiMane.render(p_78088_7_);
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
    {
        EntityCorgi entitycorgi = (EntityCorgi)p_78086_1_;

        if (entitycorgi.isAngry())
        {
            this.corgiTail.rotateAngleY = 0.0F;
        }
        else
        {
            this.corgiTail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }

        if (entitycorgi.isSitting())
        {
            this.corgiMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.corgiMane.rotateAngleX = ((float)Math.PI * 2F / 5F);
            this.corgiMane.rotateAngleY = 0.0F;
            this.corgiBody.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.corgiBody.rotateAngleX = ((float)Math.PI / 4F);
            this.corgiTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.corgiLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.corgiLeg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.corgiLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.corgiLeg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.corgiLeg3.rotateAngleX = 5.811947F;
            this.corgiLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.corgiLeg4.rotateAngleX = 5.811947F;
            this.corgiLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else
        {
            this.corgiBody.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.corgiBody.rotateAngleX = ((float)Math.PI / 2F);
            this.corgiMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.corgiMane.rotateAngleX = this.corgiBody.rotateAngleX;
            this.corgiTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.corgiLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.corgiLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.corgiLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.corgiLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.corgiLeg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
            this.corgiLeg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.corgiLeg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.corgiLeg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }

        this.corgiHeadMain.rotateAngleZ = entitycorgi.getInterestedAngle(p_78086_4_) + entitycorgi.getShakeAngle(p_78086_4_, 0.0F);
        this.corgiMane.rotateAngleZ = entitycorgi.getShakeAngle(p_78086_4_, -0.08F);
        this.corgiBody.rotateAngleZ = entitycorgi.getShakeAngle(p_78086_4_, -0.16F);
        this.corgiTail.rotateAngleZ = entitycorgi.getShakeAngle(p_78086_4_, -0.2F);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.corgiHeadMain.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.corgiHeadMain.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.corgiTail.rotateAngleX = p_78087_3_;
    }
}