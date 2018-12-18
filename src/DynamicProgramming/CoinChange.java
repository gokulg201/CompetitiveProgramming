//$Id$
package DynamicProgramming;

import java.util.Scanner;
/**
 * https://www.geeksforgeeks.org/coin-change-dp-7/
 * https://www.youtube.com/watch?v=jaNZ83Q3QGc
 * @author gokul-4406
 */
public class CoinChange {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int n = in.nextInt();
			int[] coins = new int[n];
			for(int i = 0;i < n;i++){
				coins[i] = in.nextInt();
			}
			int sum = in.nextInt();
			int[] ways = new int[sum+1];
			ways[0] = 1;
			for(int i = 0 ;i < n;i++){
				for(int j = 1 ;j <=sum ;j++){
					if(j >= coins[i]){
						ways[j] += ways[j - coins[i]];
					}
				}
			}
			System.out.println(ways[sum]);
		}
	}
	public static int count(int[] coins, int n , int sum){
		if(sum == 0){
			return 1;
		}
		if(sum < 0){
			return 0;
		}
		if(n <= 0 && sum > 0){
			return 0;
		}
		return count(coins, n - 1, sum) + count(coins, n, sum - coins[n - 1]);// Excluding the nth coin, Including the nth coin
	}
	public static int countDP(int[] coins, int n, int sum){
		int[][] dp = new int[coins.length+1][sum + 1];
		for(int i=0; i <= coins.length; i++){
			dp[i][0] = 1;
        }
		for(int i = 1 ;i < n;i++){
			for(int j = 1 ;j <=sum ;j++){
				if(coins[i - 1] > j){
					//This coin cannot be selected
					dp[i][j] = dp[i - 1][j];
 				}else{
 					//We can select this coin.
 					//Need to consider 2 possibilities
 					dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];
 				}
			}
		}
		return dp[coins.length][sum];
	}
}
