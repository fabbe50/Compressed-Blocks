package com.fabbe50.compressedblocks.common.entities;

import com.fabbe50.compressedblocks.core.registry.LootTableRegistry;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fabbe on 28/05/2017.
 */
public class EntitySquidColored extends EntitySquid {
    private static final DataParameter<Byte> SQUIDCOLOR = EntityDataManager.<Byte>createKey(EntitySquidColored.class, DataSerializers.BYTE);

    public EntitySquidColored(World worldIn) {
        super(worldIn);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SQUIDCOLOR, (byte) 0);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        switch (this.getSquidColor()) {
            case WHITE:
            default:
                return LootTableRegistry.ENTITIES_SQUID_WHITE;
            case ORANGE:
                return LootTableRegistry.ENTITIES_SQUID_ORANGE;
            case MAGENTA:
                return LootTableRegistry.ENTITIES_SQUID_MAGENTA;
            case LIGHT_BLUE:
                return LootTableRegistry.ENTITIES_SQUID_LIGHT_BLUE;
            case YELLOW:
                return LootTableRegistry.ENTITIES_SQUID_YELLOW;
            case LIME:
                return LootTableRegistry.ENTITIES_SQUID_LIME;
            case PINK:
                return LootTableRegistry.ENTITIES_SQUID_PINK;
            case GRAY:
                return LootTableRegistry.ENTITIES_SQUID_GRAY;
            case SILVER:
                return LootTableRegistry.ENTITIES_SQUID_SILVER;
            case CYAN:
                return LootTableRegistry.ENTITIES_SQUID_CYAN;
            case PURPLE:
                return LootTableRegistry.ENTITIES_SQUID_PURPLE;
            case BLUE:
                return LootTableRegistry.ENTITIES_SQUID_BLUE;
            case BROWN:
                return LootTableRegistry.ENTITIES_SQUID_BROWN;
            case GREEN:
                return LootTableRegistry.ENTITIES_SQUID_GREEN;
            case RED:
                return LootTableRegistry.ENTITIES_SQUID_RED;
            case BLACK:
                return LootTableRegistry.ENTITIES_SQUID_BLACK;
        }
    }

    private EnumDyeColor getSquidColor() {
        return EnumDyeColor.byMetadata((Byte) this.dataManager.get(SQUIDCOLOR) & 15);
    }

    private void setSquidColor(EnumDyeColor color) {
        byte b0 = (Byte) this.dataManager.get(SQUIDCOLOR);
        this.dataManager.set(SQUIDCOLOR, (byte) (b0 & 240 | color.getMetadata() & 15));
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("Color", (byte)this.getSquidColor().getMetadata());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSquidColor(EnumDyeColor.byMetadata(compound.getByte("Color")));
    }

    private static EnumDyeColor getRandomSquidColor(Random random) {
        return EnumDyeColor.byDyeDamage(random.nextInt(16));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setSquidColor(getRandomSquidColor(this.world.rand));
        return livingdata;
    }

}
