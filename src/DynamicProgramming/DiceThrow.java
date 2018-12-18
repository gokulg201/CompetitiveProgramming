//$Id$
package DynamicProgramming;

/**
 * Problem Statement:
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. 
 * X is the summation of values on each face when all the dice are thrown.
 * 
 * Reference:
 * https://www.geeksforgeeks.org/dice-throw-dp-30/
 * 
 * @author gokul-4406
 *
 */
public class DiceThrow {
	/*
	 *  Math behind the problem:
	 *  Let the function to find X from n dice is: Sum(m, n, X)
		The function can be represented as:
		Sum(m, n, X) = Finding Sum (X - 1) from (n - 1) dice plus 1 from nth dice
		               + Finding Sum (X - 2) from (n - 1) dice plus 2 from nth dice
		               + Finding Sum (X - 3) from (n - 1) dice plus 3 from nth dice
		                  ...................................................
		                  ...................................................
		                  ...................................................
		              + Finding Sum (X - m) from (n - 1) dice plus m from nth dice
		
		So we can recursively write Sum(m, n, x) as following
		Sum(m, n, X) = Sum(m, n - 1, X - 1) + 
		               Sum(m, n - 1, X - 2) +
		               .................... + 
		               Sum(m, n - 1, X - m)

	 */
    /*
    * Recursive approach
    */
	public static int noOfWays(int n,int m, int sum){
		//Base Case
		if(n == 1 && sum <= m){//If there is only 1 dice, then the sum can only be achieved
			if(sum <= m){
				return 1;
			}else{
				return 0;
			}
		}
		int noOfWays = 0;
		for(int i = 1; i <=m ;i++){
			noOfWays += noOfWays(n - 1, m , sum - i) ;
		}
		return noOfWays;
	}

	public static long noOfways_dp(int m , int n, int sum){
		//Create a matrix to denote the no of ways
		long[][] ways = new long[n+1][sum+1];
		//Base case
		//j is less than or equal to sum and no of faces in the dice coz only a sum of equal to or less than 'm' faces can be achieved using this one dice
		for(int j = 1;j <= sum && j <= m;j++){
			ways[1][j] = 1; //If we have only 1 dice then the sum can be achieved only in one way
		}
		for(int i = 2;i <=n; i++){
			for(int j = 1; j <= sum ;j++){
				//How many ways can a sum of 'j' can be achieved using 'i' dice
				for(int k = 1; k <= m && k <= j;k++){
					ways[i][j] +=ways[i - 1][j - k];// For the ith dice we are assuming it is j - k
				}
			}
		}
		//Printing the Dp array
		for(int i = 1; i <=n;i++){
			for(int j = 1; j<= sum;j++){
				System.out.print(ways[i][j]+" ");
			}
			System.out.println();
		}
		return ways[n][sum];
	}
	public static void main(String[] args){
		System.out.println(noOfways_dp(15, 18, 43));
	}
}
