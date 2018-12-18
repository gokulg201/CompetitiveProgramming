package DynamicProgramming;
//$Id$

import java.util.Scanner;

/**
 * Practice IDE : https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence/0
 * Problem Statement : https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
 * Algorithm to find the longest subsequence in a given array
 * @author gokul-4406
 *
 */
public class LongestSubsequence {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	    while(T-- > 0){
	        int n = sc.nextInt();
	        int[] arr = new int[n];
	        for(int i=0; i<n; i++)
	            arr[i] = sc.nextInt();
	        System.out.println(solve(arr, n));
	    }     
	}
	/**
	 * This algorithm will solve LongestIncreasing subsequence problem in
	 * Time Complexity O(N^2)
	 * Space Complexity O(N)
	 * @param arr
	 * @param size
	 * @return
	 */
	static int solve(int[] arr, int size){
		int[] lis = new int[size];
		if(size >= 1) lis[0] = 1;
        else return 0;
		for(int k = 0;k < size;k++){
			lis[k] = 1;
		}
		for (int i = 1; i < size; i++ ){
			for (int k = 0; k < i; k++ ){
				if(arr[k] < arr[i]){
					lis[i] = Math.max(lis[i], lis[k]+1);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++ )
              if ( max < lis[i] )
                 max = lis[i];
		return max;
	}
	/**
	 * Reference : https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	 * This is an optimised algorithm to find the longest increasing subsequence with a 
	 * time complexity O( N log N)
	 * space complexity O(N)
	 * @return
	 */
	static int lis(int[] arr, int n){
		int[] tail = new int[n];
		int p = 1;
		tail[0] = arr[0];
		for(int i =1; i < n;i++){
			//If the next element is less than the start element of the active sequence
			if(arr[i] < tail[0]){
				tail[0] = arr[i];
			}else if(arr[i] > tail[p-1]){ // If the next element is larger than the end element
				//Just clone, and extend
				tail[p++] = arr[i];
			}else{
				//If the element falls in between
				//Clone the series with end element less than current element and extend. Discard the series with same length
				//For discarding we use ceil version of binary search to get elements equal or greater than the current element
				tail[ceilBinarySearch(arr,0, p - 1, arr[i])] = arr[i];
			}
		}
		return p;
	}
	static int ceilBinarySearch(int[] arr, int l , int r,int key){
		int m ;
		//Right and left pointer should not overlap
		while(r - l > 1){
			m = l + (r - l)/2;
			if(arr[m] >= key){
				r = m;
			}else{
				l = m;
			}
		}
		return r;//Index of element greater than or equal to the key(ceil)
	}
}
