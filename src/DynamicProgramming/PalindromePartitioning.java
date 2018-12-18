//$Id$
package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * Problem:
 * https://leetcode.com/problems/palindrome-partitioning-ii/ 
 * @author gokul-4406
 *
 */
public class PalindromePartitioning {
	/**
	 * Solution:
	 * Similar to matrix chain multiplication
	 * Try to put a cut at places from i to j-1 and find the minimum 
	 * 
	 * My own solution
	 * 
	 */
	public static int minimumCut(String s){
		int n = s.length();
		int[][] cut = new int[n][n];
		boolean[][] isPal = new boolean[n][n];
		for (int i = 0; i < n; i++) 
        { 
			isPal[i][i] = true; 
			cut[i][i] = 0;
        } 
		for(int len = 2; len <= n;len++){
			for(int i = 0; i < n - len + 1;i++){
				int j = i + len - 1;
				//Checking for palindrome
				if(len == 2){
					if(s.charAt(i) == s.charAt(j)){
						isPal[i][j] = true;
					}else{
						isPal[i][j] = false;
					}
				}else{
					if(s.charAt(i) == s.charAt(j) && isPal[i + 1][j - 1]){
						isPal[i][j] = true;
					}else{
						isPal[i][j] = false;
					}
				}
				if(isPal[i][j]){
					cut[i][j] = 0;
				}else{
					cut[i][j] = Integer.MAX_VALUE;
					for(int k = i ;k < j;k++){
						cut[i][j] = Integer.min(cut[i][j], 1 + cut[i][k] + cut[k+1][j]);
					}
				}
			}
		}
//		printPalMatrix(isPal);
		return cut[0][n-1];
	}
	/**
	 * Solution:
	 * Anchor i to the right-> Checking for i length string alone
	 * Iterate j through 0 to i
	 * Find if j to i forms a palindrome or not and update isPal[j][i] . Now the cuts required is cut[j - 1] + 1
	 * Find all the cuts required while we traverse j from 0 to i.Minimum of that forms the cut at i;
	 * The above case stands true if we have a palindrome in between 0 to i. 
	 * Else the cuts required will be one less than the no of charcters(As we have to place a cut after each character as each character itself forms a valid palindrome) 
	 * 
	 * Reference:
	 * https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)
	 * 
	 * Time Complexity: O(n^2)
	 */
	public static int minCut(String str){
        if (str.length() == 0) {
            return 0;
        }
        int[] cut = new int[str.length()];
        boolean isPal[][] = new boolean[str.length()][str.length()];
        for (int i = 1; i < str.length(); i++) {//Note i runs only upto s.length()
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == str.charAt(j) && (i == j || isPal[j + 1][i - 1])) {//If you check for isPal[i-1][j+1] you need to update isPal[i][j]. It will form a tilted structure
                	//i == j is chekd because , if they are same we need not check isPal[j + 1][i - 1]
                    isPal[j][i] = true;
                    min = Math.min(min, j == 0 ? 0 : 1 + cut[j - 1]);
                }
            }
            cut[i] = min;
        }
        printPalMatrix(isPal);
        printCutMatrix(cut);
        return cut[str.length() - 1];
    }
	
	/**
	 * Simple recursive solution. But it takes exponential time 
	 */
	public static int minCutRecursive(String s, int i , int j){
		if (i == j || isPal(s, i, j)){
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for(int k = i ; k < j; k++){
			min = Math.min(min,1 + minCutRecursive(s, i, k) + minCutRecursive(s, k + 1, j)); 
		}
		return min;
	}
	//========================================UTILITY FUNCTIONS==================================================//
	public static boolean isPal(String s, int i,int j){
		char[] c = s.toCharArray();
		while(i < j){
			if(c[i++] != c[j--]){
				return false;
			}
		}
		return true;
	}
	private static void printPalMatrix(boolean isPal[][]){
		for(int i = 0;i < isPal.length;i++){
			for(int j = 0;j <isPal[0].length;j++){
				System.out.print(isPal[i][j] +" ");
			}
			System.out.println();
		}
	}
	public static void printCutMatrix(int[] cut){
		for(int i = 0 ; i < cut.length;i++){
			System.out.print(cut[i]+" ");
		}
		System.out.println();
	}
	//==========================================DIFFERENT VERSION OF THE PROBLEM================================================//
	/**
	 *
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * 
	 * Example:
	 * Input: "aab"
	 * Output:
	 * [["aa","b"],["a","a","b"]]
	 * 
	 * Solution:
	 * It is simple backtracking approach
	 * The approach is start from 0 and find a palindrome , 
	 * a , then recurse for ab.
	 * Now find if (start, i) forms a palindrome(aa- here), the recurse for the rest(i+1 , s.length) (b- here)
	 * Then again check for aab.
	 * 
	 * This approach is faster 
	 * Problem:
	 * https://leetcode.com/problems/palindrome-partitioning/description/
	 * 
	 * Reference:
	 * https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-Backtracking-solution.
	 */
	 public static List<List<String>> partition(String s) {
	        List<List<String>> result = new ArrayList<List<String>>();
	        if(s == null || s.length() == 0){
	            return result;
	        }
	        List<String> curr = new ArrayList<String>();
	        partitionUtil(s, 0 , result, curr);
	        return result;
	    }
	    public static void partitionUtil(String s, int start, List<List<String>> result,List<String> curr){
	        if(curr.size() > 0 && start == s.length()){//The recursion has reached the end
	            result.add(new ArrayList<>(curr));
	            return ;
	        }
	        for(int i = start;i < s.length();i++){
	            if(isPal(s,start, i)){
	                String currString = s.substring(start, i + 1);
	                curr.add(currString);
	                partitionUtil(s, i + 1, result, curr);
	                curr.remove(curr.size() - 1);
	            }
	        }
	    }
	    /*
	     * DFS appraoch
	     */
	    public List<List<String>> partitionDFS(String s) {
	        Map<Integer, List<List<String>>> dp = new HashMap<>();
	        List<List<String>> result =  partitionUtil(s, dp, 0);
	        List<List<String>> r = new ArrayList<>();
	        for (List<String> l : result) {
	            r.add(l);
	        }
	        return r;
	    }
	    private List<List<String>> partitionUtil(String s, Map<Integer, List<List<String>>> dp, int start) {
	        if (start == s.length()) {
	            List<String> r = new ArrayList<>();
	            return Collections.singletonList(r);
	        }

	        if (dp.containsKey(start)) {
	            return dp.get(start);
	        }

	        List<List<String>> words = new ArrayList<>();
	        for (int i = start; i < s.length(); i++) {
	            if (!isPal(s, start, i) ) {
	                continue;
	            }
	            String newWord = s.substring(start, i + 1);
	            List<List<String>> result = partitionUtil(s, dp, i + 1);
	            for (List l : result) {
	                List<String> l1 = new ArrayList<>();
	                l1.add(newWord);
	                l1.addAll(l);
	                words.add(l1);
	            }
	        }
	        dp.put(start, words);
	        return words;
	    }
	public static void main(String[] args){
//		System.out.println(minCutRecursive("abcbm", 0 , 4));
		System.out.println(partition("aab"));
	}
}
