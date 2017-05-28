package com.fabbe50.compressedblocks.core.lib;

/**
 * Created by fabbe50 on 20/04/2017.
 */
public class CompressedTNTPositionalData {
    public static float[] singleCompressed = new float[] {-3.5f, 0, 3.5f};

    public static float[] doubleCompressed = new float[] {-7, -3.5f, 0, 3.5f, 7};

    public static float[] tripleCompressed = new float[] {-10.5f, -7, -3.5f, 0, 3.5f, 7, 10.5f};

    public static float[] getCompressed(int compresssion) {
        switch (compresssion) {
            case 1:
                return singleCompressed;
            case 2:
                return doubleCompressed;
            case 3:
                return tripleCompressed;
        }
        return null;
    }

    public static int getCompression(String registryName) {
        switch (registryName) {
            case "singlecompressedtnt":
                return 1;
            case "doublecompressedtnt":
                return 2;
            case "triplecompressedtnt":
                return 3;
        }
        return 0;
    }
}
