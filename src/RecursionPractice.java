//$Id$

public class RecursionPractice {
	public static void main(String[] args){
		System.out.println(isPalindrome("malayalam", 0, 8));
	}
	static String printArray(int[] arr, int n){
		if(n - 1 == 0){
			return ""+arr[0];
		}else{
			return printArray(arr, n - 1) + ","+arr[n - 1];
		}
	}
	static boolean isPalindrome(String str, int s, int e){
		if(s == e)
			return true;
		if(str.charAt(s) != str.charAt(e))
			return false;
		if(s < e)
			return isPalindrome(str, s + 1, e - 1);
		return true;
	}
}
