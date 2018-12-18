//$Id$
package DynamicProgramming;

import java.util.ArrayList;

public class MinimumPartition {
	/**
	 * Partition into two subsets such that the sum of elements in both subsets is same.
	 * Dynamic Programming approach
	 * https://www.geeksforgeeks.org/partition-problem-dp-18/
	 * @param set
	 */
	static boolean equalPartitions_DP(int[] set){
		long sum = 0;
		int n  = set.length;
		for(int _ele : set){
			sum+=_ele;
		}
		if(sum % 2 == 0){
			boolean dp[][] = new boolean[(int) (sum/2 + 1)][n + 1];
			for(int i = 1 ; i <= (int)sum/2 ;i++){
				dp[i][0] = false;
			}
			for(int j = 0;j <=n ; j++){
				dp[0][j] = true;
			}
			for (int i = 1; i <= sum/2; i++)  
		     {
		       for (int j = 1; j <= n; j++)  
		       {
		         dp[i][j] = dp[i][j-1];
		         if (i >= set[j-1])
		        	 dp[i][j] = dp[i][j] || dp[i - set[j-1]][j-1];
		       }        
		     }   
			if(dp[(int)sum/2][n]){
				printAllSubsets(set, (int)sum/2, n - 1, new ArrayList<Integer>(), dp);
			}
			return dp[(int)sum/2][n];
		}else{
			return false;
		}
	}
	static void displayPath(ArrayList<Integer> path){
		System.out.println(path);
	}
	static void printAllSubsets(int[] arr, int sum, int n, ArrayList<Integer> path,boolean dp[][]){
		if(n == 0 && sum!=0 && dp[0][sum]){
			path.add(arr[n]);
			displayPath(path);
			path.clear();
			return ;
		}
		if(sum == 0){
			displayPath(path);
			path.clear();
			return ; 
		}
		if(dp[n-1][sum]){
			ArrayList<Integer> b = new ArrayList<>(); 
            b.addAll(path);
            printAllSubsets(arr, sum, n - 1, b, dp);
		}
		if(sum >= arr[n] && dp[n-1][sum-arr[n]]){
			path.add(arr[n]);
			printAllSubsets(arr, sum - arr[n], n - 1, path, dp);
		}
	}
	/**
	 * Partition into two subsets such that the sum of elements in both subsets is same.
	 * Recursive approach
	 * https://www.geeksforgeeks.org/partition-problem-dp-18/
	 * @param set
	 * @return
	 */
	static boolean equalPartitions_recursive(int[] set){
		long sum = 0;
		int n  = set.length;
		for(int _ele : set){
			sum+=_ele;
		}
		if (sum%2 != 0)
            return false;
 
        // Find if there is subset with sum equal to half
        // of total sum
        return isSubsetSum(set,n,(int)sum/2);
	}
	static boolean isSubsetSum(int[] set,int n,int sum){
		if(sum == 0){
			return true;
		}
		if(n == 0 && sum != 0){
			return false;
		}
		//If last element is greater than sum ignore it
		if(set[n-1] > sum){
			return isSubsetSum(set, n -1, sum);
		}
		
		return 
				isSubsetSum(set, n - 1, sum) || // Last element is not included in the subset
				isSubsetSum(set, n, sum - set[n -1]); // last element is included in the subset
	}
	/**
	 * Partition a set into two subsets such that the difference of subset sums is minimum
	 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
	 * @param set
	 */
	static int minimumDiffPartition(int arr[], int n){
		 // Calculate sum of all elements
        int sum = 0; 
        for (int i = 0; i < n; i++)
            sum += arr[i];
     
        // Create an array to store 
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];
     
        // Initialize first column as true. 
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
     
        // Initialize top row, except dp[0][0], 
        // as false. With 0 elements, no other 
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;
     
        // Fill the partition table 
        // in bottom up manner
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];
     
                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }
     
        // Initialize difference of two sums. 
        int diff = Integer.MAX_VALUE;
         
        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--)
        {
            // Find the 
            if (dp[n][j] == true)
            {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
	}
	public static void main (String[] args) 
    {
        int arr[] = {1, 4, 2, 2, 1};
        int n = arr.length;
//        System.out.println ("The minimum difference between 2 sets is "
//                            + minimumDiffPartition(arr, n));
     System.out.println(equalPartitions_DP(arr));
    }
}
