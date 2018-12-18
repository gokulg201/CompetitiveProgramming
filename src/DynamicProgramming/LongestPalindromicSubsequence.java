//$Id$
package DynamicProgramming;

public class LongestPalindromicSubsequence {
	public static int longestPalindrome(String str, int i, int j){
		if(str == null || str.isEmpty() || i > j){
			return 0;
		}
		if(str.length() == 1){
			return 1;
		}
		if(i == j){//Same character . This single char will form a palindrome
			return 1;
		}
		if(i + 1 == j && str.charAt(0) == str.charAt(1)){
			return 2;
		}
		//Two conditions. 
		//If the first and last letters are same, then pal length = 2 + pal(i+1, j-1)
		if(str.charAt(i) == str.charAt(j)){
			//Actually need to check whether (i+1,j-1) is a palindrome or not(for longest palindrome substring problem)
			return 2 + longestPalindrome(str, i + 1, j - 1);
		}else{
			//If they are not same, then exclude either first letter or last letter and recursively find the longest palindrome subsequence
			return Math.max(longestPalindrome(str, i, j - 1), longestPalindrome(str, i + 1, j));
		}
	}
	/**
	 * Dynamic Programming approach
	 * Same concept as above but a dynamic approach
	 */
	public static int lps(String str){
		int n = str.length();
		int[][] dp = new int[n][n];
		for(int i = 0 ; i < n ;i++){
			dp[i][i] = 1;//Since a single charcter forms a palindrome;
		}
		for(int len = 2; len <= n ;len++){
			for(int i = 0; i < n - len + 1; i++){
				int j = i + len - 1;
				if(len == 2 && str.charAt(i) == str.charAt(j)){
					dp[i][j] = 2;
				}else if(str.charAt(i) == str.charAt(j)){
					if(dp[i+1][j-1] == len - 2){
						dp[i][j] = 2 + dp[i+1][j-1];
					}else{
						dp[i][j] = dp[i+1][j-1];
					}
				}else{
					dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
				}
			}
		}
		printMatrix(dp);
		return dp[0][n - 1];
	}
	public static void printMatrix(int[][] dp){
		int n = dp.length;
		for(int i = 0 ;i < n;i++){
			for(int j = 0;j < n ;j++){
				System.out.print(dp[i][j]+ " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args){
		String str = "geeksfskeeg";
//		System.out.println(longestPalindrome(str,0,str.length() -1));
		System.out.println(lps(str));
	}
}
