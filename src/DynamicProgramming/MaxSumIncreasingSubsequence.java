//$Id$
package DynamicProgramming;

import java.util.Scanner;

/**
 * 
 * @author gokul-4406
 *
 */
public class MaxSumIncreasingSubsequence {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- >0){
			int n = in.nextInt();
			int[] arr = new int[n];
			for(int i =0 ;i < n;i++){
				arr[i] = in.nextInt();
			}
			System.out.println(msis(arr,n));
		}
	}
	public static int msis(int[] arr, int n){
		int[] msis = new int[n];
		int max = 0;
		for(int i = 0 ;i < n ;i++){
			msis[i] = arr[i];
		}
		for(int i = 1; i < n; i++){
			for(int j = 0 ; j < i ;j++){
				if(arr[j] < arr[i]){
					msis[i] = Integer.max(msis[i],msis[j] + arr[i]);
				}
			}
		}
		for(int i = 0;i < n;i++){
			if(max < msis[i])
				max = msis[i];
		}
		return max;
	}
}
