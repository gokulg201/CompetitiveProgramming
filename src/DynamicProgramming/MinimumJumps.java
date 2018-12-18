//$Id$
package DynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Problem Statement:
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 * If an element is 0, then cannot move through that element.
 * 
 * Reference:
 * https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 * @author gokul-4406
 *
 */
public class MinimumJumps {
	public static void main (String[] args)
	 {
	    Scanner in = new Scanner(System.in);
	    int t = in.nextInt();
	    while(t-- > 0){
	        int n = in.nextInt();
	        int[] arr = new int[n];
	        for(int i =0 ;i < n ;i++){
	            arr[i] = in.nextInt();
	        }
	        System.out.println(method2(arr,n));
	    }
	 }
	/*
	 * Dynamic Programming version of Solution.
	 * 
	 * Reference:
	 * https://www.youtube.com/watch?v=cETfFsSTGJI
	 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MinJumpToReachEnd.java
	 * 
	 * Algo:
	 * Set two pointers i and j 
	 * i moves through the array from 0 to n-1
	 * j moves from 0 to i
	 * if(i<=j+arr[j]), then it means we can reach i from j
	 * So, dp[i] = dp[j] + 1 //Extra jump from j
	 * 
	 * Time Complexity : O(n^2)
	 * Space Complexity : O(n)
	 */
	public static int method1(int[] arr, int n){
		//Maintains no of minimum jumps required to reach the curent element
		int[] jumps = new int[n];
		//Since no jumps are required to reach the first element from start
		jumps[0] = 0;
		//Traverse through the array
		for(int i = 1 ;i < n ;i++){
			jumps[i] = Integer.MAX_VALUE;
			for(int j = 0 ; j < i ;j++){
				//Check if i can be reached from j
				if(i >= j + arr[j] &&(jumps[j] != Integer.MAX_VALUE && jumps[j]!= -1)){
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
				}
			}
			//This means we cannot reach this element by any means
			if(jumps[i] == Integer.MAX_VALUE){
				jumps[i] = -1;
			}
		}
		return jumps[n-1];
	}
	 /*
	  * BFS version of the solution
	  * Time Complexity : O(n)
	  * Since we visit all the nodes utmost once
	  */
	public static int method2(int[] arr, int n){
		//Base condition
	     if(n == 0 || arr[0] == 0){
	         return -1;//Since we cannot move if first element is 0
	     }
		//Maintain a queue that will hold the index and the no of jumps
		Queue<Data> queue = new LinkedList<Data>();
		Data data = new Data(0,0);
		boolean[] visited = new boolean[n];
		queue.add(data);
		visited[0] = true;
		
		int jumps = 0;
		//Run the loop while queue is not empty
		while(!queue.isEmpty()){
			//Remove the first element
			Data currentData = queue.remove();
			int currentIndex = currentData.index;
			int currentSteps = currentData.steps;
			//Loop exit condition
			//If we have reached the last element exit
			if(currentIndex == n - 1){
				jumps = currentSteps;
				return jumps;
			}
			int steps = arr[currentIndex];//No of steps it can take from the current index
			//BFS
			for(int i = 1;i <= steps;i++){
				//If the reachable index is not out of bounds
				//And the element is not already visited
				if(currentIndex + i <= n - 1 && visited[currentIndex + i] != true){
					//The index will be i greater than the current index
					//The steps will be 1 more from the current no of steps, as all the elements in the loop can be reached by 1 jump from currentindex
					Data next = new Data(currentIndex + i, currentSteps + 1);
					queue.add(next);
					visited[currentIndex + i] = true;
				}
			}
		}
		return -1;
	}
	static class Data{
		int index;
		int steps;
		Data(int index, int steps){
			this.index = index;
			this.steps = steps;
		}
	}
	/*
	 * Optimised O(n)
	 * Logic similar to BFS
	 */
	public static int method3(int[] arr, int n){
		//Maintaining three variables
		int max_reach = arr[0];
		int stepsLeft = arr[0];
		int jumps = 0;

		for(int i = 1 ;i < n;i++){
			if(i == n - 1){
				return jumps;
			}
			//Update the max_reach from current position
			max_reach = Math.max(max_reach,i + arr[i]); //The maximum index that can be reached from the current index 

			stepsLeft--;

			if(stepsLeft == 0){//We have reached the maximum jump point from the element to which we had jumped during our last jump
				//Now we need to jump 
				jumps++;
				//If the current is index greater than the maximum reacheble point or equal to then return -1
				//As this index will not be reachable
				if(i >= max_reach){
					return -1;
				}
				//No of steps left is the no of steps it can take from current index upto max_reach
				stepsLeft = max_reach - i;
			}	
		}
		return -1;
	}
}
