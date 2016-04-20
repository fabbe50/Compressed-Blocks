package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MultiblockLargeHomeChecker {

	public static boolean isLargeHome(World world, int x, int y, int z, Block blockLargeChecker){
		if(world.getBlock(x, y-1, z) == blockLargeChecker){
			return true;
		}
		return false;
	}
	
	/*
	 * Idea came from Mustangsc1. He's the main author of the Large Home.
	 */
	public static void makeLargeHome(World world, int x, int y, int z){
		isClear(world, x, y, z, 20, 17, 26);
		makeBasement(world, x, y, z, 3, Blocks.stonebrick, 14, 19);
		makeFloor(world, x, y, z, 12, 17, 1, Blocks.planks);
		makeWalls(world, x, y+1, z, Blocks.log, 1, 14, 5, 19);
		isClear(world, x+1, y+1, z+1, 12, 5, 17);
		isClear(world, x+6, y+1, z, 2, 2, 1);
		makeWindows(world, x+2, y+2, z, 2, 2, 1);
		makeWindows(world, x+6, y+4, z, 2, 1, 1);
		makeWindows(world, x+10, y+2, z, 2, 2, 1);
		makeWindows(world, x, y+2, z+3, 1, 2, 2);
		makeWindows(world, x, y+2, z+9, 1, 2, 3);
		makeWindows(world, x, y+3, z+15, 1, 1, 2);
		makeWindows(world, x+13, y+2, z+3, 1, 2, 2);
		makeWindows(world, x+13, y+2, z+9, 1, 2, 3);
		makeWindows(world, x+13, y+3, z+15, 1, 1, 2);
		makeWindows(world, x+2, y+2, z+18, 3, 2, 1);
		makeWindows(world, x+9, y+2, z+18, 3, 2, 1);
		/*
		 * Roof
		 */
		makeWalls(world, x-1, y+6, z-1, Blocks.planks, 0, 16, 1, 21);
		makeWalls(world, x, y+6, z, Blocks.log, 1, 14, 1, 19);
		isClear(world, x+1, y+6, z+1, 12, 1, 17);
		makeWalls(world, x, y+7, z, Blocks.planks, 0, 14, 1, 19);
		makeWalls(world, x+1, y+7, z+1, Blocks.log, 1, 12, 1, 17);
		isClear(world, x+2, y+7, z+2, 10, 1, 15);
		makeWalls(world, x+1, y+8, z+1, Blocks.planks, 0, 12, 1, 17);
		makeWalls(world, x+2, y+8, z+2, Blocks.log, 1, 10, 1, 15);
		isClear(world, x+3, y+8, z+3, 8, 1, 13);
		makeWalls(world, x+2, y+9, z+2, Blocks.planks, 0, 10, 1, 15);
		makeWalls(world, x+3, y+9, z+3, Blocks.log, 1, 8, 1, 13);
		isClear(world, x+4, y+9, z+4, 6, 1, 11);
		makeWalls(world, x+3, y+10, z+3, Blocks.planks, 0, 8, 1, 13);
		makeWalls(world, x+4, y+10, z+4, Blocks.log, 1, 6, 1, 11);
		isClear(world, x+5, y+10, z+5, 4, 1, 9);
		makeWalls(world, x+4, y+11, z+4, Blocks.planks, 0, 6, 1, 11);
		makeWalls(world, x+5, y+11, z+5, Blocks.log, 1, 4, 1, 9);
		isClear(world, x+6, y+11, z+6, 2, 1, 7);
		makeWalls(world, x+5, y+12, z+5, Blocks.planks, 0, 4, 1, 9);
		makeWalls(world, x+6, y+12, z+6, Blocks.log, 1, 2, 1, 7);
		makeWalls(world, x+6, y+13, z+6, Blocks.planks, 0, 2, 1, 7);
		
		//------------------------------------------------------------------
		
		makeFloor(world, x, y+5, z, 12, 17, 4, Blocks.planks);
		makeFloor(world, x+1, y+5, z+1, 10, 15, 4, Blocks.wooden_slab);
		makeWalls(world, x+1, y+1, z+7, Blocks.log, 1, 12, 4, 1); //wall
		makeWalls(world, x+1, y+1, z+13, Blocks.log, 1, 12, 4, 1); //wall
		isClear(world, x+5, y+1, z+7, 4, 3, 1);
		isClear(world, x+3, y+1, z+13, 1, 2, 1);
		isClear(world, x+10, y+1, z+13, 1, 2, 1);
		makeWalls(world, x+6, y+1, z+14, Blocks.log, 1, 2, 4, 4);
		makeWalls(world, x+5, y+1, z+10, Blocks.log, 1, 1, 4, 3);
		isClear(world, x+5, y+3, z+11, 1, 2, 1);
		isClear(world, x+5, y+2, z+10, 4, 3, 1);
		makeWalls(world, x+8, y+1, z+10, Blocks.log, 1, 1, 4, 3);
		isClear(world, x+8, y+3, z+11, 1, 2, 1);
		isClear(world, x+8, y+2, z+10, 1, 3, 1);
		
		//---------------------------------------------------------
		
		makeWalls(world, x+11, y+1, z+7, Blocks.brick_block, 0, 1, 13, 1);
		
		//---------------------------------------------------------
		
		makeWalls(world, x+5, y+5, z+9, Blocks.log, 1, 4, 2, 5); //floor entry
		isClear(world, x+6, y+5, z+10, 2, 2, 4); //entry cleaning
	}
	
	public static void makeBasement(World world, int x, int y, int z, int metaAroundFloor, Block blockAroundFloor, int sizeFloorX, int sizeFloorZ){
		for(int i=0; i<sizeFloorX; i++){
			for(int j=0; j<sizeFloorZ; j++){
				world.setBlock(x+i, y, z+j, blockAroundFloor, metaAroundFloor, 2);
			}
		}
	}
	
	public static void isClear(World world, int x, int y, int z, int clearX, int clearY, int clearZ){
		for(int i=0; i<clearX; i++){
			for(int j=0; j<clearY; j++){
				for(int k=0; k<clearZ; k++){
					world.setBlock(x+i, y+j, z+k, Blocks.air);
				}
			}
		}
	}
	
	public static void makeFloor(World world, int x, int y, int z, int floorX, int floorZ, int metaFloor, Block blockFloor){
		for(int i=0; i<floorX; i++){
			for(int j=0; j<floorZ; j++){
				world.setBlock(x+1+i, y, z+1+j, blockFloor, metaFloor, 2);
			}
		}
	}
	
	public static void makeWalls(World world, int x, int y, int z, Block walls, int metaWalls, int sizeX, int sizeY, int sizeZ){
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				for(int k=0; k<sizeZ; k++){
					world.setBlock(x+i, y+j, z+k, walls, metaWalls, 2);
				}
			}
		}
	}
	
	public static void makeWindows(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ){
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				for(int k=0; k<sizeZ; k++){
					world.setBlock(x+i, y+j, z+k, Blocks.glass_pane);
				}
			}
		}
	}
}