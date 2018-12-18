//$Id$
package DynamicProgramming;

/**
 * Problem Statement:
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together. 
 * The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 * We have many options to multiply a chain of matrices because matrix multiplication is associative. 
 * In other words, no matter how we parenthesize the product, the result will be the same.
 *  
 * For example, if we had four matrices A, B, C, and D, we would have:
 * (ABC)D = (AB)(CD) = A(BCD) = ....
 * 
 * However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. 
 *  
 * For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. 
 * Then,
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
   A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 * Clearly the first parenthesization requires less number of operations.
 * 
 * Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i]. 
 * We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain.
 * 
 * Input: p[] = {40, 20, 30, 10, 30}   
 * Output: 26000
 * There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
 * Let the input 4 matrices be A, B, C and D.  The minimum number of 
   multiplications are obtained by putting parenthesis in following way
   (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
 * Input: p[] = {10, 20, 30, 40, 30} 
   Output: 30000
 * There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30.
 * Let the input 4 matrices be A, B, C and D.  The minimum number of multiplications are obtained by putting parenthesis in following way
 * ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30
 * Input: p[] = {10, 20, 30}
 * Output: 6000
 * There are only two matrices of dimensions 10x20 and 20x30. So there is only one way to multiply the matrices, cost of which is 10*20*30
 *      
 * @author gokul-4406
 *
 */
public class MatrixChainMultiplication {
	/*
	 * Dynamic programming solution
	 * 
	 */
	public static int minimumCostMultiplication(int[] dimensions){
		int n = dimensions.length;
		//Cost matrix
		int[][] costs = new int[n][n];
		//Paranthesis matrix
		int[][] paranthesis = new int[n][n];

		//Filling the diagonal elements
		for(int i = 0; i < n;i++){
			costs[i][i] = 0;
		}
		//Increasing length of no of matrices picked
		for(int len = 1; len < n;len++){
			for(int i = 1; i < n - len ; i++){
				int j = i + len ;
				costs[i][j] = Integer.MAX_VALUE;
				//Now setting paranthesis positions
				for(int k = i ; k < j ;k++){
					int count = costs[i][k] + costs[k+1][j] + (dimensions[i-1] * dimensions[k] * dimensions[j]);
					if(costs[i][j] > count){
						costs[i][j] = count;
						paranthesis[i][j] = k;// Setting a paranthesis at k
					}
				}
			}
		}
		printOptimalParenthesizations(1, n-1, paranthesis, 'A');
		return costs[1][n-1];
	}
	public static char printOptimalParenthesizations(int i , int j ,int[][] paranth, char name) {
        if(i != j){
        	System.out.print('(');
            name = printOptimalParenthesizations(i, paranth[i][j], paranth, name);
        }
        if(i == j){
        	System.out.print(name++);
        	return name;
        }
        if(i != j){
        	name = printOptimalParenthesizations(paranth[i][j] + 1, j, paranth, name);
        	System.out.print(')');
        }
        return name;
    }

	/*
	 * Recursive Solution
	 * T(i,j) = min(T[i][k] + T[k+1][j] + dimensions[i-1]*dimensions[k]*dimensions[j]) where k grows from i + 1 to j - 1
	 */
	//Here i starts from 1 
	public int minCostMultiplication(int[] dimensions, int i, int j){
		//Base case
		if(i == j){
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for(int k = i;k < j;k++){
			int count = minCostMultiplication(dimensions , i , k) + minCostMultiplication(dimensions,k + 1, j) + dimensions[i-1] * dimensions[k] * dimensions[j];
			if(min > count){
				min = count;
			}
		}
		return min;
	}
	public static void main(String[] args){
		int arr[] = {40, 20, 30, 10, 30}; 
//		int arr[] = {5,4,6,7,2}; 
//		int arr[] = {2,3,6,4,5}; 
	    minimumCostMultiplication(arr); 
	}
}
