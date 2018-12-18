//$Id$
package DynamicProgramming;

/**
 * Problem Statement:
 * Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. 
 * C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters in individual strings is preserved.
 * 
 * Reference:
 * https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/
 * https://www.youtube.com/watch?v=ih2OZ9-M3OM
 * 
 * Problem:
 * https://leetcode.com/problems/interleaving-string/description/
 * 
 * @author gokul-4406
 *
 */
public class StringInterleaving {
	public static boolean isInterLeave1(String s1, String s2, String s3){
		if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0){
			return true;
		}
		if(s3.length() == 0){
			return false;
		}
		return (s1.charAt(0) == s3.charAt(0) && isInterleave(s1.substring(1), s2, s3.substring(1))) || (s2.charAt(0) == s3.charAt(0) && isInterleave(s1, s2.substring(1), s3.substring(1)));
	}
	public static boolean isInterleave(String s1, String s2, String s3) {
        //This is a 2D dynamic approach
        int m = s1.length();
        int n = s2.length();
        int l = s3.length();
        //Base Cases
        if(m + n != l){
        	return false;
        }
        boolean dp[][] = new boolean[m+1][n+1];
        for(int i = 0; i <= m ;i++){
            for(int j = 0;j <= n;j++){
            	if(i == 0 && j == 0){
            		dp[i][j] = true;
            	}
            	else if(j == 0){
            		if(i+j-1 < l && s1.charAt(i - 1) == s3.charAt(i+j-1)){
            			dp[i][j] = dp[i - 1][j];
            		}
            	}else if(i == 0){
            		if(i+j-1 < l && s2.charAt(j - 1) == s3.charAt(i+j-1)){
            			dp[i][j] = dp[i][j - 1];
            		}
            	}else{
            		if(i+j-1 < l && s1.charAt(i - 1) == s3.charAt(i+j-1)){
                        dp[i][j] |= dp[i - 1][j];
                    }
                    if(i+j-1 < l && s2.charAt(j - 1) == s3.charAt(i+j-1)){
                        dp[i][j] |= dp[i][j - 1];
                    }
            	}
            }
        }
        return dp[m][n];
    }
	public static void main(String[] args){
		String s1 ="db";
		String s2= "b";
		String s3 = "cbb";
		System.out.println(isInterleave(s1, s2, s3));
	}
}
