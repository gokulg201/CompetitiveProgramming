//$Id$
package backttracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
 * 
 * @author gokul-4406
 *
 */
public class RatinMaze {
	public static List<String> solution = new ArrayList<String>();
	public static String constructPath(LinkedList<String> path){
		String result = "";
		for(String s:path){
			result+=s;
		}
		return result;
	}
	public static void solveMaze(int[][] maze,int N){
		int[][] sol = new int[N][N];
		LinkedList<String> path = new LinkedList<String>();
		String direction = null;
		solve(maze, 0, 0, sol,path,direction,N);
		Collections.sort(solution);
		System.out.println(solution);
	}
	public static void solve(int[][] maze, int row, int col,int[][] sol,LinkedList<String> path,String direction,int N){
		if(row == N-1 && col == N-1){
			sol[row][col] = 1;
			System.out.println();
			printSolution(sol);
			path.add(direction);
			solution.add(constructPath(path));
			sol[row][col] = 0;
			path.removeLast();
			return;
		}
		if(isSafe(maze, row, col, sol,N)){
			sol[row][col] = 1;
			if(direction !=null){
				path.add(direction);
			}
			direction = "U";
			solve(maze, row -1, col, sol,path,direction,N);
			direction = "L";
			solve(maze, row, col-1, sol,path,direction,N);
			direction = "R";
			solve(maze, row, col+1, sol,path,direction,N);
			direction = "D";
			solve(maze, row+1, col, sol,path,direction,N);
			direction = null;
			sol[row][col] = 0;
			if(!path.isEmpty())
				path.removeLast();
		}
		return ;
	}
	private static boolean isSafe(int[][] maze, int row, int col, int[][] sol,int N){
		return row >= 0 && row < N && col >= 0 && col < N && maze[row][col] == 1 && sol[row][col] != 1;
	}
	private static void printSolution(int[][] sol){
		for(int i =0 ;i < sol.length;i++){
			for(int j = 0;j < sol.length;j++){
				System.out.print(sol[i][j]);
			}
			System.out.println();
		}
	}
	public static long count(long[][] maze, int n, int m){
	     if(maze[1][1] == -1){
	         return 0;//If the starting node itself is not reachable then 0
	     }
	     for(int i = 1; i <= n;i++){
	         if(maze[i][1] == -1){
	             break;
	         }
	         maze[i][1] = 1;//We can move down from the starting point
	     }
	     for(int i = 1; i <= m;i++){
	         if(maze[1][i] == -1){
	             break;
	         }
	         maze[1][i] = 1;//We can move right from the starting point
	     }
	     for(int i = 2; i <= n;i++){
	         for(int j = 2; j <= m;j++){
	             //Checking if the slot is reachable
	             if(maze[i][j] != -1){
	                 //Checking if the slot is reachable from left
	                 if(maze[i][j-1] != -1){
	                     maze[i][j] = (maze[i][j] % 1000000007) + (maze[i][j-1] % 1000000007);
	                 }
	                 //Checking if the slot is reachable from top
	                 if(maze[i-1][j] != -1){
	                     maze[i][j] = (maze[i][j]% 1000000007) + (maze[i-1][j]% 1000000007);
	                 }
	             }
	         }
	     }
	     return maze[n][m] != -1 ? maze[n][m] % 1000000007: 0;
	 }
	public static void main(String[] args){
//		int[][] maze =  { {1, 0, 0, 0},
//		        {1, 1, 0, 0},
//		        {1, 1, 0, 0},
//		        {0, 1, 1, 1}
//		    };
//		 solveMaze(maze,maze.length);
		Scanner in = new Scanner(System.in);
	    int t = in.nextInt();
	    while(t-- > 0){
	        int n = in.nextInt();
	        int m = in.nextInt();
	        long[][] maze = new long[n+1][m+1];
	        int k = in.nextInt();
	        for(int i = 0; i < k;i++){
	            int row = in.nextInt();
	            int col = in.nextInt();
	            maze[row][col] = -1;
	        }
	        System.out.println(count(maze,n,m));
	    }
	}
}
