//$Id$
package DynamicProgramming;

/**
 * We have been given N balloons, each with a number of coins associated with it. 
 * On bursting a balloon i, the number of coins gained is equal to A[i-1]*A[i]*A[i+1]. 
 * Also, balloons i-1 and i+1 now become adjacent. Find the maximum possible profit earned after bursting all the balloons.
 * Assume an extra 1 at each boundary.
 * 
 * Examples:
	Input : 5, 10
	Output : 60
	Explanation - First Burst 5, Coins = 1*5*10
	              Then burst 10, Coins+= 1*10*1
	              Total = 60
	
	Input : 1, 2, 3, 4, 5
	Output : 110
 * 
 * Reference:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BurstBalloons.java
 * https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
 * @author gokul-4406
 *
 */
public class BalloonBurst {
	/*
	 * There are multiple approaches to this problem
	 * We can by a recursive solution with memoisation or a dynamic programming approach. This will yield O(n^3) Time Complexity
	 * 
	 * In both approaches, the idea is the same. 
	 */
	
	/*
	 * Approach 1: Recursive approach
	 * We recursively check for each element if that forms the last burst 
	 */
	
	public static int maxCoins(int[] iNums){
		//Ammended input array. Have inlcuded left most and right most boundary values too.i.e., left = 1 and right = 1 
		int[] num = new int[iNums.length + 2]; //+ 2 because to hold left and right most costs i.e., 1
		int n = 1;
		for(int i : iNums){
			num[n++] = i;
		}
		//Boundaries
		num[0] = num[n++] = 1;
		//We need to create a memoisation table
		int[][] memo = new int[n][n];
		return burstUtil(memo,num,0,n-1);
		
	}
	/*
	 * Consider a balloon and it is assumed to be the last ballon to be bursted.
	 * Calculate the cost of bursting this balloon and add to it the cost of bursting other balloons which probably follow the same process
	 *  
	 */
	public static int burstUtil(int[][] memo, int[] num, int left, int right){
		//Base Case
		//If there is no element between right nd left
		if(left+1 == right) return 0;
		if(memo[left][right] > 0) return memo[left][right];
		//Consisder each balloon as the last burst and compute the ans
		int ans = 0;
		for(int i = left + 1;i < right;i++){
			ans = Math.max(ans,num[left] * num[i] * num[right] +
					burstUtil(memo, num, left, i) +//Left burst combinations
					burstUtil(memo, num, i, right));//Right burst combinations
			//Memoisation
			memo[left][right] = ans;
		}
		return ans;
	}
	public static int burst(int[] balloons){
		int n = balloons.length;
		if(n == 0){
			return 0;
		}
		//Memoisation table
		int[][] memo = new int[n][n];
		//Iterating thorugh sub arrays of varying lengths
		for(int len = 1; len <= n;len++){
			for(int i = 0;i <= n - len;i++){
				int j = i + len - 1;
				for(int k = i;k <=j;k++){
					//Declaring required variables
					int leftValue = 1;
					int rightValue = 1;
					if(i != 0){
						leftValue = balloons[i - 1];
					}
					if(j != n-1){
						rightValue = balloons[j + 1];
					}
					int leftBurst = 0;
					int rightBurst = 0;
					if(i != k){
						leftBurst = memo[i][k - 1];
					}
					if(j != k){
						rightBurst = memo[k + 1][j];
					}
					memo[i][j] = Math.max(memo[i][j], leftValue * balloons[k] * rightValue + leftBurst + rightBurst);
				}
			}
		}
		return memo[0][n - 1];
	}
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		int[] balloons = {3,1,5,8,2,5,7,99,100,21,32,123,123,45,32,45,67,21,78,90,324,324,827,34,45,343,324324,3424,23423,2341,2434,43,234,56,7876,23423,5676,3242,6756234,344};
		System.out.println(burst(balloons));
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime+"ms");
	}
}
