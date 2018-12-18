//$Id$
package backttracking;

/**
 * 
 * @author gokul-4406
 *
 */
public class KnightsTourWarnsdorff {
	static int N = 8;
	static final int[] x_coordi = {2, 1, -1, -2, -2, -1, 1, 2};
	static final int[] y_coordi = {1, 2, 2, 1, -1, -2, -2, -1};
	static final int start_x = (int) Math.random() % N;
	static final int start_y = (int) Math.random() % N;
	static void solveKT(){
		int[][] sol = new int[N][N];
		for(int i =0;i < N;i++){
			for(int j = 0;j < N;j++){
				sol[i][j] = -1;
			}
		}
		int x = start_x;
		int y = start_y;
		sol[x][y] = 0;
		if(!solveKTUtil(x,y,1,sol)){
			System.out.println("No Solution");
		}else{
			if(!isNeighbour(x, y, start_x, start_y)){
				System.out.println("Solution is not closed tour");
				printSolution(sol);
			}else{
				printSolution(sol);
			}
		}
	}
	static boolean solveKTUtil(int x, int y, int move, int[][] sol){
		if(move == N*N) return true;
		int move_x , move_y;
		int min_deg_idx = -1, min_deg = (N+1);
		//Select a square with minimum no of degree and unvisited one
		for(int i = 0; i < 8; i++){
			move_x = x + x_coordi[i];
			move_y = y + y_coordi[i];
			if(isSafe(move_x, move_y, sol)){
				//Calculate the minimum degree square
				int deg = getAdjacent(move_x, move_y, sol);
				if(deg < min_deg){
					min_deg = deg;
					min_deg_idx = i;
				}
			}
		}
		if(min_deg_idx == -1) return false;
		//Calculate the actual move 
		move_x = x + x_coordi[min_deg_idx];
		move_y = y + y_coordi[min_deg_idx];
		
		sol[move_x][move_y] = move;
		
		return solveKTUtil(move_x, move_y, move + 1, sol);
	}
	static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }    
	}
	static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 &&
                y < N && sol[x][y] == -1);
    }
	/*
	 * Get the no of unvisited adjacent squares 
	 */
	static int getAdjacent(int x, int y, int sol[][]){
		int count  = 0;
		for(int i = 0;i < N;i++){
			if(isSafe(x + x_coordi[i], y + y_coordi[i], sol)){
				count++;
			}
		}
		return count;
	}
	static boolean isNeighbour(int x, int y, int _x, int _y){
		for(int i = 0 ; i < 8 ;i++){
			if((x + x_coordi[i] == _x) && (y + y_coordi[i] == _y))
				return true;
		}
		return false;
	}
	public static void main(String[] args){
		solveKT();
	}
}
