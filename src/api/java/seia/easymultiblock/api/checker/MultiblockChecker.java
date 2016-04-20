package seia.easymultiblock.API.checker;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;


/*UWAGA
 *  Start checking from the corner. It's using for the all same blocks.
 *  
 * HOW TO USE:
 * 
 * MultiblockChecker zmienna(3) // check how big is 3x3x3, 5x5x5 etc.
 * if ( zmienna.checkThisBlock(x, y, z) ) {
 * 		TRUE, COS TU ROBISZ                 
 * }
 * else {
 * 		FALSE, COS TU ROBISZ
 * }
 * 
 */
public class MultiblockChecker {	
	private World world;
	private Block block;
	private int ileX;
	private int ileY;
	private int ileZ;
	private int size;
	private int[][][] helpTable;
	
	public MultiblockChecker( World world, Block block, int size ) {
		this.world = world;
		this.block = block;
		this.size = size;
		ileX = ileY = ileZ = 0;
		//tab = new int[size][size][size];
		helpTable =  new int[size * 10][size * 10][size * 10];
	}
	
	private void resetTab() {
		for ( int i = 0; i < size * 10; i++ ) {
			for ( int j = 0; j < size * 10 ; j++ ) {
				for ( int k = 0; k < size * 10; k++ ) {
					helpTable[i][j][k] = 0;
				}
			}
		}		
	}

	private void isThisBlock( int blockX, int blockY, int blockZ, int x, int y, int z, Block block ) {
		
		helpTable[x][y][z] = 1;
		
		if ( helpTable[x + 1][y][z] != 1 
				&& world.getBlock( blockX + 1, blockY, blockZ) == block ) {	
			ileX++;
			isThisBlock( blockX + 1, blockY, blockZ, x + 1, y, z, block);
		}
		else if ( helpTable[x - 1][y][z] != 1 
				&& world.getBlock( blockX - 1, blockY, blockZ) == block ) {	
			ileX++;
			isThisBlock( blockX - 1, blockY, blockZ, x - 1, y, z, block);
		}
		else if ( helpTable[x][y + 1][z] != 1 
				&& world.getBlock( blockX, blockY + 1, blockZ) == block ) {	
			ileY++;
			isThisBlock( blockX, blockY + 1, blockZ, x, y + 1, z, block);
		}
		else if ( helpTable[x][y - 1][z] != 1 
				&& world.getBlock( blockX, blockY - 1, blockZ) == block ) {	
			ileY++;
			isThisBlock( blockX, blockY - 1, blockZ, x, y - 1, z, block);
		}
		else if ( helpTable[x][y][z + 1] != 1 
				&& world.getBlock( blockX, blockY, blockZ + 1) == block ) {	
			ileZ++;
			isThisBlock( blockX, blockY, blockZ + 1, x, y, z + 1, block);
		}
		else if ( helpTable[x][y][z - 1] != 1 
				&& world.getBlock( blockX, blockY, blockZ - 1 ) == block ) {	
			ileZ++;
			isThisBlock( blockX, blockY, blockZ - 1, x, y, z - 1, block);
		}
	}
	
	public boolean checkThisBlock(int x, int y, int z) {
		int pivot = (int)size * 10 / 2;
		resetTab();
		isThisBlock(x, y, z, pivot, pivot, pivot, block);
		return ileX + ileY + ileZ == (size * size * size - 1);
	}
}
