//$Id$
package backttracking;

class Solution {
    public static boolean solveSudoku(char[][] board) {
        int[] slot = findEmptySlot(board);
        int row = slot[0] , col = slot[1];
        if(row == -1 && col == -1){//No empty slots available
            return true;
        }
        //trying out numbers
        for(int i = 1; i <= 9;i++){
            if(isSafe(board, row, col, i)){
                board[row][col] = Integer.valueOf(i).toString().charAt(0);
                if(solveSudoku(board)){
                    return true;
                }
                board[row][col] = '.';
            }
        } 
        return false;
    }
    
    public static boolean isSafe(char[][] board,int row, int col, int no){
        return checkRow(board, row, no) &&
            checkColumn(board, col , no) &&
            checkBox(board, row - row % 3, col - col% 3 , no);
    }
    private static boolean checkRow(char[][] board, int row, int no){
        for(int i = 0 ; i < board.length; i++){
            if(board[row][i] == no){
                return false;
            }
        }
        return true;
    }
    private static boolean checkColumn(char[][] board, int col, int no){
        for(int i = 0 ; i < board.length; i++){
            if(board[i][col] == no){
                return false;
            }
        }    
        return true;
    }
    private static boolean checkBox(char[][] board,int row, int col, int no){
        for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
                if(board[row + i][col + j] == no)
                    return false;
        return true;
    }
    public static int[] findEmptySlot(char[][] grid){
        int[] slot = {-1,-1};
        for(int i = 0; i < grid.length;i++){
            for(int j = 0 ; j < grid.length;j++){
                if(grid[i][j] == '.'){
                    slot[0] = i;
                    slot[1] = j;
                    return slot;
                }
            }
        }
        return slot;
    }
    static void printGrid(char[][] grid)
	{
	    for (int row = 0; row < grid.length; row++)
	    {
	       for (int col = 0; col < grid.length; col++){
	    	   System.out.print(grid[row][col]+" ");
	       }
	       System.out.println();
	    }
	}
    public static int minDistance(String word1, String word2) {
        if(word1 == null || word1.isEmpty()){
            if(word2 == null || word2.isEmpty()){
                 return 0;  
            }else{
                return word2.length();
            }
        }
        if(word2 == null || word2.isEmpty()){
            if(word1 == null || word1.isEmpty()){
                 return 0;  
            }else{
                return word1.length();
            }
        }
        int size1 = word1.length();
        int size2 = word2.length();
        
        int[][] dp = new int[size1 + 1][size2 + 1];
        for(int i = 0; i <=size1 ;i++){
            for(int j = 0 ;j <= size2; j++){
                if(i == 0){
                    dp[i][j] = j;
                    continue;
                }
                if(j == 0){
                    dp[i][j] = i;
                    continue;
                }
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + 1;
                }
            }
        }
        return dp[size1][size2];
    }
    static int N = 9;
    static void solveKT(){
		int[][] sol = new int[N][N];
		for(int i =0;i < N;i++){
			for(int j = 0;j < N;j++){
				sol[i][j] = -1;
			}
		}
		int[] x_coordi = {2, 1, -1, -2, -2, -1, 1, 2};
		int[] y_coordi = {1, 2, 2, 1, -1, -2, -2, -1};
		sol[0][0] = 0;
		if(solveKTUtil(0, 0, 1, x_coordi, y_coordi, sol)){
			printSolution(sol);
		}else{
			System.out.println("No Solution");
		}
	}
	static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 &&
                y < N && sol[x][y] == -1);
    }
	static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }    
	}
	static boolean solveKTUtil(int row, int col, int move, int[] x_coordi,int[] y_coordi,int[][] sol){
		if(move == N *N){
			return true;
		}
		int _x, _y;
		for(int k = 0; k < 8;k++){
			_x = row + x_coordi[k];
			_y = col + y_coordi[k];
			if(isSafe(_x, _y, sol)){
				sol[_x][_y] = move;
				if(solveKTUtil(_x, _y, move + 1, x_coordi, y_coordi, sol)){
					return true;
				}
				sol[_x][_y] = 0;
			}
		}
		return false;
	}
    public static void main(String[] args){
    	solveKT();
//    	System.out.println(minDistance("horse", "ros"));
//    	String[][] grid = {{"5","3",".",".","7",".",".",".","."},{"6",".",".","1","9","5",".",".","."},{".","9","8",".",".",".",".","6","."},{"8",".",".",".","6",".",".",".","3"},{"4",".",".","8",".","3",".",".","1"},{"7",".",".",".","2",".",".",".","6"},{".","6",".",".",".",".","2","8","."},{".",".",".","4","1","9",".",".","5"},{".",".",".",".","8",".",".","7","9"}};
//    	char[][] board = new char[grid.length][grid.length];
//    	for(int i = 0 ; i < grid.length;i++){
//    		for(int j = 0 ; j < grid.length;j++){
//    			board[i][j] = grid[i][j].charAt(0);
//    		}
//    	}
//    	System.out.println(solveSudoku(board));
//    	printGrid(board);
    }
}