//$Id$
package DynamicProgramming;

/**
 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 * Problem Statement:
 * Given an array of (even no of)numbers, there are two players who can choose either first or last element 
 * and remove them from the array
 * Find the maximum score Player 1 canget get, if he is allowed to make the first move 
 * 
 * @author gokul-4406
 *
 */
public class OptimalGameStrategy {
	public int recursiveSolution(int[] arr,int i ,int j){
		/*
		 * The math behind the problem
		 * F(i,j) = max(arr[i] + min(F(i + 1, j - 1), F(i + 2, j)) //If P1 selects arr[i] , P2 willl select an element such that leaves P1 to select a minimum element 
		 * 				arr[j] + min(F(i + 1, j - 1), F(i, j - 2))) //If P1 selects arr[j] , P2 willl select an element such that leaves P1 to select a minimum element
		 * Base Conditions:
		 * if i == j , then arr[i]
		 * if(j == i + 1) then F(i,j) = max (arr[i],arr[j])
		 */
		if(i == j)
			return arr[i];
		if(j == i + 1){
			return Integer.max(arr[i], arr[j]);
		}
					
		return Integer.max(Integer.min(recursiveSolution(arr, i + 2, j),recursiveSolution(arr, i + 1, j - 1)) + arr[i] , Integer.min(recursiveSolution(arr, i + 1, j - 1),recursiveSolution(arr, i, j - 2)) + arr[j]);
	}
	/**
	 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
	 * Exact replica of recursive soltuion into dynamic programming
	 * Time Complexity : O(n^2)
	 * @param arr
	 * @param n
	 * @return
	 */
	public int dynamicProgramming_1(int[] arr, int n){
		int[][] dp = new int[n][n];
		int x; //F(i + 2, j)
		int y; //F(i + 1, j - 1)
		int z; //F(i, j - 2)
		for(int i = 0 ;i < n; i++){
			for(int j = 0 ; j < n;j++){
				dp[i][j] = arr[i];
			}
		}
		for(int length = 0 ; length < n; length++){
			for(int i = 0 , j = length ; j < n ;i++,j++){
				x = ((i + 2) <= j) ? dp[i + 2][j] : 0;
				y = ((i + 1) <= (j-1)) ? dp[i + 1][j - 1] : 0;
				z = (i <= (j - 2)) ? dp[i][j - 2] : 0;
				dp[i][j] =  Integer.max(arr[i] + Integer.min(x,y), arr[j] + Integer.min(y,z));
			}
		}
		return dp[0][n - 1];
		
	}
	/**
	 * Reference :
	 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/NPotGold.java
	 * 
	 * Time Complexity O(n^2)
	 * @param arr
	 * @param n
	 * @return
	 */
	public int dynamicProgramming_2(int[] arr,int n){
		Pair[][] dp = new Pair[n][n];
		for(int i = 0 ;i < n; i++){
			for(int j = 0 ; j < n;j++){
				dp[i][j].first = arr[i];
				dp[i][j].second = 0;
			}
		}
		for(int count = 1 ; count < n; count++){
			for(int i = 0 ;i < n ;i++){
				int j = i + count - 1;
				if(arr[i] + dp[i + 1][j].second > arr[j] + dp[i][j - 1].second){
					dp[i][j].first = arr[i] + dp[i + 1][j].second;
					dp[i][j].second = dp[i + 1][j].first;
				}else{
					dp[i][j].first = arr[j] + dp[i][j - 1].second;
					dp[i][j].second = dp[i][j - 1].first;
				}
			}
		}
		return dp[0][n - 1].first;
	}
	static class Pair{
		int first;
		int second;
	}
}
