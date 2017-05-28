package com.fabbe50.compressedblocks.core.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by fabbe on 13/05/2017.
 */
public class ClassTransformer implements IClassTransformer {
    private static final String ASM_HOOKS = "com/fabbe50/compressedblocks/core/asm/ASMHooks";

    public static final ClassnameMap CLASS_MAPPINGS = new ClassnameMap(

    );

    private static final Map<String, Transformer> transformers = new HashMap();

    static {

    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(transformers.containsKey(transformedName))
            return transformers.get(transformedName).apply(basicClass);

        return basicClass;
    }

    private static byte[] transformBucketItem(byte[] basicClass) {


        return basicClass;
    }

    private static interface Transformer extends Function<byte[], byte[]> { }
}
