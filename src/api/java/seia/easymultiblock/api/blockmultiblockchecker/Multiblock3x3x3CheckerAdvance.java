package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class Multiblock3x3x3CheckerAdvance {
	
	/**
	 * I hope the names are clear to understand.
	 * If You don't know how to implement the multiblock function into Your block, see the Multiblock3x3x3Checker.
	 * It works the same way but here You have more options to choose.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param floor_corners
	 * @param floor_wall_middles
	 * @param floor_middle
	 * @param middle_corners
	 * @param middle_wall_middles
	 * @param middle_middle
	 * @param middle_front
	 * @param top_corners
	 * @param top_wall_middles
	 * @param top_middle
	 * @return
	 */
	
	/*
	 * The floors are like this:
	 * 
	 * SPACE
	 * 
	 * top - multiblock top
	 * middle - multiblock middle
	 * floor - multiblock floor
	 * 
	 * GROUND
	 * BEDROCK
	 */
	
	public boolean isMultiBlockStructureWithDifferentBlocks(World world, int x, int y, int z, Block floor_corners, Block floor_wall_middles, Block floor_middle, Block middle_corners, Block middle_wall_middles, Block middle_middle, Block middle_front, Block top_corners, Block top_wall_middles, Block top_middle){ 
		if (checkNorth(world, x, y, z, floor_corners, floor_wall_middles, floor_middle, middle_corners, middle_wall_middles, middle_middle, middle_front, top_corners, top_wall_middles, top_middle))/*North*/ return true; 
		if (checkEast(world, x, y, z, floor_corners, floor_wall_middles, floor_middle, middle_corners, middle_wall_middles, middle_middle, middle_front, top_corners, top_wall_middles, top_middle))/*East*/ return true; 
		if (checkSouth(world, x, y, z, floor_corners, floor_wall_middles, floor_middle, middle_corners, middle_wall_middles, middle_middle, middle_front, top_corners, top_wall_middles, top_middle))/*South*/ return true; 
		if (checkWest(world, x, y, z, floor_corners, floor_wall_middles, floor_middle, middle_corners, middle_wall_middles, middle_middle, middle_front, top_corners, top_wall_middles, top_middle))/*West*/ return true; 
		return false;
	}
	
	private static boolean checkNorth(World world, int x, int y, int z, Block floor_corners, Block floor_wall_middles, Block floor_middle, Block middle_corners, Block middle_wall_middles, Block middle_middle, Block middle_front, Block top_corners, Block top_wall_middles, Block top_middle){
		if(world.getBlock(x+-1, y+-1, z+0) == floor_corners){
			if(world.getBlock(x+-1, y+-1, z+-1) == floor_wall_middles){
				if(world.getBlock(x+-1, y+-1, z+-2) == floor_corners){
					if(world.getBlock(x+-1, y+0, z+0) == middle_corners){
						if(world.getBlock(x+-1, y+0, z+-1) == middle_wall_middles){
							if(world.getBlock(x+-1, y+0, z+-2) == middle_corners){
								if(world.getBlock(x+-1, y+1, z+0) == top_corners){
									if(world.getBlock(x+-1, y+1, z+-1) == top_wall_middles){
										if(world.getBlock(x+-1, y+1, z+-2) == top_corners){
											if(world.getBlock(x+0, y+-1, z+0) == floor_wall_middles){
												if(world.getBlock(x+0, y+-1, z+-1) == floor_middle){
													if(world.getBlock(x+0, y+-1, z+-2) == floor_wall_middles){
														if(world.getBlock(x+0, y+0, z+-1) == middle_middle){
															if(world.getBlock(x+0, y+0, z+-2) == middle_wall_middles){
																if(world.getBlock(x+0, y+1, z+0) == top_wall_middles){
																	if(world.getBlock(x+0, y+1, z+-1) == top_middle){
																		if(world.getBlock(x+0, y+1, z+-2) == top_wall_middles){
																			if(world.getBlock(x+1, y+-1, z+0) == floor_corners){
																				if(world.getBlock(x+1, y+-1, z+-1) == floor_wall_middles){
																					if(world.getBlock(x+1, y+-1, z+-2) == floor_corners){
																						if(world.getBlock(x+1, y+0, z+0) == middle_corners){
																							if(world.getBlock(x+1, y+0, z+-1) == middle_wall_middles){
																								if(world.getBlock(x+1, y+0, z+-2) == middle_corners){
																									if(world.getBlock(x+1, y+1, z+0) == top_corners){
																										if(world.getBlock(x+1, y+1, z+-1) == top_wall_middles){
																											if(world.getBlock(x+1, y+1, z+-2) == top_corners){
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
	
	private static boolean checkEast(World world, int x, int y, int z, Block floor_corners, Block floor_wall_middles, Block floor_middle, Block middle_corners, Block middle_wall_middles, Block middle_middle, Block middle_front, Block top_corners, Block top_wall_middles, Block top_middle){
		if(world.getBlock(x+0, y+-1, z+-1) == floor_corners){
			if(world.getBlock(x+1, y+-1, z+-1) == floor_wall_middles){
				if(world.getBlock(x+2, y+-1, z+-1) == floor_corners){
					if(world.getBlock(x+0, y+0, z+-1) == middle_corners){
						if(world.getBlock(x+1, y+0, z+-1) == middle_wall_middles){
							if(world.getBlock(x+2, y+0, z+-1) == middle_corners){
								if(world.getBlock(x+0, y+1, z+-1) == top_corners){
									if(world.getBlock(x+1, y+1, z+-1) == top_wall_middles){
										if(world.getBlock(x+2, y+1, z+-1) == top_corners){
											if(world.getBlock(x+0, y+-1, z+0) == floor_wall_middles){
												if(world.getBlock(x+1, y+-1, z+0) == floor_middle){
													if(world.getBlock(x+2, y+-1, z+0) == floor_wall_middles){
														if(world.getBlock(x+1, y+0, z+0) == middle_middle){
															if(world.getBlock(x+2, y+0, z+0) == middle_wall_middles){
																if(world.getBlock(x+0, y+1, z+0) == top_wall_middles){
																	if(world.getBlock(x+1, y+1, z+0) == top_middle){
																		if(world.getBlock(x+2, y+1, z+0) == top_wall_middles){
																			if(world.getBlock(x+0, y+-1, z+1) == floor_corners){
																				if(world.getBlock(x+1, y+-1, z+1) == floor_wall_middles){
																					if(world.getBlock(x+2, y+-1, z+1) == floor_corners){
																						if(world.getBlock(x+0, y+0, z+1) == middle_corners){
																							if(world.getBlock(x+1, y+0, z+1) == middle_wall_middles){
																								if(world.getBlock(x+2, y+0, z+1) == middle_corners){
																									if(world.getBlock(x+0, y+1, z+1) == top_corners){
																										if(world.getBlock(x+1, y+1, z+1) == top_wall_middles){
																											if(world.getBlock(x+2, y+1, z+1) == top_corners){
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
	
	private static boolean checkSouth(World world, int x, int y, int z, Block floor_corners, Block floor_wall_middles, Block floor_middle, Block middle_corners, Block middle_wall_middles, Block middle_middle, Block middle_front, Block top_corners, Block top_wall_middles, Block top_middle){
		if(world.getBlock(x+1, y+-1, z+0) == floor_corners){
			if(world.getBlock(x+1, y+-1, z+1) == floor_wall_middles){
				if(world.getBlock(x+1, y+-1, z+2) == floor_corners){
					if(world.getBlock(x+1, y+0, z+0) == middle_corners){
						if(world.getBlock(x+1, y+0, z+1) == middle_wall_middles){
							if(world.getBlock(x+1, y+0, z+2) == middle_corners){
								if(world.getBlock(x+1, y+1, z+0) == top_corners){
									if(world.getBlock(x+1, y+1, z+1) == top_wall_middles){
										if(world.getBlock(x+1, y+1, z+2) == top_corners){
											if(world.getBlock(x+0, y+-1, z+0) == floor_wall_middles){
												if(world.getBlock(x+0, y+-1, z+1) == floor_middle){
													if(world.getBlock(x+0, y+-1, z+2) == floor_wall_middles){
														if(world.getBlock(x+0, y+0, z+1) == middle_middle){
															if(world.getBlock(x+0, y+0, z+2) == middle_wall_middles){
																if(world.getBlock(x+0, y+1, z+0) == top_wall_middles){
																	if(world.getBlock(x+0, y+1, z+1) == top_middle){
																		if(world.getBlock(x+0, y+1, z+2) == top_wall_middles){
																			if(world.getBlock(x+-1, y+-1, z+0) == floor_corners){
																				if(world.getBlock(x+-1, y+-1, z+1) == floor_wall_middles){
																					if(world.getBlock(x+-1, y+-1, z+2) == floor_corners){
																						if(world.getBlock(x+-1, y+0, z+0) == middle_corners){
																							if(world.getBlock(x+-1, y+0, z+1) == middle_wall_middles){
																								if(world.getBlock(x+-1, y+0, z+2) == middle_corners){
																									if(world.getBlock(x+-1, y+1, z+0) == top_corners){
																										if(world.getBlock(x+-1, y+1, z+1) == top_wall_middles){
																											if(world.getBlock(x+-1, y+1, z+2) == top_corners){
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
	
	private static boolean checkWest(World world, int x, int y, int z, Block floor_corners, Block floor_wall_middles, Block floor_middle, Block middle_corners, Block middle_wall_middles, Block middle_middle, Block middle_front, Block top_corners, Block top_wall_middles, Block top_middle){
		if(world.getBlock(x+0, y+-1, z+1) == floor_corners){
			if(world.getBlock(x+-1, y+-1, z+1) == floor_wall_middles){
				if(world.getBlock(x+-2, y+-1, z+1) == floor_corners){
					if(world.getBlock(x+0, y+0, z+1) == middle_corners){
						if(world.getBlock(x+-1, y+0, z+1) == middle_wall_middles){
							if(world.getBlock(x+-2, y+0, z+1) == middle_corners){
								if(world.getBlock(x+0, y+1, z+1) == top_corners){
									if(world.getBlock(x+-1, y+1, z+1) == top_wall_middles){
										if(world.getBlock(x+-2, y+1, z+1) == top_corners){
											if(world.getBlock(x+0, y+-1, z+0) == floor_wall_middles){
												if(world.getBlock(x+-1, y+-1, z+0) == floor_middle){
													if(world.getBlock(x+-2, y+-1, z+0) == floor_wall_middles){
														if(world.getBlock(x+-1, y+0, z+0) == middle_middle){
															if(world.getBlock(x+-2, y+0, z+0) == middle_wall_middles){
																if(world.getBlock(x+0, y+1, z+0) == top_wall_middles){
																	if(world.getBlock(x+-1, y+1, z+0) == top_middle){
																		if(world.getBlock(x+-2, y+1, z+0) == top_wall_middles){
																			if(world.getBlock(x+0, y+-1, z+-1) == floor_corners){
																				if(world.getBlock(x+-1, y+-1, z+-1) == floor_wall_middles){
																					if(world.getBlock(x+-2, y+-1, z+-1) == floor_corners){
																						if(world.getBlock(x+0, y+0, z+-1) == middle_corners){
																							if(world.getBlock(x+-1, y+0, z+-1) == middle_wall_middles){
																								if(world.getBlock(x+-2, y+0, z+-1) == middle_corners){
																									if(world.getBlock(x+0, y+1, z+-1) == top_corners){
																										if(world.getBlock(x+-1, y+1, z+-1) == top_wall_middles){
																											if(world.getBlock(x+-2, y+1, z+-1) == top_corners){
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