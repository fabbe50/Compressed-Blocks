package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MultiblockFountaineChecker {

	public static boolean hasAroundFluids(World world, int x, int y, int z, FluidRegistry fluidRegistered){
		
		Map<String, Fluid> fluid = fluidRegistered.getRegisteredFluids();
		
		if(world.getBlock(x+1, y, z) == fluid){
			if(world.getBlock(x-1, y, z) == fluid){
				if(world.getBlock(x, y, z+1) == fluid){
					if(world.getBlock(x, y, z-1) == fluid){
						if(world.getBlock(x+1, y, z+1) == fluid){
							if(world.getBlock(x+1, y, z-1) == fluid){
								if(world.getBlock(x-1, y, z+1) == fluid){
									if(world.getBlock(x-1, y, z-1) == fluid){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean hasAroundFluidsID(World world, int x, int y, int z, FluidRegistry fluidRegistered){
		
		Map<String, Integer> fluidIDs = fluidRegistered.getRegisteredFluidIDs();
		
		if(world.getBlock(x+1, y, z) == fluidIDs){
			if(world.getBlock(x-1, y, z) == fluidIDs){
				if(world.getBlock(x, y, z+1) == fluidIDs){
					if(world.getBlock(x, y, z-1) == fluidIDs){
						if(world.getBlock(x+1, y, z+1) == fluidIDs){
							if(world.getBlock(x+1, y, z-1) == fluidIDs){
								if(world.getBlock(x-1, y, z+1) == fluidIDs){
									if(world.getBlock(x-1, y, z-1) == fluidIDs){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean hasAroundFluids(World world, int x, int y, int z,Map<String, Fluid> registeredFluids) {
		if(world.getBlock(x+1, y, z) == registeredFluids){
			if(world.getBlock(x-1, y, z) == registeredFluids){
				if(world.getBlock(x, y, z+1) == registeredFluids){
					if(world.getBlock(x, y, z-1) == registeredFluids){
						if(world.getBlock(x+1, y, z+1) == registeredFluids){
							if(world.getBlock(x+1, y, z-1) == registeredFluids){
								if(world.getBlock(x-1, y, z+1) == registeredFluids){
									if(world.getBlock(x-1, y, z-1) == registeredFluids){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean hasAroundFluidsID(World world, int x, int y, int z, Map<String, Integer> registeredFluidIDs) {
		if(world.getBlock(x+1, y, z) == registeredFluidIDs){
			if(world.getBlock(x-1, y, z) == registeredFluidIDs){
				if(world.getBlock(x, y, z+1) == registeredFluidIDs){
					if(world.getBlock(x, y, z-1) == registeredFluidIDs){
						if(world.getBlock(x+1, y, z+1) == registeredFluidIDs){
							if(world.getBlock(x+1, y, z-1) == registeredFluidIDs){
								if(world.getBlock(x-1, y, z+1) == registeredFluidIDs){
									if(world.getBlock(x-1, y, z-1) == registeredFluidIDs){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean hasArroundFluidsBlocks(World world, int x, int y, int z, Block block){
		if(world.getBlock(x+1, y, z) == block){
			if(world.getBlock(x-1, y, z) == block){
				if(world.getBlock(x, y, z+1) == block){
					if(world.getBlock(x, y, z-1) == block){
						if(world.getBlock(x+1, y, z+1) == block){
							if(world.getBlock(x+1, y, z-1) == block){
								if(world.getBlock(x-1, y, z+1) == block){
									if(world.getBlock(x-1, y, z-1) == block){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}