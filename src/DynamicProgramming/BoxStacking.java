//$Id$
package DynamicProgramming;

import java.util.Arrays;

/**
 * Problem Statement:
 * You are given a set of n types of rectangular 3-D boxes, where the i^th box has height h(i), width w(i) and depth d(i) (all real numbers). 
 * You want to create a stack of boxes which is as tall as possible, but you can only stack a box on top of another box
 * if the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher box.
 * Of course, you can rotate a box so that any side functions as its base. 
 * It is also allowable to use multiple instances of the same type of box.
 * 
 * Reference : https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
 * 
 * @author gokul-4406
 *
 */
public class BoxStacking{
	static class Box implements Comparable<Box>{
		int height;
		int width;
		int depth;
		int area;
		Box(int height, int width, int depth){
			this.height = height;
			this.width = width;
			this.depth = depth;
		}
		public int compareTo(Box b1){
			return b1.area - this.area;
		}
	}
	/*
	 * Algo:
	 * 1) Form all the rotations i.e., different combinations for a given box interchanging the length of its various sides
	 * 2) Calculate the maximum stack height for each box considering it will form the top of the stack msh[]
	 * 3) Implement LIS algorithm to find the maximum stack height of each rotation of the box
	 * 4) Run through the maximum stack height array msh[] and return the max;
	 * 5) This is the maximum height that can be formed 
	 * 
	 * Time Complexity: O(n^2)
	 * Auxiliary Space: O(n)
	 * 
	 */
	public static int maxHeight(Box[] arr, int n){
		Box[] rotations = new Box[n*3];
		//Generating all possible rotations i.e., shuffling sides {1,2,3} becomes {2,1,3},{3,1,2} with width<=depth for simplicity
		for(int i = 0 ;i < n;i++){
			Box parent = arr[i];
			 rotations[i*3] = new Box(parent.height, Math.max(parent.width,parent.depth),Math.min(parent.width,parent.depth));
			 rotations[i*3 + 1] = new Box(parent.width, Math.max(parent.depth,parent.height),Math.min(parent.depth,parent.height));
			 rotations[i*3 + 2] = new Box(parent.depth, Math.max(parent.width,parent.height),Math.min(parent.width,parent.height));
		}
		//Calculating base area of each of the boxes
        for(int i = 0; i < rotations.length; i++) 
            rotations[i].area = rotations[i].width * rotations[i].depth; 
		//Sorting in non-increasing order aof base area
		Arrays.sort(rotations);

		int rot_n = 3 * n;
		int[] msh = new int[rot_n];
		//Initialising the maximum stack heights with the box at the top of the stack
		for(int i = 1; i < rot_n;i++){
			msh[i] = rotations[i].height;
		}
		//Implement LIS for the rotations array
		for(int i = 0; i < rot_n;i++){
			Box box = rotations[i];
			int val = 0;
			for(int j = 0; j < i;j++){
				Box prevBox = rotations[j];
				if(box.width < prevBox.width && box.depth < prevBox.depth){
					val = Math.max(val,msh[j]);
				}
			}
			msh[i] = val + box.height;
		}
		int max = -1; 
        /* Pick maximum of all msh values */
        for(int i = 0; i < rot_n; i++){ 
            max = Math.max(max, msh[i]);
        } 
        return max; 
	}
	public static void main(String[] args){
		 Box[] arr = new Box[4]; 
	        arr[0] = new Box(4, 6, 7); 
	        arr[1] = new Box(1, 2, 3); 
	        arr[2] = new Box(4, 5, 6); 
	        arr[3] = new Box(10, 12, 32); 
	          
	        System.out.println("The maximum possible "+ 
	                           "height of stack is " +  
	                           maxHeight(arr,4)); 
	}
}
