package com.fabbe50.compressedblocks.item.tools;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.lib.EnumModToolMaterial;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.fabbe50.compressedblocks.utility.MathHelper;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ItemModSword extends ItemCB {
    private float totalDamage;                                          //Damage it deals
    private float mobDamage;                                            //Damage value when hitting mobs
    private float playerDamage;                                         //Damage value when hitting players
    private float extraDamage = MathHelper.endgamiumextradamage;        //Gets the damage value from the config
    private float cooldown = 0;                                         //Cool down for the particle effect (WILL CRASH GAME IF REMOVED AND PARTICLES GETS SPAMMED)
    private final EnumModToolMaterial swordMaterial;                    //Material Holder
    EntityPlayer player;
    Random rand = new Random();
    World world;

    public ItemModSword(EnumModToolMaterial mat) { //Constructor
        this.swordMaterial = mat;                                       //Apply material
        this.maxStackSize = 1;                                          //Sets so you can only have 1 sword per stack
        this.setMaxDamage(mat.getMaxUses());                            //Sets the durability
        this.playerDamage = 4.0F + mat.getDamageVsEntity();             //Sets damage value against players
        this.mobDamage = 4.0F + mat.getDamageVsEntity() + extraDamage;  //Sets damage value against mobs
        setUnlocalizedName(mat.name() + "sword");                       //Sets the unlocalized name
        modSwordAttributes();                                           //Calls class where I can add sword attributes that are special for that specific sword
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) { //Tooltip text adder
        list.add(ColorHelper.magenta + "Nothing is more fabulous than a rainbow sword!");
        list.add(ColorHelper.blue + "This sword obliterates mobs, hurts players and SOMETIMES love animals.");
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if(cooldown > 0){
            cooldown--;
        }
        else if(cooldown <= 0){
            cooldown = 0; /*This is a precaution, just in case cooldown were to drop below 0 for some reason*/
        }
        getAttackDamageValues();
    }

    public void getAttackDamageValues () {
        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition objectMouseOver = mc.objectMouseOver;
        if (objectMouseOver != null && ConfigurationHandler.endgamiumextradamage != 0 && swordMaterial == EnumModToolMaterial.endgamium) {
            try {
                if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                    if (objectMouseOver.entityHit instanceof EntityPlayer) {
                        this.totalDamage = this.playerDamage;
                    } else if (objectMouseOver.entityHit instanceof EntityMob && !(objectMouseOver.entityHit instanceof EntityWither) ||
                            objectMouseOver.entityHit instanceof EntityWaterMob && !(objectMouseOver.entityHit instanceof EntityWither)) {
                        this.totalDamage = this.mobDamage;
                    } else if (objectMouseOver.entityHit instanceof EntityAnimal) {
                        this.totalDamage = -1000;
                    } else if (objectMouseOver.entityHit instanceof EntityWither) {
                        this.totalDamage = 100;
                    }
                }
            }
            catch (Exception e) {
                LogHelper.error(e);
                this.totalDamage = this.mobDamage;
            }
        }
        else {this.totalDamage = this.playerDamage;}
    }

    public float getDamage() {
        return this.swordMaterial.getDamageVsEntity();
    }

    public float getAttackedBlock(ItemStack itemStack, Block block) {
        if (block == Blocks.web) {
            return 15.0F;
        }
        else {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }

    public boolean hitEntity(ItemStack itemStack, EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
        itemStack.damageItem(1, livingEntity2);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int posX, int posY, int posZ, EntityLivingBase livingEntity)
    {
        if ((double)block.getBlockHardness(world, posX, posY, posZ) != 0.0D)
        {
            itemStack.damageItem(2, livingEntity);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;
    }

    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (cooldown == 0) {
            for (int i = 0; i <= 10; i++) {
                //world.spawnParticle("reddust", player.posX + (rand.nextDouble() - 0.5D) * (double)player.width, player.posY + rand.nextDouble() * (double)player.height - (double)player.yOffset, player.posZ + (rand.nextDouble() - 0.5D) * (double)player.width, 0.0D, 0.0D, 0.0D);
                CompressedBlocks.proxy.registerParticles(player);
            }

            cooldown = 60;
        }

        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    public boolean isWeb(Block block)
    {
        return block == Blocks.web;
    }

    public int getItemEnchantability()
    {
        return this.swordMaterial.getEnchantability();
    }

    public String getToolMaterialName()
    {
        return this.swordMaterial.toString();
    }

    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        ItemStack mat = swordMaterial.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
        return super.getIsRepairable(p_82789_1_, p_82789_2_);
    }

    public Multimap getItemAttributeModifiers()
    {
        getAttackDamageValues();
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.totalDamage, 0));
        return multimap;
    }

    public void modSwordAttributes () {
        //switch (swordMaterial) {
        //    case endgamium:
                //Stuff
        //}
    }
}
