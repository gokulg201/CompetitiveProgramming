//$Id$
package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class StockBuySell {
	/**
	 * Dynamic programming approach based on tushar's inputs. This has a space complexity of O(n^2)
	 * Reference:
	 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
	 * https://www.youtube.com/watch?v=oDhu5uGq_ic
	 */
	public static int maxProfit_dp(int[] prices,int k){
		int n = prices.length;
		int[][] dp = new int[k+1][n];
		for(int i = 0;i <= k;i++){
			int maxDiff = -prices[0];
			for(int j = 0 ;j < n;j++){
				//Base Cases
				if(i == 0){
					dp[i][j] = 0; //Since with no transactions we cannot achieve any profit
					continue;
				}
				if(j == 0){
					dp[i][j] = 0;// Since we need 2 days to complete a transcation
					continue;
				}
				//Formula
				dp[i][j] = Math.max(dp[i][j - 1] //i.e., No transaction on j'th day
							, maxDiff+ prices[j]) ;//Selling on jth day
				maxDiff = Math.max(maxDiff, dp[i-1][j] - prices[j]);
 			}
		}
		return dp[k][n-1];
	}
	public void printActualSolution(int T[][], int prices[]) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j-1; k >= 0; k--) {
                    if (T[i-1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }
        while(!stack.isEmpty()) {
            System.out.println("Buy at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at price " + prices[stack.pollFirst()]);
        }
    }
	/**
	 * Dynamic programming approach based on tushar's inputs. This has a space complexity of O(n)
	 * Reference:
	 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
	 * https://www.youtube.com/watch?v=oDhu5uGq_ic
	 * 
	 */
	public static int maxProfit_optimised(int[] prices,int k){
		int n = prices.length;
		if(n == 0){
			return 0;
		}
		int[] dp = new int[n];
		int[] dp_cache = new int[n];
		for(int i = 1;i <= k;i++){
			int maxDiff = -prices[0];
			for(int j = 1 ;j < n;j++){
				//Formula
				dp[j] = Math.max(dp[j - 1] //i.e., No transaction on j'th day
							, maxDiff+ prices[j]) ;//Selling on jth day
				maxDiff = Math.max(maxDiff, dp_cache[j] - prices[j]);
				dp_cache[j] = dp[j];
 			}
		}
		return dp[n-1];
		/*
		 * Another backward looping approach. 
		 * Reference:
		 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
		 * 
		int[] T_ik0 = new int[k + 1];
	    int[] T_ik1 = new int[k + 1];
	    Arrays.fill(T_ik1, Integer.MIN_VALUE);
	        
	    for (int price : prices) {
	        for (int j = k; j > 0; j--) {
	            T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
	            T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
	        }
	    }
	        
	    return T_ik0[k];
		 */
	}
	/**
	 * Problem Statement:
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	 * Note that you cannot sell a stock before you buy one.
	 * 
	 * Solution:
	 * Find the local minimum and local maximum. The difference gives the max profit
	 * 
	 * Reference:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
	 * https://www.youtube.com/watch?v=76-CYD0jn7s
	 * 
	 */
	public static int maxProfitForSingleTransaction(int[] prices){
		int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, - price);
        }
        return T_ik0;
        /*
         * Another simplified approach, which follows the same idea as above but simple implementation
		if(prices==null || prices.length ==0)
            return 0;
        int min = Integer.MAX_VALUE, maxProfit = Integer.MIN_VALUE;
        for(int price : prices){
            min = Math.min(min,price);
            maxProfit = Math.max(maxProfit, price - min);
        }
        return maxProfit;
         */
	}
	/**
	 * Problem Statement:
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
	 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
	 * 
	 * Reference:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
	 * https://www.youtube.com/watch?v=Taq95cvRom8
	 * 
	 */
	public static int maxProfitForInfiniteTransactions(int[] prices){
		int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0 - price);
        }
        return T_ik0;
        
        /*Another simplified approach which does the same as above
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
         */
	}
	/**
	 * Problem Statement:
	 * Kadane's Algorithm comes in handy when the input set does not contain the stock prices but rather contains difference in stock prices from the preivous day
	 * For Eg: for {1, 7, 4, 11}, the interviewer gives an input of {0, 6, -3, 7}
	 * 
	 * Solution:
	 * Instead of finding for a positive contiguous sum of prices, we find a positive contiguous sum of differences 'maxCur += prices[i] - prices[i-1]'
	 * 
	 */
	public static int maxProfitForDifferenceInStockPrices(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
	/**
	 * Problem Statement:
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
	 * Note:
	 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 * 
	 * Reference:
	 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
	 * https://www.youtube.com/watch?v=oDhu5uGq_ic
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
	 * 
	 */
	public static int maxProfitForKTransactions(int[] prices,int k){
		if (k >= prices.length / 2) {
			int len = prices.length, profit = 0;
	        for (int i = 1; i < len; i++)
	            // as long as there is a price gap, we gain a profit.
	            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
	        return profit;
		}
		return maxProfit_optimised(prices, k);//This is the optimised DP approach by Tushar
	}
	/**
	 *Problem Statement:
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
	 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
	 * 
	 * Reference:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
	 */
	public static int maxProfitForInfiniteTransactionsWithCoolDown(int[] prices){
		int T_ik0pre = 0,T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
        	int T_ik0old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0pre - price);
            T_ik0pre = T_ik0old;
        }
        return T_ik0; 
	}
	/**
	 *Problem Statement:
	 *Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
	 *You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
	 *You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
	 *Return the maximum profit you can make. 
	 *
	 *Reference:
	 *https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/111002
	 * 
	 */
	public static int maxProfitForInfiniteTransactionsWithTranscationCost(int[] prices, int fee){
		int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
	    for (int price : prices) {
	        int T_ik0_old = T_ik0;
	        T_ik0 = Math.max(T_ik0, T_ik1 + price);
	        T_ik1 = Math.max(T_ik1, T_ik0_old - price - fee);
	    }
	    return T_ik0;
	}
	public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
//            int k = Integer.parseInt(line);
//            line = in.readLine();
            int[] prices = stringToIntegerArray(line);
            
//            int ret = maxProfit(prices,k);
//            int ret = maxProfit(prices);
            int ret = 0;
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
	}
}
