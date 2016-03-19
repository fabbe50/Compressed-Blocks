package com.fabbe50.compressedblocks.entities.registry;

import com.fabbe50.compressedblocks.entities.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import java.util.*;

/**
 * Created by fabbe50 on 03/03/2016.
 */
public class RegistryCorgi {
    private static final RegistryCorgi INSTANCE = new RegistryCorgi();

    private List<Integer> newCorgiIds = Lists.newArrayList();
    @SideOnly(Side.CLIENT)
    private Map<Integer, ResourceLocation> newCorgis;

    public static RegistryCorgi instance()
    {
        return INSTANCE;
    }

    public void registerCorgiId(int id)
    {
        if (newCorgiIds.contains(id))
        {
            LogHelper.fatal("Attempt to register duplicate corgi id: " + id);
            throw new RuntimeException();
        }
        newCorgiIds.add(id);
    }

    @SideOnly(Side.CLIENT)
    public void registerCorgiSkin(int corgiId, ResourceLocation corgiSkin)
    {
        if (newCorgis == null)
        {
            newCorgis = Maps.newHashMap();
        }
        newCorgis.put(corgiId, corgiSkin);
    }

    @SideOnly(Side.CLIENT)
    public static ResourceLocation getCorgiSkin(int corgiType, ResourceLocation defaultSkin)
    {
        if (instance().newCorgis != null && instance().newCorgis.containsKey(corgiType))
        {
            return instance().newCorgis.get(corgiType);
        }
        return defaultSkin;
    }

    public static Collection<Integer> getRegisteredCorgis()
    {
        return Collections.unmodifiableCollection(instance().newCorgiIds);
    }

    public static void applyRandomType(EntityCorgi corgi, Random rand)
    {
        //corgi.setCorgiType(rand.nextInt(1));
    }
}
