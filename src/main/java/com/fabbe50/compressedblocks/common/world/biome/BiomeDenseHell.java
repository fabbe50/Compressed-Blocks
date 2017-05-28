package com.fabbe50.compressedblocks.common.world.biome;

import com.fabbe50.compressedblocks.common.world.biome.decorators.BiomeDenseHellDecorator;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import java.util.Random;

/**
 * Created by fabbe50 on 13/01/2017.
 */
public class BiomeDenseHell extends Biome {
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState NETHERSTONE = BlockRegistry.NETHER_STONE.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    protected static final IBlockState LAVA = Blocks.LAVA.getDefaultState();
    protected static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    protected static final IBlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();

    /** Holds the noise used to determine whether slowsand can be generated at a location */
    private double[] buffer;

    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] depthBuffer = new double[256];

    public BiomeDenseHell(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 75, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 100, 16, 16));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 8, 6, 6));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 8, 6, 6));
        this.decorator = new BiomeDenseHellDecorator();
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        rand = new Random(worldIn.getSeed());

        /* Determines whether slowsand or gravel can be generated at a location */
        NoiseGeneratorOctaves slowsandGravelNoiseGen = new NoiseGeneratorOctaves(rand, 4);
        /* Determines whether something other than netherrack can be generated at a location */
        NoiseGeneratorOctaves netherrackExclusivityNoiseGen = new NoiseGeneratorOctaves(rand, 4);

        int i = worldIn.getSeaLevel() + 1;
        double d0 = 0.03125D;
        this.slowsandNoise = slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, x * 16, z * 16, 0, 16, 16, 1, 0.03125D, 0.03125D, 1.0D);
        this.gravelNoise = slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, x * 16, 109, z * 16, 16, 1, 16, 0.03125D, 1.0D, 0.03125D);
        this.depthBuffer = netherrackExclusivityNoiseGen.generateNoiseOctaves(this.depthBuffer, x * 16, z * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);

        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                boolean flag = this.slowsandNoise[j + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[j + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
                int l = (int)(this.depthBuffer[j + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
                int i1 = -1;
                IBlockState iblockstate = NETHERSTONE;
                IBlockState iblockstate1 = NETHERSTONE;

                for (int j1 = 127; j1 >= 0; --j1) {
                    if (j1 < 127 - rand.nextInt(5) && j1 > rand.nextInt(5)) {
                        IBlockState iblockstate2 = primer.getBlockState(k, j1, j);

                        if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR) {
                            if (iblockstate2.getBlock() == BlockRegistry.NETHER_STONE) {
                                if (i1 == -1) {
                                    if (l <= 0) {
                                        iblockstate = AIR;
                                        iblockstate1 = NETHERSTONE;
                                    }
                                    else if (j1 >= i - 4 && j1 <= i + 1) {
                                        iblockstate = NETHERSTONE;
                                        iblockstate1 = NETHERSTONE;

                                        if (flag1) {
                                            iblockstate = GRAVEL;
                                            iblockstate1 = NETHERSTONE;
                                        }

                                        if (flag) {
                                            iblockstate = SOUL_SAND;
                                            iblockstate1 = SOUL_SAND;
                                        }
                                    }

                                    if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                                        iblockstate = LAVA;
                                    }

                                    i1 = l;

                                    if (j1 >= i - 1) {
                                        primer.setBlockState(k, j1, j, iblockstate);
                                    }
                                    else {
                                        primer.setBlockState(k, j1, j, iblockstate1);
                                    }
                                }
                                else if (i1 > 0) {
                                    --i1;
                                    primer.setBlockState(k, j1, j, iblockstate1);
                                }
                            }
                        }
                        else {
                            i1 = -1;
                        }
                    }
                    else {
                        primer.setBlockState(k, j1, j, BEDROCK);
                    }
                }
            }
        }
    }
}
