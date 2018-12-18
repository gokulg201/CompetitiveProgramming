//$Id$
package crackingTheCodingInterview;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {
	Stack<Character> brackets =  new Stack<Character>();
	public String checkBalance(String expression){
		for(int i=0 ;i < expression.length(); i++){
			char ch = expression.charAt(i);
			if(ch == '{'){
				brackets.push('}');
			}else if(ch == '('){
				brackets.push(')');
			}else if(ch == '['){
				brackets.push(']');
			}else{
				if(brackets.empty() || ch != brackets.pop()){
					return "NO";
				}
			}
			
		}
		if(!brackets.empty()){
			return "NO";
		}
		return "YES";
	}
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args){
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String expression = scanner.nextLine();
            System.out.println(new BalancedBrackets().checkBalance(expression));
        }

        scanner.close();
	}
}
