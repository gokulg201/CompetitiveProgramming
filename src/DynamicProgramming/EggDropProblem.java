//$Id$
package DynamicProgramming;

public class EggDropProblem {
	public static void main(String[] args){

	}
	/*
	* This is a naive recursive solution.
	* Algo :
	* 1) If we select a floor to experiment with, there are possibly 2 out comes.-> Egg will break; Egg will not break;
	* 	i)So, if it breaks,the result floor may be the same floor or the floor below this floor. i.e., trials(floor - 1 , eggs - 1)
	* 	ii)If it doesnt break, the result floor is above the current floor. i.e., trials(floor + 1, eggs)
	* 2) The above said logic can be recursively processed for each floor -> 1 + max(trials(floor - 1 , eggs - 1),trials(totalFloors - floor , eggs - 1));
	* 3) We will get the min  of all the above results, then we will ge the minimum no of combinations
	*/
	public static int trials(int floors, int eggs){
		//Base Case 1 
		//If the no of floors is 1 or 0, then the answer is 1 or 0
		if(floors == 1 || floors == 0){
			return floors;
		}
		//Base case 2
		//If the no of eggs is 1, then it will take as much trials as the no of floors
		if(eggs == 1){
			return floors;
		}

		int min = Integer.MAX_VALUE , result;

		for(int k = 1; k <=floors; k++){
			result = 1 + Integer.max(trials(k - 1 , eggs - 1),trials(floors - k, eggs));
			if(result < min){
				min = result;
			}
		}
		return min ;
	}
	public static int trialsDP(int floors, int eggs){
		int[][] dp = new int[eggs + 1][floors + 1];
		for(int i = 1;i <=eggs;i++){
			dp[i][1] = 1; // 1 trial for one floor 
			dp[i][0] = 0; // 0 trial for zero floor
		}
		for(int i = 1 ;i <= floors; i++){
			dp[1][i] = i; // With 1 Egg, no of trials equals the no of floors
		}
		int result = Integer.MAX_VALUE;
		for(int i = 2 ; i <= eggs; i++){
			for(int j = 1; j <=floors ;j++){
				dp[i][j] = Integer.MAX_VALUE;
				for(int x = 1; x <= j ;x++){
					result = 1 + Integer.max(dp[i-1][x-1],dp[i][j-x]);
					if(result < dp[i][j]){
						dp[i][j] = result;
					}
				}				
			}
		}
		return dp[eggs][floors];
	}
}
