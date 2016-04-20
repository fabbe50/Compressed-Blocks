package seia.easymultiblock.API.BlockMultiblockConnectionChecker;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MultiblockDirewolf20HomeChecker {
	
	public static boolean isDirewolf20Home(World world, int x, int y, int z, Block direChecker){
		if(world.getBlock(x, y-1, z) == direChecker){
			return true;
		}
		return false;
	}
	
	public static void buildDirewolf20(World world, int x, int y, int z, Block walls, Block windows, Block torches){
		isClear(world, x, y, z, 9);
		buildWalls(world, x, y, z, 9, walls);
		isClear(world, x+1, y+1, z+1, 7);
		makeWindowsRoof(world, x+2, y+8, z+2, 2, windows);
		makeWindowsRoof(world, x+5, y+8, z+2, 2, windows);
		makeWindowsRoof(world, x+2, y+8, z+5, 2, windows);
		makeWindowsRoof(world, x+5, y+8, z+5, 2, windows);
		putTorches(world, x+1, y+1, z+4, 7, torches);
		putTorches(world, x+4, y+1, z+1, 7, torches);
		putTorches(world, x+7, y+1, z+4, 7, torches);
		putTorches(world, x+4, y+1, z+7, 7, torches);
	}
	
	public static void isClear(World world, int x, int y, int z, int cleanSize){
		for(int i=0; i<cleanSize; i++){
			for(int j=0; j<cleanSize; j++){
				for(int k=0; k<cleanSize; k++){
					world.setBlock(x+i, y+j, z+k, Blocks.air);
				}
			}
		}
	}
	
	public static void buildWalls(World world, int x, int y, int z, int wallsSize, Block wallsBlock){
		for(int i=0; i<wallsSize; i++){
			for(int j=0; j<wallsSize; j++){
				for(int k=0; k<wallsSize; k++){
					world.setBlock(x+i, y+j, z+k, wallsBlock);
				}
			}
		}
	}
	
	public static void makeWindowsRoof(World world, int x, int y, int z, int windowsSize, Block windowsBlock){
		for(int i=0; i<windowsSize; i++){
			for(int j=0; j<windowsSize; j++){
				world.setBlock(x+i, y, z+j, windowsBlock);
			}
		}
	}
	
	public static void putTorches(World world, int x, int y, int z, int torchesColumn, Block torch){
		for(int i=0; i<torchesColumn; i++){
			world.setBlock(x, y+i, z, torch);
		}
	}
}