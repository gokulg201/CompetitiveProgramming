//$Id$
package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length; 
        if(n == 0 || target < 0){
            return null;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for(int i = 0;i < n;i++){
            dp[i][0] = true;
        }
        if(candidates[0] <= target){
            dp[0][candidates[0]] = true;
        }
        for(int i = 1;i < n;i++){
            for(int j = 0;j <= target;j++){
                dp[i][j] = candidates[i] <= j ? dp[i - 1][j] || dp[i - 1][j - candidates[i]] : dp[i - 1][j];
            }
        }
        if(dp[n-1][target]){
            return combinations(dp,candidates,n-1,target,new ArrayList<Integer>(),new ArrayList<List<Integer>>());
        }else{
            return null;
        }
    }
    public static List<List<Integer>> combinations(boolean[][] dp,int[] arr,int i, int target,List<Integer> currentList, List<List<Integer>> overallList){
        if(i == 0 && target != 0 && dp[0][target]){
            currentList.add(arr[i]);
            overallList.add(currentList);
            currentList.clear();
            return overallList;
        }
        if (i == 0 && target == 0) 
        { 
            currentList.clear();
            return overallList; 
        } 
        if(dp[i - 1][target]){
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.addAll(currentList);
            return combinations(dp, arr,i-1 , target , newList, overallList);
        }
        if(dp[i - 1][target - arr[i]] && target >= arr[i]){
            currentList.add(arr[i]);
            return combinations(dp,arr,i - 1,target - arr[i],currentList,overallList);
        }
        return null;
    }
    public static void print(List<List<Integer>> list){
    	for(List<Integer> l : list){
    		for(Integer i : l){
    			System.out.print(i+" ");
    		}
    		System.out.println();
    	}
    }
    public static void main(String args[]) 
    { 
        int arr[] = {12 ,3 ,16, 19, 11, 12, 18, 25, 22 }; 
        int n = arr.length; 
        int sum = 63; 
        print(combinationSum2(arr,sum));
    }
}