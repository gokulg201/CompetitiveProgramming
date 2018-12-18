//$Id$
package DynamicProgramming;

import java.util.Scanner;

public class LongestCommonSubstring {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int sz_a = in.nextInt();
			int sz_b = in.nextInt();
			String a = in.next();
			String b = in.next();
			char[] ch_a = a.toCharArray();
			char[] ch_b = b.toCharArray();
			int[][] LCSuff= new int[sz_a + 1][sz_b + 1];
			int result = 0; 
			for(int i = 0 ;i <=sz_a;i++){
				for(int j = 0 ;j <=sz_b;j++){
					if(i == 0 || j == 0)
						LCSuff[i][j] = 0;
					else if(ch_a[i - 1] == ch_b[j - 1]){
						LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
						result = Math.max(result, LCSuff[i][j]);
					}
					else{
						LCSuff[i][j] = 0;
					}
				}
			}
			System.out.println(result);
		}
	}
}
