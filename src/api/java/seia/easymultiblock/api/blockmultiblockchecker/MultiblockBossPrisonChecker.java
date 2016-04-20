package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MultiblockBossPrisonChecker {

	public static boolean isCorrectFormed(World world, int x, int y, int z, Block b1, Block top){
		if(buildingChecker(world, x, y, z, b1, top)){
			return true;
		}
		return false;
	}
	
	public static boolean isCorrecrFormed(World world, int x, int y, int z, ItemStack b1, int metaB1, ItemStack top, int metaTop){
		if(buildingChecker(world, x, y, z, b1, metaB1, top, metaTop)){
			return true;
		}
		return false;
	}
	
	public static boolean isCorrecrFormed(World world, int x, int y, int z, Block b1, ItemStack top, int metaTop){
		if(buildingChecker(world, x, y, z, b1, top, metaTop)){
			return true;
		}
		return false;
	}
	
	private static boolean buildingChecker(World world, int x, int y, int z,Block b1, ItemStack top, int metaTop) {
		if(world.getBlock(x+-1, y, z) == b1){
			if(world.getBlock(x+-2, y, z) == b1){
				if(world.getBlock(x+-3, y, z) == b1){
					if(world.getBlock(x+-3, y, z+-1) == b1){
						if(world.getBlock(x, y, z+-1) == b1){
							if(world.getBlock(x, y, z+-2) == b1){
								if(world.getBlock(x, y, z+-3) == b1){
									if(world.getBlock(x+-1, y, z+-3) == b1){
										if(world.getBlock(x+-2, y, z+-2) == b1){
											if(world.getBlock(x+-2, y+1, z+-2) == b1){
												if(world.getBlock(x+-2, y+2, z+-2) == b1){
													if(new ItemStack(world.getBlock(x+-2, y+3, z+-2), 1, metaTop) == top){
														return true;
													}
												}
											}
										}
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

	private static boolean buildingChecker(World world, int x, int y, int z, Block b1, Block top){
		if(world.getBlock(x+-1, y, z) == b1){
			if(world.getBlock(x+-2, y, z) == b1){
				if(world.getBlock(x+-3, y, z) == b1){
					if(world.getBlock(x+-3, y, z+-1) == b1){
						if(world.getBlock(x, y, z+-1) == b1){
							if(world.getBlock(x, y, z+-2) == b1){
								if(world.getBlock(x, y, z+-3) == b1){
									if(world.getBlock(x+-1, y, z+-3) == b1){
										if(world.getBlock(x+-2, y, z+-2) == b1){
											if(world.getBlock(x+-2, y+1, z+-2) == b1){
												if(world.getBlock(x+-2, y+2, z+-2) == b1){
													if(world.getBlock(x+-2, y+3, z+-2) == top){
														return true;
													}
												}
											}
										}
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
	
	private static boolean buildingChecker(World world, int x, int y, int z, ItemStack b1, int metaB1, ItemStack top, int metaTop){
		if(new ItemStack(world.getBlock(x+-1, y, z), 1, metaB1) == b1){
			if(new ItemStack(world.getBlock(x+-2, y, z), 1, metaB1) == b1){
				if(new ItemStack(world.getBlock(x+-3, y, z), 1, metaB1) == b1){
					if(new ItemStack(world.getBlock(x+-3, y, z+-1), 1, metaB1) == b1){
						if(new ItemStack(world.getBlock(x, y, z+-1), 1, metaB1) == b1){
							if(new ItemStack(world.getBlock(x, y, z+-2), 1, metaB1) == b1){
								if(new ItemStack(world.getBlock(x, y, z+-3), 1, metaB1) == b1){
									if(new ItemStack(world.getBlock(x+-1, y, z+-3), 1, metaB1) == b1){
										if(new ItemStack(world.getBlock(x+-2, y, z+-2), 1, metaB1) == b1){
											if(new ItemStack(world.getBlock(x+-2, y+1, z+-2), 1, metaB1) == b1){
												if(new ItemStack(world.getBlock(x+-2, y+2, z+-2), 1, metaB1) == b1){
													if(new ItemStack(world.getBlock(x+-2, y+3, z+-2), 1, metaTop) == top){
														return true;
													}
												}
											}
										}
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
