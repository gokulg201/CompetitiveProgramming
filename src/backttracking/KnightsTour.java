//$Id$
package backttracking;

/**
 * https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 * @author gokul-4406
 *
 */
public class KnightsTour {
	static int N = 9;
	static boolean solveKTUtil(int _x,int _y, int _move, int[] x_coordi,int[] y_coordi,int[][] sol){
		if(_move == N*N)
			return true;
		int move_x , move_y;
		for(int k = 0;k < 8 ;k++){
			move_x = _x + x_coordi[k];
			move_y = _y + y_coordi[k];
			if(isSafe(move_x, move_y, sol)){
				sol[move_x][move_y] = _move;
				if(solveKTUtil(move_x, move_y, _move + 1, x_coordi, y_coordi,sol)){
					return true;
				}else{
					sol[move_x][move_y]  = -1;
				}
			}
		}
		return false;
	}
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
	public static void main(String[] args){
		solveKT();
	}
}
