package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Multiblock3x3x3PrisonChecker {

	public static boolean isCorrect3x3x3Prison(World world, int x, int y, int z, Block stonebrick, Block prisoncore, Block ironbars, Block air){ 
		if (checkNorth(world, x, y, z, stonebrick, prisoncore, ironbars, air))/*North*/ return true; 
		if (checkEast(world, x, y, z, stonebrick, prisoncore, ironbars, air))/*East*/ return true; 
		if (checkSouth(world, x, y, z, stonebrick, prisoncore, ironbars, air))/*South*/ return true; 
		if (checkWest(world, x, y, z, stonebrick, prisoncore, ironbars, air))/*West*/ return true;
		return false;
	}
	
	private static boolean checkNorth(World world, int x, int y, int z, Block stonebrick, Block prisoncore, Block ironbars, Block air){
		if(world.getBlock(x+-1, y+0, z+1) == stonebrick){
			if(world.getBlock(x+-1, y+0, z+0) == stonebrick){
				if(world.getBlock(x+-1, y+0, z+-1) == stonebrick){
					if(world.getBlock(x+-1, y+1, z+1) == stonebrick){
						if(world.getBlock(x+-1, y+1, z+0) == ironbars){
							if(world.getBlock(x+-1, y+1, z+-1) == stonebrick){
								if(world.getBlock(x+-1, y+2, z+1) == stonebrick){
									if(world.getBlock(x+-1, y+2, z+0) == ironbars){
										if(world.getBlock(x+-1, y+2, z+-1) == stonebrick){
											if(world.getBlock(x+0, y+0, z+1) == stonebrick){
												if(world.getBlock(x+0, y+0, z+-1) == stonebrick){
													if(world.getBlock(x+0, y+1, z+1) == ironbars){
														if(world.getBlock(x+0, y+1, z+0) == air){
															if(world.getBlock(x+0, y+1, z+-1) == ironbars){
																if(world.getBlock(x+0, y+2, z+1) == ironbars){
																	if(world.getBlock(x+0, y+2, z+0) == air){
																		if(world.getBlock(x+0, y+2, z+-1) == ironbars){
																			if(world.getBlock(x+1, y+0, z+1) == stonebrick){
																				if(world.getBlock(x+1, y+0, z+0) == stonebrick){
																					if(world.getBlock(x+1, y+0, z+-1) == stonebrick){
																						if(world.getBlock(x+1, y+1, z+1) == stonebrick){
																							if(world.getBlock(x+1, y+1, z+0) == ironbars){
																								if(world.getBlock(x+1, y+1, z+-1) == stonebrick){
																									if(world.getBlock(x+1, y+2, z+1) == stonebrick){
																										if(world.getBlock(x+1, y+2, z+0) == ironbars){
																											if(world.getBlock(x+1, y+2, z+-1) == stonebrick){
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
	
	private static boolean checkEast(World world, int x, int y, int z, Block stonebrick, Block prisoncore, Block ironbars, Block air){
		if(world.getBlock(x+-1, y+0, z+-1) == stonebrick){
			if(world.getBlock(x+0, y+0, z+-1) == stonebrick){
				if(world.getBlock(x+1, y+0, z+-1) == stonebrick){
					if(world.getBlock(x+-1, y+1, z+-1) == stonebrick){
						if(world.getBlock(x+0, y+1, z+-1) == ironbars){
							if(world.getBlock(x+1, y+1, z+-1) == stonebrick){
								if(world.getBlock(x+-1, y+2, z+-1) == stonebrick){
									if(world.getBlock(x+0, y+2, z+-1) == ironbars){
										if(world.getBlock(x+1, y+2, z+-1) == stonebrick){
											if(world.getBlock(x+-1, y+0, z+0) == stonebrick){
												if(world.getBlock(x+1, y+0, z+0) == stonebrick){
													if(world.getBlock(x+-1, y+1, z+0) == ironbars){
														if(world.getBlock(x+0, y+1, z+0) == air){
															if(world.getBlock(x+1, y+1, z+0) == ironbars){
																if(world.getBlock(x+-1, y+2, z+0) == ironbars){
																	if(world.getBlock(x+0, y+2, z+0) == air){
																		if(world.getBlock(x+1, y+2, z+0) == ironbars){
																			if(world.getBlock(x+-1, y+0, z+1) == stonebrick){
																				if(world.getBlock(x+0, y+0, z+1) == stonebrick){
																					if(world.getBlock(x+1, y+0, z+1) == stonebrick){
																						if(world.getBlock(x+-1, y+1, z+1) == stonebrick){
																							if(world.getBlock(x+0, y+1, z+1) == ironbars){
																								if(world.getBlock(x+1, y+1, z+1) == stonebrick){
																									if(world.getBlock(x+-1, y+2, z+1) == stonebrick){
																										if(world.getBlock(x+0, y+2, z+1) == ironbars){
																											if(world.getBlock(x+1, y+2, z+1) == stonebrick){
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
	
	private static boolean checkSouth(World world, int x, int y, int z, Block stonebrick, Block prisoncore, Block ironbars, Block air){
		if(world.getBlock(x+1, y+0, z+-1) == stonebrick){
			if(world.getBlock(x+1, y+0, z+0) == stonebrick){
				if(world.getBlock(x+1, y+0, z+1) == stonebrick){
					if(world.getBlock(x+1, y+1, z+-1) == stonebrick){
						if(world.getBlock(x+1, y+1, z+0) == ironbars){
							if(world.getBlock(x+1, y+1, z+1) == stonebrick){
								if(world.getBlock(x+1, y+2, z+-1) == stonebrick){
									if(world.getBlock(x+1, y+2, z+0) == ironbars){
										if(world.getBlock(x+1, y+2, z+1) == stonebrick){
											if(world.getBlock(x+0, y+0, z+-1) == stonebrick){
												if(world.getBlock(x+0, y+0, z+1) == stonebrick){
													if(world.getBlock(x+0, y+1, z+-1) == ironbars){
														if(world.getBlock(x+0, y+1, z+0) == air){
															if(world.getBlock(x+0, y+1, z+1) == ironbars){
																if(world.getBlock(x+0, y+2, z+-1) == ironbars){
																	if(world.getBlock(x+0, y+2, z+0) == air){
																		if(world.getBlock(x+0, y+2, z+1) == ironbars){
																			if(world.getBlock(x+-1, y+0, z+-1) == stonebrick){
																				if(world.getBlock(x+-1, y+0, z+0) == stonebrick){
																					if(world.getBlock(x+-1, y+0, z+1) == stonebrick){
																						if(world.getBlock(x+-1, y+1, z+-1) == stonebrick){
																							if(world.getBlock(x+-1, y+1, z+0) == ironbars){
																								if(world.getBlock(x+-1, y+1, z+1) == stonebrick){
																									if(world.getBlock(x+-1, y+2, z+-1) == stonebrick){
																										if(world.getBlock(x+-1, y+2, z+0) == ironbars){
																											if(world.getBlock(x+-1, y+2, z+1) == stonebrick){
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
	
	private static boolean checkWest(World world, int x, int y, int z, Block stonebrick, Block prisoncore, Block ironbars, Block air){
		if(world.getBlock(x+1, y+0, z+1) == stonebrick){
			if(world.getBlock(x+0, y+0, z+1) == stonebrick){
				if(world.getBlock(x+-1, y+0, z+1) == stonebrick){
					if(world.getBlock(x+1, y+1, z+1) == stonebrick){
						if(world.getBlock(x+0, y+1, z+1) == ironbars){
							if(world.getBlock(x+-1, y+1, z+1) == stonebrick){
								if(world.getBlock(x+1, y+2, z+1) == stonebrick){
									if(world.getBlock(x+0, y+2, z+1) == ironbars){
										if(world.getBlock(x+-1, y+2, z+1) == stonebrick){
											if(world.getBlock(x+1, y+0, z+0) == stonebrick){
												if(world.getBlock(x+-1, y+0, z+0) == stonebrick){
													if(world.getBlock(x+1, y+1, z+0) == ironbars){
														if(world.getBlock(x+0, y+1, z+0) == air){
															if(world.getBlock(x+-1, y+1, z+0) == ironbars){
																if(world.getBlock(x+1, y+2, z+0) == ironbars){
																	if(world.getBlock(x+0, y+2, z+0) == air){
																		if(world.getBlock(x+-1, y+2, z+0) == ironbars){
																			if(world.getBlock(x+1, y+0, z+-1) == stonebrick){
																				if(world.getBlock(x+0, y+0, z+-1) == stonebrick){
																					if(world.getBlock(x+-1, y+0, z+-1) == stonebrick){
																						if(world.getBlock(x+1, y+1, z+-1) == stonebrick){
																							if(world.getBlock(x+0, y+1, z+-1) == ironbars){
																								if(world.getBlock(x+-1, y+1, z+-1) == stonebrick){
																									if(world.getBlock(x+1, y+2, z+-1) == stonebrick){
																										if(world.getBlock(x+0, y+2, z+-1) == ironbars){
																											if(world.getBlock(x+-1, y+2, z+-1) == stonebrick){
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