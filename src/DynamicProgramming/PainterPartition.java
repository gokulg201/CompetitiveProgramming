//$Id$
package DynamicProgramming;

/**
 * https://practice.geeksforgeeks.org/problems/the-painters-partition-problem/0
 * https://articles.leetcode.com/the-painters-partition-problem-part-ii/
 * https://articles.leetcode.com/the-painters-partition-problem/
 * @author gokul-4406
 *
 */
public class PainterPartition {
	public static void main(String[] args){
		int[] a = {10, 10, 10, 10};
		int n = a.length;
		int k = 2;
		findMax_DP(a, n, k);
//		System.out.println(findMax_recursion(a, n, k));
	}
	static int MAX_N = 100;
	/**
	 * Dynamic Programming approach
	 * @param A
	 * @param n
	 * @param k
	 */
	static void findMax_DP(int A[], int n, int k) {
		  int M[][] = new int[n+1][k+1];
		  int cum[] = new int[n+1];
		  for (int i = 1; i <= n; i++)
		    cum[i] = cum[i-1] + A[i-1];
		 
		  for (int i = 1; i <= n; i++)
		    M[i][1] = cum[i];
		  for (int i = 1; i <= k; i++)
		    M[1][i] = A[0];
		 
		  for (int i = 2; i <= k; i++) {
		    for (int j = 2; j <= n; j++) {
		      int best = Integer.MAX_VALUE;
		      for (int p = 1; p <= j; p++) {
		        best = Integer.min(best, Integer.max(M[p][i-1], cum[j]-cum[p]));
		      }
		      M[j][i] = best;
		    }
		  }
		  System.out.println("The minimum of max subset sum is "+M[n][k]);
		  int[] cut = new int[k];
		  int result = M[n][k];
		  int pos = k - 2;
		  int i = k;
		  int j = n;
		  while (i > 1 && j > 0){
			  for(int p = j ; p >= 1;p--){
				  if(cum[j] - cum[p] == result){
					 cut[pos--] = p + 1;
					 j = p;
					 i = i - 1;
					 result = M[j][i];
					 break;
				  }else if(M[p][i-1] == result){
					  cut[pos--] = p + 1;
					  j = p;
					  i = i - 1;
					  result = M[j][i];
					  break;
				  }
			  }
		  }
		  for(int x = 0;x < cut.length;x++){
			  System.out.print(cut[x]+" ");
		  }
		}
	static int sum(int[] arr, int from ,int to){
		int sum = 0;
		for(int i = from; i <= to ;i++){
			sum+=arr[i];
		}
		return sum;
	}
	static int findMax_recursion(int[] arr, int n, int k){
		if(k == 1)
			return sum(arr, 0, n - 1);
		if(n == 1)
			return arr[0];
		int best = Integer.MAX_VALUE;
		for(int j = 1; j < n ;j++){
			best = Integer.min(best, Integer.max(findMax_recursion(arr, j, k- 1), sum(arr, j, n - 1)));
		}
		return best;
	}
}
