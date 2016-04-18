package com.fabbe50.compressedblocks.utility;

import java.io.File;

/**
 * Created by fabbe50 on 06/04/2016.
 */
public class GetFilesFromDir {
    public static int getFileAmount(String dir, int i) {
        try {
            int files = new File(dir).listFiles().length;
            if (files > 1000) {
                LogHelper.warn("[" + dir + "] Return value is over 1000, returns the first 1000");
                return 1000;
            } else {
                LogHelper.info("[" + dir + "] Returning value: " + files);
                return files;
            }
        }
        catch (Exception e) { LogHelper.error(e); return i; }
    }
}
