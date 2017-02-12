package com.fabbe50.compressedblocks.core.lib;

import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class EntityBlacklistStorage {
    public static List<Class<? extends Entity>> entityBlackList = new ArrayList<>();

    public static void init() {
        for (int i = 0; i < Configs.entityBlacklist.length; i++) {
            entityBlackList.add(i, EntityList.getClass(Configs.entityBlacklistR.get(i)));
            LogHelper.info(entityBlackList.get(i).getName());
        }
    }
}
