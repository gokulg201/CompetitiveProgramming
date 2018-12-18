//$Id$
package DynamicProgramming;

import java.util.Scanner;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * https://leetcode.com/articles/maximal-square/
 * 
 * References:
 * https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
 * @author gokul-4406
 */
public class LargestSquare {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] matrix = new int[n][m];
			for(int i =0 ;i < n ;i++){
				for(int j = 0; j < m ;j++){
					matrix[i][j] = in.nextInt();
				}
			}
			largestSquare_dp2(matrix,n,m);
		}
	}
	/**
	 * Complexity Analysis:
	 * m-row, n- column
	 * Time Complexity O(mn)
	 * Space Complexity O(mn) - storing an extra square matrix
	 * @param matrix
	 */
	public static void largestSquare_dp1(int[][] matrix){
		int[][] dp = new int[matrix.length][matrix.length];
		int maxSqLen = 0;
		for(int i = 0 ;i < matrix.length;i++){
			for(int j = 0;j < matrix.length;j++){
				if(i == 0 || j == 0){
					dp[i][j] = matrix[i][j];
					continue;
				}
				if(matrix[i][j] == 1){
					dp[i][j] = min(dp[i][j - 1], dp[i - 1][j - 1], dp[i - 1][j]) + 1;
					if(maxSqLen < dp[i][j])
						maxSqLen = dp[i][j];
				}
			}
		}
		System.out.println(maxSqLen);
	}
	/**
	 * Complexity Analysis:
	 * m-row, n- column
	 * Time Complexity O(mn)
	 * Space Complexity O(n) - an extra array of size-column
	 * @param matrix
	 */
	public static void largestSquare_dp2(int[][] matrix){
		int[] dp = new int[matrix.length];
		int maxSqLen = 0;
		int prev = 0;
		for(int i = 0 ;i < matrix.length;i++){
			for(int j = 0;j < matrix.length;j++){
				int temp = dp[j];
				if(i == 0 || j == 0){
					dp[j] = matrix[i][j];
					prev = temp;
					continue;
				}
				if(matrix[i][j] == 1){
					dp[j] = min(dp[j - 1], dp[j], prev) + 1;
					if(maxSqLen < dp[j])
						maxSqLen = dp[j];
				}else{
					dp[j] = 0;
				}
				prev = temp;
			}
		}	
		System.out.println(maxSqLen);
	}
	public static void largestSquare_dp2(int[][] matrix,int rowLength,int columnLength){
		int[] dp = new int[columnLength];
		int maxSqLen = 0;
		int prev = 0;
		for(int i = 0 ;i < rowLength;i++){
			for(int j = 0;j < columnLength;j++){
				int temp = dp[j];
				if(i == 0 || j == 0){
					dp[j] = matrix[i][j];
					prev = temp;
					if(maxSqLen < dp[j])
						maxSqLen = dp[j];
					continue;
				}
				if(matrix[i][j] == 1){
					dp[j] = min(dp[j - 1], dp[j], prev) + 1;
					if(maxSqLen < dp[j])
						maxSqLen = dp[j];
				}else{
					dp[j] = 0;
				}
				prev = temp;
			}
		}	
		System.out.println(maxSqLen);
	}
	public static int min(int a , int b, int c){
		return Integer.min(a, Integer.min(b, c));
	}
}
