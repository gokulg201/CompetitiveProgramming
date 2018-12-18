//$Id$
package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement:
 * Given a sequence of words, and a limit on the number of characters that can be put 
 * in one line (line width). Put line breaks in the given sequence such that the 
 * lines are printed neatly
 * 
 * Solution:
 * Badness - We define badness has square of empty spaces in every line. So 2 empty space
 * on one line gets penalized as 4 (2^2) while 1 each empty space on 2 lines gets
 * penalized as 2(1 + 1). So we prefer 1 empty space on different lines over 2 empty space on
 * one line.
 * 
 * For every range i,j(words from i to j) find the cost of putting them on one line. If words 
 * from i to j cannot fit in one line cost will be infinite. Cost is calculated as square of
 * empty space left in line after fitting words from i to j.
 * 
 * Then apply this formula to get places where words need to be going on new line.
 * minCost[i] = minCost[j] + cost[i][j-1]
 * Above formula will try every value of j from i to len and see which one gives minimum 
 * cost to split words from i to len.
 * 
 * Space complexity is O(n^2)
 * Time complexity is O(n^2)
 * 
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * 
 */
public class TextJustification {
	public static List<String> justify(String[] words, int width){
		int n = words.length;
		//Form the cost matrix
		int[][] cost = costMatrix(words, width);
		int[] minCost = new int[n];
		int[] result = new int[n];
		
		for(int i = n - 1; i >= 0;i--){
			minCost[i] = cost[i][n - 1];
			result[i] = n;
			for(int j = n - 1; j > i;j--){
				if(cost[i][j - 1] == Integer.MAX_VALUE){
					continue;
				}
				int tempCost = cost[i][j - 1] + minCost[j];
				if(minCost[i] > tempCost){
					minCost[i] = tempCost;
					result[i] = j;
				}
			}
		}
		return formWordWrap(words, result);
	}
	public static int[][] costMatrix(String[] words, int width){
		int n = words.length;
		int[][] cost = new int[n][n];
		for(int i = 0; i < n; i++){
			cost[i][i] = width - words[i].length();
			for(int j = i + 1; j < n;j++){
				cost[i][j] = cost[i][j - 1] -words[j].length() - 1;//1 for the space
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = i; j < n;j++){
				if(cost[i][j] < 0){
					cost[i][j] = Integer.MAX_VALUE;
				}else{
					cost[i][j] = (int) Math.pow(cost[i][j], 2);
				}
			}
		}	
//		printCostMatrix(cost);
		return cost;
	}
	private static void printCostMatrix(int[][] cost){
		for(int i = 0 ; i < cost.length;i++){
			for(int j = 0;j < cost[0].length;j++){
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static List<String> formWordWrap(String[] words, int[] result){
		List<String> wrap = new ArrayList<String>();
		int n = words.length;
		int i = 0;
		do{
			String line = "";
			for(int j = i ; j < result[i]; j++){
				line+=line.equals("") ? words[j] : " "+words[j];
			}
			wrap.add(line);
			i = result[i];
		}while(i < n);
		return wrap;
	}
	public static void print(List<String> wrap){
		for(String str: wrap){
			System.out.println(str);
		}
	}
	public void refineWords(List<String> wrap, int width){
		for(String words:wrap){
			String[] str = words.split(" ");
 		}
	}
	/**
	 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
	 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth 
	 * characters.Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the 
	 * left will be assigned more spaces than the slots on the right. For the last line of text, it should be left justified and no extra space is inserted between words.
	 * 
	 */
	public static List<String> greedyAppraoch(String[] words, int maxWidth){
		List<String> wrap = new ArrayList<String>();
		int n = words.length;
		int first = 0;
		while(first < n){
			int last = first + 1;
			int width = words[first].length();
			while(last < n){
				if(width + words[last].length() + 1 > maxWidth){
					break;
				}
				width += words[last++].length() + 1;
			}
			int noOfSpaces = last - first - 1;//This is the no of spaces required to separate the words to form a sentence.
			StringBuilder builder = new StringBuilder();
			if(noOfSpaces == 0 || last == n){
				for(int i = first ; i < last;i++){
					builder.append(words[i]+ " ");
				}
				builder.deleteCharAt(builder.length() - 1);
				for(int i = builder.length(); i < maxWidth;i++){
					builder.append(" ");//Filling the rest with spaces
				}
			}else{
				int evenSpaces = (maxWidth - width) / noOfSpaces;
				int extraSpaces = (maxWidth - width) % noOfSpaces;
				for(int i = first ; i < last - 1;i++){
					builder.append(words[i]);
					//Now add spaces required
					for(int j = 0 ; j <= evenSpaces + ((i - first) < extraSpaces ? 1 : 0);j++){//It is less than or equal since it has to include the mandatory space required to separate the words
						builder.append(" ");
					}
				}
				builder.append(words[last - 1]);
			}
			wrap.add(builder.toString());
			first = last;
		}
		return wrap;
	}
	public static void main(String[] args){
		String[] words = {"This","is","an","example","of","text","jsutification."};
//		String[] words = {"aaa","bb","cc","ddddd"};
		print(greedyAppraoch(words, 16));
	}
}
