//$Id$
package backttracking;

import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/sudoku-backtracking-7/
 * @author gokul-4406
 *
 */
public class Sudoku {
	/**
	 * Solution reference:
	 * https://leetcode.com/problems/sudoku-solver/discuss/15752/Straight-Forward-Java-Solution-Using-Backtracking
	 * @param grid
	 * @return
	 */
	public static boolean solveSudoku(int[][] grid){
		int[] coord = findUnAssignedLocation(grid);
		int row = coord[0], col = coord[1];
		if(row == -1 && col == -1)
			return true;
		for(int i = 1 ;i <=9; i++){
			if(isSafe(grid, row, col, i)){
				grid[row][col] = i;
				if(solveSudoku(grid)){
					return true;
				}
				grid[row][col] = 0;
			}else{
				continue;
			}
		}
		return false;
	}
	private static int[] findUnAssignedLocation(int[][] grid){
		int[] coord = {-1,-1};
		 for (int row = 0; row < grid.length; row++)
		        for (int col = 0; col < grid.length; col++)
		            if (grid[row][col] == 0){
		            	coord[0] = row;
		 				coord[1] = col;
		 				return coord;
		            }
	    return coord;
	}
	private static boolean isSafe(int[][] grid, int row, int col, int value){
		return checkRow(grid, row, value) && 
				checkColumn(grid, col, value) && 
				   checkBox(grid, row - row%3, col - col%3, value);
	}
	private static boolean checkRow(int[][] grid, int row,int value){
		for(int col = 0 ;col < grid.length;col++){
			if(grid[row][col] == value)
				return false;
		}
		return true;
	}
	private static boolean checkColumn(int[][] grid, int col, int value){
		for(int row = 0 ;row < grid.length;row++){
			if(grid[row][col] == value)
				return false;
		}
		return true;
	}
	private static boolean checkBox(int[][] grid, int boxStartRow, int boxStartCol, int value){
		for (int row = 0; row < 3; row++)
	        for (int col = 0; col < 3; col++)
	            if (grid[row+boxStartRow][col+boxStartCol] == value)
	                return false;
	    return true;
	}
	static void printGrid(int[][] grid)
	{
	    for (int row = 0; row < grid.length; row++)
	    {
	       for (int col = 0; col < grid.length; col++){
	    	   System.out.print(grid[row][col]+" ");
	       }
	       System.out.println();
	    }
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int[][] grid = new int[9][9];
			for(int i = 0;i < 9;i++){
				for(int j =0 ;j < 9 ;j++){
					grid[i][j] = in.nextInt();
				}
			}
			 if (solveSudoku(grid) == true){
				 printGrid(grid);
			 }
		}
//		 int[][] grid = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
//                 {5, 2, 0, 0, 0, 0, 0, 0, 0},
//                 {0, 8, 7, 0, 0, 0, 0, 3, 1},
//                 {0, 0, 3, 0, 1, 0, 0, 8, 0},
//                 {9, 0, 0, 8, 6, 3, 0, 0, 5},
//                 {0, 5, 0, 0, 9, 0, 6, 0, 0},
//                 {1, 3, 0, 0, 0, 0, 2, 5, 0},
//                 {0, 0, 0, 0, 0, 0, 0, 7, 4},
//                 {0, 0, 5, 2, 0, 6, 3, 0, 0}};
//		 if (solveSudoku(grid) == true)
//			 printGrid(grid);
//		 else{
//			 printGrid(grid);
//			 System.out.println("No solution exists");
//		 }
	}
}
