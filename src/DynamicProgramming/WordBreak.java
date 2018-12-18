//$Id$
package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Problem Statement:
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. 
 * 	Consider the following dictionary 
	{ i, like, sam, sung, samsung, mobile, ice, 
	  cream, icecream, man, go, mango}
	
	Input:  ilike
	Output: Yes 
	The string can be segmented as "i like".
	
	Input:  ilikesamsung
	Output: Yes
	The string can be segmented as "i like samsung" 
	or "i like sam sung".
 * 
 * Reference:
 * https://www.geeksforgeeks.org/word-break-problem-dp-32/
 * 
 * @author gokul-4406
 *
 */
public class WordBreak {
	
	static HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	static{
//		String[] dict = {"apple", "pen", "applepen", "pine", "pineapple"};
		String[] dict = {"mobile","samsung","sam","sung","man","mango", 
                "icecream","and","go","i","like","ice","cream"};
		for(String word:dict){
			wordMap.put(word, 0);
		}
	}
	public static boolean containsWord(String word){
		return wordMap.containsKey(word);
	}
	/*
	 * Approach1:
	 * Dynamic Programming
	 * 
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(n)
	 */
	public static boolean wordBreak(String str){
		int size = str.length();
		//Base Case
		if(size == 0){
			return true;
		}
		boolean status[] = new boolean[size+1];
		for(int i = 1; i <=size;i++){
			if(status[i] == false && containsWord(str.substring(0, i))){
				status[i] = true;
			}
			if(status[i]){
				//If the index has reached the last prefix
				if(i == size){
					break;
//					printDP(status, size);
//					return true;
				}
				for (int j = i+1; j <= size; j++) 
	            {
	                // Update status[j] if it is false and can be updated 
	                // Note the parameter passed to dictionaryContains() is 
	                // substring starting from index 'i' and length 'j-i' 
	                if (status[j] == false && containsWord(str.substring(i, j))){
//	                	System.out.println(str.subSequence(i, j));
	                	status[j] = true;
	                }
	                // If we reached the last character 
	                if (j == size && status[j] == true){
//	                	printDP(status, size);
//	                	return true;
	                	break;
	                }
	            } 
			}
		}
		for (int i = 0; i <= size; i++) {
			System.out.println(status[i]);
		}
		if(status[size]){
			String result = "";
			printCombinations(str, size, result);
//			printPossibleCombinations(str, status);
		}
		return status[size];
	}
	public static void printDP(boolean[] status,int size){
		for (int i = 0; i <= size; i++) {
			System.out.println(status[i]);
		}
	}
	public static void printPossibleCombinations(String sentence,boolean[] status){
		//We need to iterate through the status array and check for true
		int n = status.length;
		//Maintaining a stack to keep track of combined words
		Stack<String> stack = new Stack<String>();
		List<String> temp = new ArrayList<String>();
		int prev = 0;//This maintains the index of previously encountered true
		for(int i = 0 ;i < n ;i++){
			if(status[i]){
				String word = sentence.substring(prev, i);
				//Now we know that this will make a word in the dictionary. But we need to check if this will combine with previous words to form another word
				while(!stack.isEmpty()){
					String prefix = stack.pop();
					if(containsWord(prefix+word)){
						temp.add(prefix+word);
					}else{
						stack.clear();
					}
				}
				stack.add(word);
				for(String str:temp){
					stack.add(str);
					System.out.println(str);
				}
				temp.clear();
				prev = i;
			}
		}
	}
	public static void printCombinations(String sentence,int n, String result){
		for(int i = 1 ; i <= n;i++){
			String prefix = sentence.substring(0, i);
			if(containsWord(prefix)){
				if(i == n){
					result+= prefix;
					System.out.println(result);
					return;
				}
				printCombinations(sentence.substring(i, n), n - i, result+prefix+" ");
			}
		}
	}
	public List<String> possibleCombinations(String str,int n,List<String> result){
		for(int i = 1;i <= n;i++){
			String prefix = str.substring(0, i);
			if(containsWord(prefix)){
				//Base Case
				if(i == n){
					result.add(prefix);
					System.out.println(prefix);
					return result;
				}
				return possibleCombinations(str.substring(i, n), n - i, result);
			}
		}
		return result;
	}
	public static void main(String[] args){
		wordBreak("samsungmango");
//		printCombinations("pineapplepenapple", "pineapplepenapple".length(), "");
	}
}
