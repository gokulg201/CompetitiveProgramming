//$Id$
package backttracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
class Rat{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			int n=sc.nextInt();
			int[][] a=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					a[i][j]=sc.nextInt();
			GfG g=new GfG();
			ArrayList<String> res=g.printPath(a,n);
			for(int i=0;i<res.size();i++)
				System.out.print(res.get(i)+" ");
			System.out.println();
		}
	}
}

/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/*Complete the function below*/
class GfG{
    public static String constructPath(LinkedList<String> path){
		String result = "";
		for(String s:path){
			result+=s;
		}
		return result;
	}
     public static ArrayList<String> printPath(int[][] maze, int N)
     {
          
		int[][] sol = new int[N][N];
		LinkedList<String> path = new LinkedList<String>();
		ArrayList<String> solution = new ArrayList<String>();
		String direction = null;
		solve(maze, 0, 0, sol,path,direction,N,solution);
		Collections.sort(solution);
	    return solution;
     }
     public static void solve(int[][] maze, int row, int col,int[][] sol,LinkedList<String> path,String direction,int N,ArrayList<String> solution){
		if(row == N-1 && col == N-1){
			sol[row][col] = 1;
			System.out.println();
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
			solve(maze, row -1, col, sol,path,direction,N,solution);
			direction = "L";
			solve(maze, row, col-1, sol,path,direction,N,solution);
			direction = "R";
			solve(maze, row, col+1, sol,path,direction,N,solution);
			direction = "D";
			solve(maze, row+1, col, sol,path,direction,N,solution);
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
}