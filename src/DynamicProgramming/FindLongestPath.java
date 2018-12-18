//$Id$
package DynamicProgramming;

import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 * Given an array of integers, find the longest path such that the next element in the path is one greater than the current element.
 * And the path can traverse only one unit at a time.
 * i.e., if a element is at i,j it can go to i+1,j ; i-1,j; i,j+1; i,j-1;
 * 
 * Algo :
 * Traverse through the matrix according to the rules
 * Update the max path based on the formula 1 + path from next element
 * i.e., if current position is i,j and i+1,j is the next position the path from i,j = 1 + path(i+1,j)
 * Recurse the above until we reach a point where we cannot proceed further. In such case, return 1
 * 
 * Complexity Analysis:
 * Time Complexity : O(n^2) since we are calculating each element of dp[n][n] only once
 * Space Complexity : O(n^2) since we are maintaining an extra dp cache
 * @author gokul-4406
 *
 */
public class FindLongestPath {
	public static int path(int i, int j,int[][] dp,int[][] matrix,int n){
		//Check for validity of the row and column 
		if(i < 0 && i > n && j < 0 && j > n )
			return 0;
		//if the path has already been computed return 
		if(dp[i][j] != -1)
			return dp[i][j];
		//Check for the number above the current and proceed in that direction
		if(i > 0 && matrix[i][j] + 1 == matrix[i-1][j]){
			return dp[i][j] = 1 + path(i - 1, j, dp, matrix, n);
		}
		//Check for the number below the current and proceed in that direction
		if(i < n-1 && matrix[i][j] + 1 == matrix[i+1][j]){
			return dp[i][j] = 1 + path(i + 1, j, dp, matrix, n);
		}
		//Check for the number left of the current and proceed in that direction
		if(j > 0 && matrix[i][j] + 1 == matrix[i][j-1]){
			return dp[i][j] = 1 + path(i, j - 1, dp, matrix, n);
		}
		//Check for the number right of the current and proceed in that direction
		if(j < n-1 && matrix[i][j] + 1 == matrix[i][j+1]){
			return dp[i][j] = 1 + path(i, j + 1, dp, matrix, n);
		}
		//If it has no numbers greater than 1 around it, then that is the end
		return dp[i][j] = 1;
	}
	public static void main(String[] args){
//		int[][] matrix = {{1, 2, 9},
//        				  {5, 3, 8},
//        				  {4, 6, 7}};
//		int[][] dp = new int[matrix.length][matrix.length];
//		int max = 0;
//		for(int i =0 ;i < matrix.length;i++){
//			for(int j = 0;j < matrix.length;j++){
//				dp[i][j] = -1;
//			}
//		}	
//		for(int i =0 ;i < matrix.length;i++){
//			for(int j = 0;j < matrix.length;j++){
//				dp[i][j] = path(i, j, dp, matrix,matrix.length);
//				if(max < dp[i][j])
//					max = dp[i][j];
//			}
//		}
//		System.out.println(max);
		Scanner in = new Scanner(System.in);
	    int t = in.nextInt();
	    while(t-- > 0){
	        int n = in.nextInt();
	        int[][] matrix = new int[n][n];
	        for(int k = 0 ; k < n ;k++){
	           for(int l = 0;l < n;l++){
	               matrix[k][l] = in.nextInt();
	           }
	        }
	    int[][] dp = new int[n][n];
		int max = 0;
		for(int i =0 ;i < matrix.length;i++){
			for(int j = 0;j < matrix.length;j++){
				dp[i][j] = -1;
			}
		}	
		for(int i = n - 1 ;i >= 0  ;i--){
			for(int j = n - 1;j >= 0;j--){
				dp[i][j] = highestpath(i, j, matrix, n,dp);
				if(max < dp[i][j])
					max = dp[i][j];
			}
		}
		System.out.println(max);   
	    }
	}
	private static int max(int a , int b, int c){
		return Integer.max(a, Integer.max(b, c));
	}
	/*Problem Statement
	 * https://practice.geeksforgeeks.org/problems/path-in-matrix/0
	 * Given a N X N  matrix Matrix[N][N] of positive integers.  There are only three possible moves from a cell Matrix[r][c].

		1. Matrix[r+1][c]

		2. Matrix[r+1][c-1]

		3. Matrix[r+1][c+1]

		Starting from any column in row 0, return the largest sum of any of the paths up to row N-1.
	 */
	/**
	 * Algo:
	 * It is a bottom-up approach
	 * Calculate all the paths from bottom up manner using the formula
	 * dp[i][j] = matrix[i][j] + Integer.max(dp[i+1][j] , dp[i+1][j + 1]);
	 * 
	 * Complexity Analysis:
	 * Time Complexity : O(n^2)
	 * Space Complexity : O(n^2)
	 * @param i
	 * @param j
	 * @param matrix
	 * @param n
	 * @param dp
	 * @return
	 */
	//Bottom - Up Approach
	public static int highestpath(int i, int j, int[][] matrix,int n,int[][] dp){
		//Check for boundary violations
		if(i < 0 && i > n && j < 0 && j > n )
			return 0;
		if(i < n-1 && (j != 0 || j != n-1)){
			if(j == 0){
				return dp[i][j] = matrix[i][j] + Integer.max(dp[i+1][j] , dp[i+1][j + 1]);
			}else if(j == n - 1){
				return dp[i][j] = matrix[i][j] + Integer.max(dp[i+1][j] , dp[i+1][j - 1]);
			}else{
				return dp[i][j] = matrix[i][j] + max(dp[i+1][j] , dp[i+1][j -1] , dp[i+1][j + 1]);
			}
		}else{
			return dp[i][j] = matrix[i][j];
		}
	}
	/**
	 * @param i
	 * @param j
	 * @param matrix
	 * @param n
	 * @param dp
	 * @return
	 */
	//Top - Down Approach - This didnt work for some cases
	//1
	//17
	//67 280 171 381 930 781 925 4 393 380 246 433 762 258 5 166 315 503 385 728 854 350 464 288 304 80 689 56 313 843 92 379 122 614 111 403 394 387 406 138 767 651 571 880 260 927 398 926 429 782 653 634 132 468 274 435 548 314 490 212 156 933 942 629 546 404 31 292 142 436 781 260 86 703 140 697 630 537 622 410 318 275 44 801 94 669 236 993 982 77 204 137 10 497 765 907 900 147 550 42 582 331 301 19 33 792 715 14 680 336 424 350 962 467 150 408 135 737 400 468 814 956 956 175 452 72 433 704 218 983 97 799 665 749 169 49 541 883 63 572 570 486 921 884 304 423 291 790 159 42 257 324 997 212 498 801 283 283 504 500 617 952 650 281 700 818 329 592 52 743 164 621 228 436 856 883 858 498 672 17 540 928 340 536 139 190 336 773 472 191 272 88 142 921 720 842 90 400 433 141 143 948 114 722 384 969 605 593 819 276 961 358 556 301 893 46 842 581 819 665 771 90 104 265 363 823 106 452 574 890 945 68 190 58 790 925 378 746 517 196 373 478 905 280 130 798 326 323 730 144 987 500 585 90 764 947 264 221 751 837 463 47 257 652 456 46 576 185 143 444 381 867 921 285 147 402 434 472 724 163 615 710 15 551 151 130 498 414 703
	public static int highestWeightPath(int i, int j, int[][] matrix,int n,int[][] dp){
		//Check for boundary violations
		if(i < 0 && i > n && j < 0 && j > n )
			return 0;
		//Check for already computed
		if(dp[i][j] != -1)
			return dp[i][j];
		//Check for the highest option avaialble
		if(i < n-1 && (j != 0 || j != n-1)){
			if(j > 0 && j < n -1){
				if(matrix[i+1][j] > matrix[i+1][j-1]){
					if(matrix[i+1][j] > matrix[i+1][j+1]){
						return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j, matrix, n, dp);
					}else{
						return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j + 1, matrix, n, dp);
					}
				}else{
					if(matrix[i+1][j-1] > matrix[i+1][j+1]){
						return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j - 1, matrix, n, dp);
					}else{
						return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j + 1, matrix, n, dp);
					}
				}
			}else if(j == 0){
				if(matrix[i+1][j] > matrix[i+1][j+1]){
					return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j, matrix, n, dp);
				}else{
					return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j + 1, matrix, n, dp);
				}
			}else{
				if(matrix[i+1][j] > matrix[i+1][j-1]){
					return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j, matrix, n, dp);
				}else{
					return dp[i][j] = matrix[i][j] + highestWeightPath(i + 1, j - 1, matrix, n, dp);
				}
			}
		}else{
			return dp[i][j] = matrix[i][j];
		}
	}
}
