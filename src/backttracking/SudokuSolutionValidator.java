//$Id$
package backttracking;

import java.util.Arrays;

public class SudokuSolutionValidator {
	private static boolean checkSudokuStatus(int[][] grid) {
        for (int i = 0; i < 9; i++) {
    
            int[] column = new int[9];
            int[] square = new int[9];
            int[] row = grid[i].clone();
    
            for (int j = 0; j < 9; j ++) {
                column[j] = grid[j][i];
                square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (!(validate(column) && validate(row) && validate(square)))
                return false;
        }
        return true;
    }

    private static boolean validate(int[] check) {
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }
    public static void main(String[] args){
    	int[][] grid = 	 {{2, 4, 8, 3, 9, 5, 7, 1, 6},
			              {5, 7, 1, 6, 2, 8, 3, 4, 9},
			              {9, 3, 6, 7, 4, 1, 5, 8, 2},
			              {6, 8, 2, 5, 3, 9, 1, 7, 4},
			              {3, 5, 9, 1, 7, 4, 6, 2, 8},
			              {7, 1, 4, 8, 6, 2, 9, 5, 3},
			              {8, 6, 3, 4, 1, 7, 2, 9, 5},
			              {1, 9, 5, 2, 8, 6, 4, 3, 7},
			              {4, 2, 7, 9, 5, 3, 8, 6, 1}};
    	System.out.println(checkSudokuStatus(grid));
    }
}
