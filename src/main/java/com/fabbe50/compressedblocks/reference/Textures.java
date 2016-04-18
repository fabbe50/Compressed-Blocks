package com.fabbe50.compressedblocks.reference;

import com.fabbe50.compressedblocks.entities.EnumCorgiTypes;
import com.fabbe50.compressedblocks.entities.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.utility.GetFilesFromDir;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import net.minecraft.util.ResourceLocation;
import scala.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class Textures {
    //Classes

    //Start
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
    public static final ResourceLocation RESOURCE_PATH_ENTITY = new ResourceLocation(RESOURCE_PREFIX + "textures/entity/");
    public static final ResourceLocation corgiPath = new ResourceLocation(RESOURCE_PATH_ENTITY + "tamable/corgi/corgi_");
    public static final ResourceLocation CORGI_FOLDER = new ResourceLocation(RESOURCE_PATH_ENTITY + "tamable/corgi/");
    public static final String corgiDir = "compressedblocks/entities/corgi";
    public static final String corgiDirTextures = corgiDir + "/textures";
    public static final String corgiDirAbilityConfig = corgiDir + "/config";
    public static final int safeCorgiAmount = 10 - 1;
    public static int corgiAmount;

    private static ResourceLocation[] modCorgis;
    public static ResourceLocation[] corgiTextures;
    public static String[] externalCorgiTextures;

    private static EnumCorgiTypes[] corgitypes;
    private static String[] textureNames;

    public static void initCorgiTexture () {

        corgiAmount = GetFilesFromDir.getFileAmount(corgiDir, safeCorgiAmount) + EnumCorgiTypes.count();
        corgiTextures = new ResourceLocation[corgiAmount];
        textureNames = new String[corgiAmount];
        LogHelper.info("[Textures.java] Corgi-amount: " + corgiAmount);

        getEnumCorgiTypeInstance();

        getCorgiTexturesFromMod();
        /*for (int i = 0; i < EnumCorgiTypes.count() - 1; i++) {
            corgiTextures[i] = modCorgis[i];
        }*/
        System.arraycopy(modCorgis, 0, corgiTextures, 0, modCorgis.length);

        getCorgiTextureNames();
        for (int i = EnumCorgiTypes.count() + 1; i < corgiAmount; i++) {
            //externalCorgiTextures[i] = corgiDirTextures + "/" + textureNames[i];
        }
    }

    private static void getCorgiTextureNames() {
        File dir = new File(corgiDir);
        File textureDir = new File(corgiDirTextures);
        File configDir = new File(corgiDirAbilityConfig);
        File[] files = dir.listFiles();
        try {
            if (dir.exists()) {
                for (int i = 0; i < files.length; i++) {
                    textureNames[i] = files[i].getName();
                    LogHelper.info("Found Corgi-texture: " + textureNames[i]);
                }
            }
            else {
                LogHelper.warn("Couldn't find the \"compressedblocks/entities/corgi\" directory!");
                if (dir.mkdirs()) {
                    LogHelper.info("Created \"compressedblocks/entities/corgi\" directory.");
                    if (textureDir.mkdir()) { LogHelper.info("Created \"compressedblocks/entities/corgi/textures\" directory."); }
                    if (configDir.mkdir()) { LogHelper.info("Created \"compressedblocks/entities/corgi/config\" directory."); }
                }
                else {
                    LogHelper.warn("Failed to create \"compressedblocks/entities/corgi\" directory. If you intend to use this, you might wanna create it yourself.");
                }
            }
        }
        catch (Exception e) {
            LogHelper.error(e);
        }
    }

    private static void getCorgiTexturesFromMod () {
        modCorgis = new ResourceLocation[EnumCorgiTypes.count()];
        for (int i = 0; i <= EnumCorgiTypes.count() - 1; i++) {
            modCorgis[i] = new ResourceLocation(corgitypes[i].getResourceLocation(i));
        }
    }

    private static void getEnumCorgiTypeInstance() {
        try {
            corgitypes = EnumCorgiTypes.values();
        }
        catch (Exception e) {
            LogHelper.error(e);
        }
    }
}
