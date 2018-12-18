//$Id$
package hackerEarthPractice.fight;

import java.util.Scanner;

public class Fight7 {
	public static void main(String[] args){
		Scanner s =  new Scanner(System.in);
		String str = s.nextLine();
		int N = Integer.parseInt(str.split(" ")[0]);
		int M = Integer.parseInt(str.split(" ")[1]);
		str = s.nextLine();
		long sum = 0;int result;
		if(N <= Math.pow(10, 5) && N >= 1 && M >= 1 && M <= N){
			for(int i = 0;i < N;i++){
				sum = sum + Long.parseLong(str.split(" ")[i]);
			}
			if(sum % M == 0){
				System.out.println(sum / M);
			}else{
				System.out.println((int)(sum / M)+1);
			}
		}
	}
}
