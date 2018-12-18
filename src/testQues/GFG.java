//$Id$
package testQues;

/*package whatever //do not write package name here */

/**
 * Test Class 
 * @author gokul-4406
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
class GFG
 {
	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> list = new ArrayList<>();
        //position of first word in line
        int first = 0;
        while (first < words.length) {
            int width = words[first].length();
            //position of last word in line
            int last = first + 1;
            while (last < words.length && width + words[last].length() + 1 <= maxWidth) {
                width += words[last++].length() + 1;
            }
            StringBuilder sb = new StringBuilder(maxWidth);
            int numOfSpacer = last - first - 1;//This is the no of spaces that should be put to separte the words
            //last line or one word in a line, left-justified
            if (last == words.length || numOfSpacer == 0) {
                sb.append(words[first]);
                for (int i = first + 1; i < last; i++) {
                    sb.append(" ").append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                int spaces = (maxWidth - width) / numOfSpacer;//Dividing the available spaces with no of spaces to be applied
                //extra spaces add to left spacers
                int extra = (maxWidth - width) % numOfSpacer;
                for (int i = first; i < last - 1; i++) {
                    sb.append(words[i]);
                    for (int j = 0; j <= spaces + ((i - first) < extra ? 1 : 0); j++) {
                        sb.append(" ");
                    }
                }
                sb.append(words[last - 1]);
            }
            list.add(String.valueOf(sb));
            first = last;
        }
        return list;
    }
	public static void print(List<String> wrap){
		for(String str: wrap){
			System.out.println(str);
		}
	}
	static Map<Integer, Integer> coinCountMap = new HashMap<>();
    public static int can_give_change(List<Integer> rupee_notes) {
        for(int i = 1 ;i <= rupee_notes.size();i++){
            if(rupee_notes.get(i - 1) == 5){
                updateCount(5, 1);
                continue;
            }
            if(rupee_notes.get(i - 1) == 10){
                if(coinCountMap.containsKey(5)){
                    updateCount(10, 1);
                    updateCount(5, -1);
                }else{
                    return i;
                }
                continue;
            }
            if(rupee_notes.get(i - 1) == 20){
                if(coinCountMap.containsKey(10)){
                    updateCount(20, 1);
                    updateCount(10, -1);
                    if(coinCountMap.containsKey(5)){
                        updateCount(5, -1);
                    }else{
                        return i;
                    }
                }else if(coinCountMap.containsKey(5)){
                    int count =coinCountMap.get(5);
                    if(count == 3){
                        updateCount(5, -3);
                    }else{
                        return i;
                    }
                }else{
                    return i;
                }
                continue;
            }
        }
        return 0;
    }
    public static void updateCount(int coin,int count){
        if(coinCountMap.containsKey(coin)){
            coinCountMap.put(coin, coinCountMap.get(coin) + count);
        }else{
            coinCountMap.put(coin, count);
        }
    }
    public static int CountNaturalNumber(int n) {
        int count = 0;
        for(int i = 1;i <= n ;i++){
            if(permuteAndCheck(i)){
                count++;
            }
        }
        return count;
    }
    public static boolean permuteAndCheck(int number){
        List<Integer> permutations = permutations(number);
        for(Integer p:permutations){
            if(p < number){
                return false;
            }
        }
        return true;
    }
    public static List<Integer> permutations(int number){
        List<Integer> digits = new ArrayList<>();
        while(number > 0){
            digits.add(number % 10);
            number = number / 10;
        }
        int i = 0;int j = digits.size() - 1;
        int[] arr = convertListToArray(digits);
        while(i < j){
            swap(arr, i, j);
        }
        List<List<Integer>> result = new ArrayList<>();
        permute(0,arr,result);
        List<Integer> permutations = new ArrayList<>();
        for(List<Integer> list : result){
            int num = 0;
            for(int k = 0, p = list.size() - 1;k < list.size() && p >= 0;k++, p--){
                num+=list.get(k) * Math.pow(10, p);
            }
            permutations.add(num);
        }
        return permutations;
    }
    static void permute(int start, int[] num, List<List<Integer>> result){
            if(start >= num.length){
            	ArrayList<Integer> item = convertArrayToList(num);
        		result.add(item);
            }
            for (int j = start; j <= num.length - 1; j++) {
        		swap(num, start, j);
        		permute(start + 1,num, result);
        		swap(num, start, j);
        	}
    }
    static void swap(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
    static ArrayList<Integer> convertArrayToList(int[] num) {
    	ArrayList<Integer> item = new ArrayList<Integer>();
    	for (int h = 0; h < num.length; h++) {
    		item.add(num[h]);
    	}
    	return item;
    }
    static int[] convertListToArray(List<Integer> list){
    	int[] arr = new int[list.size()];
    	int j = 0;
    	for(Integer i:list){
    		arr[j++] = i;
    	}
    	return arr;
    }
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println(CountNaturalNumber(8));
	}
}