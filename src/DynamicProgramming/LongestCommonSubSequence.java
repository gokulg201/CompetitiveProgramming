//$Id$
package DynamicProgramming;

import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 * https://www.youtube.com/watch?v=NnD96abizww&t=394s
 * @author gokul-4406
 *
 */
public class LongestCommonSubSequence {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int sz_a = in.nextInt();
			int sz_b = in.nextInt();
			String a = in.next();
			String b = in.next();
			char[] ch_a = a.toCharArray();
			char[] ch_b = b.toCharArray();
			System.out.println(LCS_DP(ch_a, ch_b, sz_a, sz_b));
		}
	}
	/**
	 * Dynamic programming approach of Longest Common Subsequence
	 */
	static int LCS_DP(char[] ch_a,char[] ch_b,int sz_a,int sz_b){
		int[][] lcs = new int[sz_a+1][sz_b+1];
		int result = 0;
		for(int i = 1; i <= sz_a;i++){
			for(int j = 1; j <=sz_b; j++){
				if(ch_a[i - 1] == ch_b[j -1]){
					//pick the diagonal element and add 1 to it
					//To find the charcters if a move is diagonal then that character is in the sequence
					lcs[i][j] = lcs[i -1][j -1] + 1;
					result = Integer.max(result, lcs[i][j]);
				}else{
					//pick the max of i-1/j-1 element
					lcs[i][j] = Integer.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		return lcs[sz_a][sz_b];
	}
	/**
	 * Naive recursive approach
	 */
	static int LCS_recursion(char[] ch_a,char[] ch_b,int sz_a,int sz_b){
		if(sz_a == 0 || sz_b == 0){
			return 0;
		}
		if(ch_a[sz_a] == ch_b[sz_b]){
			return 1 + LCS_recursion(ch_a, ch_b, sz_a - 1, sz_b - 1);
		}else{
			return Integer.max(LCS_recursion(ch_a, ch_b, sz_a, sz_b - 1), LCS_recursion(ch_a, ch_b, sz_a - 1, sz_b));
		}
	}
}
