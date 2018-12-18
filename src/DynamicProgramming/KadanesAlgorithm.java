//$Id$
package DynamicProgramming;

public class KadanesAlgorithm {
	/*
	 * Problem Statement: The aim here is to find a contiguous sub-array in a given array such that,
	 * it has the largest sum.
	 * 
	 * Algorithm :
	 * 1) Traverse through the array and try to find all positive contiguous segments.
	 * 2) Find max of those
	 * Note: This algorithm will not work if we have all negative integers in the array
	 * 
	 * Time Complexity : O(n)
	 * Space Complexity : 0
	 */
	public static int largestSumContiguousSubArray(int[] arr){
		int max_so_far = 0, max_ending_here = 0;
		int max_ele = Integer.MIN_VALUE;
		int n = arr.length;
		for(int i = 0 ;i < n;i++){
			max_ele = Integer.max(max_ele, arr[i]);
			max_ending_here = max_ending_here + arr[i];
			if(max_ending_here < 0)
				max_ending_here = 0;
			else{
				if(max_so_far < max_ending_here){
					max_so_far = max_ending_here;
				}
			}
		}
		if(max_so_far == 0){//Handle for all negative numbers
			return max_ele;
		}
		return max_so_far;
	}
	
	public static void main(String[] args){
		int[] arr = {-2, -3, -4, 4, -2, -1, -5, -3};
		int sum = largestSumContiguousSubArray(arr);
		System.out.println("Maximum Continguous sum "+sum);
	}
}
