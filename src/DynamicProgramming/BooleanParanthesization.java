//$Id$
package DynamicProgramming;

import java.util.Scanner;

/**
 * Problem Statement:
 * Given a boolean expression with following symbols.
 * Symbols
    'T' ---> true 
    'F' ---> false 
 * And following operators filled between symbols
 * Operators
    &   ---> boolean AND
    |   ---> boolean OR
    ^   ---> boolean XOR 
 * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
 * Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators (&, | and ^}
 *   
 * Reference:
 * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
 * 
 * @author gokul-4406
 *
 */
public class BooleanParanthesization {
	public static void main (String[] args)
	 {
	    Scanner in = new Scanner(System.in);
	    int t = in.nextInt();
	    while(t-- > 0){
	        int n = in.nextInt();
	        String expression = in.next();
	        char[] symbol = new char[n/2 + 1];
	        char[] operator = new char[n/2];
	        for(int i = 0 ;i < n/2 + 1;i++){
	            symbol[i] = expression.charAt(i*2);
	            if(i != n/2){
	                operator[i] = expression.charAt(2*i + 1);   
	            }
	        }
	        System.out.println(maximumPossibleCombinations(symbol,operator,n/2+1) % 1003);
	    }
	 }
	/*
	 * The mathematical model behind this solution is that,
	 * T(i,j) = Math.sum{
	 * 					[T(i,k)*T(k+1,j) if operator is '&'],
	 * 					[Total(i,k)*Total(k+1,j) - F(i,k)*F(k+1,j) if operator is '|']
	 * 					[T(i,k)*F(k+1,j) + F(i,k)*T(k+1,j) if operator is '^']
	 * 					} where k = i to j-1;
	 * F(i,j) = Math.sum{
	 * 					[Total(i,k)*Total(k+1,j) - T(i,k)*T(k+1,j) if operator is '&'],
	 * 					[F(i,k)*F(k+1,j) if operator is '|']
	 * 					[T(i,k)*F(k+1,j) + F(i,k)*T(k+1,j) if operator is '^']
	 * 					} where k = i to j-1;
	 * Total(i,j) = T(i,j) + F(i,j)
	 * 
	 * Dynamic Programming Approach:
	 * This takes a diagonal appraoch like optimal strategy game plan problem
	 * i.e., All the diagonal elements are filled and the problem is proceeded from diagonal
	 * 
	 */
	public static long maximumPossibleCombinations(char symb[], char oper[], int n){
		//Create 2 arrays to hold Truth table and False table
		long[][] T = new long[n][n];
		long[][] F = new long[n][n];
		/*Base cases
		* T(i,i) = 1 if symbol is T
		*        = 0 if symbol is T
		* F(i,i) = 1 if symbol is F
		*        = 0 if symbol is F
		*/
		for(int i = 0 ; i < n;i++){
			if(symb[i] == 'T'){
				T[i][i] = 1;
			}else{
				F[i][i] = 1;
			}
 		}
 		// Now fill T[i][i+1], T[i][i+2], T[i][i+3]... in order 
    	// And F[i][i+1], F[i][i+2], F[i][i+3]... in order 
 		for(int length = 1; length < n; ++length){ // This loop denotes the no of elements(symbols) we are taking for consideration
 			for(int i = 0, j = length; j < n;++i,++j){// i loops from 0 to n - 1 as we have to run for all symbols; j loops from i to n - 1; i.e., from the diagonal to the right
 				T[i][j] = F[i][j] = 0; 
 				//now we have to choose a point of parenthesization
 				// g is less than the gap because it must fall between i to n - 1
 				for(int g = 0 ;g < length ; g++){
 					int k = i + g;
 					//Now that we have fixed k, we can start calculating T[i][j] and F[i]][j]
 					long total_i_k = T[i][k] + F[i][k];
 					long total_k_j = T[k+1][j] + F[k+1][j];
 					//As per the math function stated above
 					if(oper[k] == '&'){
 						T[i][j]+= (T[i][k] * T[k+1][j]);
 						F[i][j]+= (total_i_k*total_k_j - (T[i][k] * T[k+1][j]));
 					}
 					if(oper[k] == '|'){
 						T[i][j]+= (total_i_k*total_k_j - (F[i][k] * F[k+1][j]));
 						F[i][j]+= (F[i][k]*F[k+1][j]);
 					}
 					if(oper[k] == '^'){
 						T[i][j]+= (T[i][k] * F[k+1][j] + F[i][k] * T[k+1][j]);
 						F[i][j]+= (T[i][k] * T[k+1][j] + F[i][k] * F[k+1][j]);
 					}
 				}
 			}
 		}
 		return T[0][n - 1];
 		//return F[0][n - 1]; //If the problem statement was to find the maximum no of combinations to achieve False
	}
	/*
	 * 
	 * For understanding the math function
	 */
	public int recursiveSolution(char symb[], char oper[], int n){
		return truthTable(symb, oper, n, 0, n - 1);
	}
	/*
	 * This is a recursive function to find T(i,j)
	 */
	private int truthTable(char symb[], char oper[], int n,int i, int j){
		int ways = 0;
		//Base Case
		if(i == j){
			if(symb[i] == 'T'){
				return 1;
			}else{
				return 0;
			}
		}
		/*
		 * T(i,j) = Math.sum{
		 * 					[T(i,k)*T(k+1,j) if operator is '&'],
		 * 					[Total(i,k)*Total(k+1,j) - F(i,k)*F(k+1,j) if operator is '|']
		 * 					[T(i,k)*F(k+1,j) + F(i,k)*T(k+1,j) if operator is '^']
		 * 					} where k = i to j-1;
		 */
		for(int k = i ; k < n;k++){
			if(oper[i] == '&'){
				ways+= truthTable(symb, oper, n, i, k) * truthTable(symb, oper, n, k+1, j);
			}
			if(oper[i] == '|'){
				int total_i_k = truthTable(symb, oper, n, i, k) + falseTable(symb, oper, n, i, k);
				int total_k_j = truthTable(symb, oper, n, k+1, j) + falseTable(symb, oper, n, k+1, j);
				ways+= (total_i_k * total_k_j) - (falseTable(symb, oper, n, i, k) * falseTable(symb, oper, n, k+1, j));
			}
			if(oper[i] == '^'){
				ways+= (truthTable(symb, oper, n, i, k) * falseTable(symb, oper, n, k+1, j)) + (falseTable(symb, oper, n, i, k) * truthTable(symb, oper, n, k+1, j));
			}
		}
		return ways;
	}
	/*
	 * This is a recursive function to find F(i,j)
	 */
	private int falseTable(char symb[], char oper[], int n,int i, int j){
		int ways = 0;
		//Base Case
		if(i == j){
			if(symb[i] == 'F'){
				return 1;
			}else{
				return 0;
			}
		}
		/*
		 * F(i,j) = Math.sum{
		 * 					[Total(i,k)*Total(k+1,j) - T(i,k)*T(k+1,j) if operator is '&'],
		 * 					[F(i,k)*F(k+1,j) if operator is '|']
		 * 					[T(i,k)*F(k+1,j) + F(i,k)*T(k+1,j) if operator is '^']
		 * 					} where k = i to j-1;
		 */
		for(int k = i ; k < n;k++){
			if(oper[i] == '&'){
				int total_i_k = truthTable(symb, oper, n, i, k) + falseTable(symb, oper, n, i, k);
				int total_k_j = truthTable(symb, oper, n, k+1, j) + falseTable(symb, oper, n, k+1, j);
				ways+= (total_i_k*total_k_j) - (truthTable(symb, oper, n, i, k) * truthTable(symb, oper, n, k+1, j));
			}
			if(oper[i] == '|'){
				ways+= falseTable(symb, oper, n, i, k) * falseTable(symb, oper, n, k+1, j);
			}
			if(oper[i] == '^'){
				ways+= (truthTable(symb, oper, n, i, k) * falseTable(symb, oper, n, k+1, j)) + (falseTable(symb, oper, n, i, k) * truthTable(symb, oper, n, k+1, j));
			}
		}
		return ways;
	}
}
