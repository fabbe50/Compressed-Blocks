package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class Multiblock3x3x3Checker {
	
	/**
	 * This function will check if You correctly formed the right multiblock.
	 * All the data are stored in the block_front !!!!!!
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param block_floor - blocks on the whole floor of the multiblock
	 * @param block_middle_corners - middle floor corners of the multiblock
	 * @param block_middlefloor_middles - blocks in the middle walls on the middle floor
	 * @param block_hollow - block directly inside multiblock
	 * @param block_front - the front block is in the middle of the middle floor
	 * @param block_top - blocks on the whole top of the multiblock
	 * @return
	 */
	
	
	/*
	 * HOW TO ADD THE CODE: (The simplest way.)
	 * Remember to change the "block_floor", "block_middle_corners" etc. to the blocks like Blocks.air or Blocks.stonebricks or YourMod.YourBlock
	 * Yes that's how the EM Furnace code looks like :P
	 * 
	 * 	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
			if(!world.isRemote && Multiblock3x3x3Checker.isMultiBlockStructureWithDifferentBlocks(world, x, y, z, block_floor, block_middle_corners, block_middlefloor_middles, block_hollow, block_front, block_top)){
				FMLNetworkHandler.openGui(player, EasyMultiblock.instance, EMGuis.guiIdEMFurnace, world, x, y, z);
			}
			return true;
		}
	 */
	
	
	/*
	 * The good example of this is the EM Furnace made like so:
	 * 
	 * block_floor - stone bricks (SB)
	 * block_middle_corners - stone bricks (SB)
	 * block_middlefloor_middles - iron bars (IB)
	 * block_hollow - lava (LA)
	 * block_front - EM Furnace Block (EM)
	 * block_top - stone bricks (SB)
	 * 
	 * 1st floor 
	 * 	SB	SB	SB
	 * 	SB	SB	SB
	 * 	SB	SB	SB
	 * 
	 * 2nd floor
	 * 	SB	IB	SB
	 * 	IB	LA	IB
	 * 	SB	EM	SB
	 * 
	 * 3rd floor
	 * 	SB	SB	SB
	 * 	SB	SB	SB
	 * 	SB	SB	SB
	 */

	public static boolean isMultiBlockStructureWithDifferentBlocks(World world, int x, int y, int z, Block block_floor, Block block_middle_corners, Block block_middlefloor_middles, Block block_hollow,Block block_front, Block block_top) {
		if (checkNorth(world, x, y, z, block_floor, block_middle_corners, block_middlefloor_middles, block_hollow, block_front, block_top))/* North */
			return true;
		if (checkEast(world, x, y, z, block_floor, block_middle_corners, block_middlefloor_middles, block_hollow, block_front, block_top))/* East */
			return true;
		if (checkSouth(world, x, y, z, block_floor, block_middle_corners, block_middlefloor_middles, block_hollow, block_front, block_top))/* South */
			return true;
		if (checkWest(world, x, y, z, block_floor, block_middle_corners, block_middlefloor_middles, block_hollow, block_front, block_top))/* West */
			return true;
		return false;
	}
	
	private static boolean checkNorth(World world, int x, int y, int z, Block block_floor, Block block_front, Block block_middle_corners, Block block_middlefloor_middles, Block block_hollow, Block block_top){
		if(world.getBlock(x+-1, y+-1, z+0) == block_floor){
			if(world.getBlock(x+-1, y+-1, z+-1) == block_floor){
				if(world.getBlock(x+-1, y+-1, z+-2) == block_floor){
					if(world.getBlock(x+-1, y+0, z+0) == block_middle_corners){
						if(world.getBlock(x+-1, y+0, z+-1) == block_middlefloor_middles){
							if(world.getBlock(x+-1, y+0, z+-2) == block_middle_corners){
								if(world.getBlock(x+-1, y+1, z+0) == block_top){
									if(world.getBlock(x+-1, y+1, z+-1) == block_top){
										if(world.getBlock(x+-1, y+1, z+-2) == block_top){
											if(world.getBlock(x+0, y+-1, z+0) == block_floor){
												if(world.getBlock(x+0, y+-1, z+-1) == block_floor){
													if(world.getBlock(x+0, y+-1, z+-2) == block_floor){
														if(world.getBlock(x+0, y+0, z+-1) == block_hollow){
															if(world.getBlock(x+0, y+0, z+-2) == block_middlefloor_middles){
																if(world.getBlock(x+0, y+1, z+0) == block_top){
																	if(world.getBlock(x+0, y+1, z+-1) == block_top){
																		if(world.getBlock(x+0, y+1, z+-2) == block_top){
																			if(world.getBlock(x+1, y+-1, z+0) == block_floor){
																				if(world.getBlock(x+1, y+-1, z+-1) == block_floor){
																					if(world.getBlock(x+1, y+-1, z+-2) == block_floor){
																						if(world.getBlock(x+1, y+0, z+0) == block_middle_corners){
																							if(world.getBlock(x+1, y+0, z+-1) == block_middlefloor_middles){
																								if(world.getBlock(x+1, y+0, z+-2) == block_middle_corners){
																									if(world.getBlock(x+1, y+1, z+0) == block_top){
																										if(world.getBlock(x+1, y+1, z+-1) == block_top){
																											if(world.getBlock(x+1, y+1, z+-2) == block_top){
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
			}
		}
	return false;
	}
	
	private static boolean checkEast(World world, int x, int y, int z, Block block_floor, Block block_middle_corners, Block block_middlefloor_middles, Block block_hollow,Block block_front, Block block_top){
		if(world.getBlock(x+0, y+-1, z+-1) == block_floor){
			if(world.getBlock(x+1, y+-1, z+-1) == block_floor){
				if(world.getBlock(x+2, y+-1, z+-1) == block_floor){
					if(world.getBlock(x+0, y+0, z+-1) == block_middle_corners){
						if(world.getBlock(x+1, y+0, z+-1) == block_middlefloor_middles){
							if(world.getBlock(x+2, y+0, z+-1) == block_middle_corners){
								if(world.getBlock(x+0, y+1, z+-1) == block_top){
									if(world.getBlock(x+1, y+1, z+-1) == block_top){
										if(world.getBlock(x+2, y+1, z+-1) == block_top){
											if(world.getBlock(x+0, y+-1, z+0) == block_floor){
												if(world.getBlock(x+1, y+-1, z+0) == block_floor){
													if(world.getBlock(x+2, y+-1, z+0) == block_floor){
														if(world.getBlock(x+1, y+0, z+0) == block_hollow){
															if(world.getBlock(x+2, y+0, z+0) == block_middlefloor_middles){
																if(world.getBlock(x+0, y+1, z+0) == block_top){
																	if(world.getBlock(x+1, y+1, z+0) == block_top){
																		if(world.getBlock(x+2, y+1, z+0) == block_top){
																			if(world.getBlock(x+0, y+-1, z+1) == block_floor){
																				if(world.getBlock(x+1, y+-1, z+1) == block_floor){
																					if(world.getBlock(x+2, y+-1, z+1) == block_floor){
																						if(world.getBlock(x+0, y+0, z+1) == block_middle_corners){
																							if(world.getBlock(x+1, y+0, z+1) == block_middlefloor_middles){
																								if(world.getBlock(x+2, y+0, z+1) == block_middle_corners){
																									if(world.getBlock(x+0, y+1, z+1) == block_top){
																										if(world.getBlock(x+1, y+1, z+1) == block_top){
																											if(world.getBlock(x+2, y+1, z+1) == block_top){
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
			}
		}
	return false;
	}

	private static boolean checkSouth(World world, int x, int y, int z, Block block_floor, Block block_middle_corners, Block block_middlefloor_middles, Block block_hollow,Block block_front, Block block_top){
		if(world.getBlock(x+1, y+-1, z+0) == block_floor){
			if(world.getBlock(x+1, y+-1, z+1) == block_floor){
				if(world.getBlock(x+1, y+-1, z+2) == block_floor){
					if(world.getBlock(x+1, y+0, z+0) == block_middle_corners){
						if(world.getBlock(x+1, y+0, z+1) == block_middlefloor_middles){
							if(world.getBlock(x+1, y+0, z+2) == block_middle_corners){
								if(world.getBlock(x+1, y+1, z+0) == block_top){
									if(world.getBlock(x+1, y+1, z+1) == block_top){
										if(world.getBlock(x+1, y+1, z+2) == block_top){
											if(world.getBlock(x+0, y+-1, z+0) == block_floor){
												if(world.getBlock(x+0, y+-1, z+1) == block_floor){
													if(world.getBlock(x+0, y+-1, z+2) == block_floor){
														if(world.getBlock(x+0, y+0, z+1) == block_hollow){
															if(world.getBlock(x+0, y+0, z+2) == block_middlefloor_middles){
																if(world.getBlock(x+0, y+1, z+0) == block_top){
																	if(world.getBlock(x+0, y+1, z+1) == block_top){
																		if(world.getBlock(x+0, y+1, z+2) == block_top){
																			if(world.getBlock(x+-1, y+-1, z+0) == block_floor){
																				if(world.getBlock(x+-1, y+-1, z+1) == block_floor){
																					if(world.getBlock(x+-1, y+-1, z+2) == block_floor){
																						if(world.getBlock(x+-1, y+0, z+0) == block_middle_corners){
																							if(world.getBlock(x+-1, y+0, z+1) == block_middlefloor_middles){
																								if(world.getBlock(x+-1, y+0, z+2) == block_middle_corners){
																									if(world.getBlock(x+-1, y+1, z+0) == block_top){
																										if(world.getBlock(x+-1, y+1, z+1) == block_top){
																											if(world.getBlock(x+-1, y+1, z+2) == block_top){
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
			}
		}
	return false;
	}
			
	private static boolean checkWest(World world, int x, int y, int z, Block block_floor, Block block_middle_corners, Block block_middlefloor_middles, Block block_hollow,Block block_front, Block block_top){
		if(world.getBlock(x+0, y+-1, z+1) == block_floor){
			if(world.getBlock(x+-1, y+-1, z+1) == block_floor){
				if(world.getBlock(x+-2, y+-1, z+1) == block_floor){
					if(world.getBlock(x+0, y+0, z+1) == block_middle_corners){
						if(world.getBlock(x+-1, y+0, z+1) == block_middlefloor_middles){
							if(world.getBlock(x+-2, y+0, z+1) == block_middle_corners){
								if(world.getBlock(x+0, y+1, z+1) == block_top){
									if(world.getBlock(x+-1, y+1, z+1) == block_top){
										if(world.getBlock(x+-2, y+1, z+1) == block_top){
											if(world.getBlock(x+0, y+-1, z+0) == block_floor){
												if(world.getBlock(x+-1, y+-1, z+0) == block_floor){
													if(world.getBlock(x+-2, y+-1, z+0) == block_floor){
														if(world.getBlock(x+-1, y+0, z+0) == block_hollow){
															if(world.getBlock(x+-2, y+0, z+0) == block_middlefloor_middles){
																if(world.getBlock(x+0, y+1, z+0) == block_top){
																	if(world.getBlock(x+-1, y+1, z+0) == block_top){
																		if(world.getBlock(x+-2, y+1, z+0) == block_top){
																			if(world.getBlock(x+0, y+-1, z+-1) == block_floor){
																				if(world.getBlock(x+-1, y+-1, z+-1) == block_floor){
																					if(world.getBlock(x+-2, y+-1, z+-1) == block_floor){
																						if(world.getBlock(x+0, y+0, z+-1) == block_middle_corners){
																							if(world.getBlock(x+-1, y+0, z+-1) == block_middlefloor_middles){
																								if(world.getBlock(x+-2, y+0, z+-1) == block_middle_corners){
																									if(world.getBlock(x+0, y+1, z+-1) == block_top){
																										if(world.getBlock(x+-1, y+1, z+-1) == block_top){
																											if(world.getBlock(x+-2, y+1, z+-1) == block_top){
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
			}
		}
	return false;
	}
}