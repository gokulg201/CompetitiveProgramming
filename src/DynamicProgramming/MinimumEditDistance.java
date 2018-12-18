//$Id$
package DynamicProgramming;

import java.util.Scanner;

public class MinimumEditDistance {
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
			int[][] lcs = new int[sz_a+1][sz_b+1];
			for(int i = 0; i <= sz_a;i++){
				for(int j = 0; j <=sz_b; j++){
					if(i == 0)
						lcs[i][j] = j;
					else if(j == 0)
						lcs[i][j] = i;
					else if(ch_a[i - 1] == ch_b[j - 1]){
						//pick the diagonal element, since this means no change 
						lcs[i][j] = lcs[i -1][j -1];
					}else{
						//pick the min of i-1/j-1 element
						lcs[i][j] = Integer.min(Integer.min(lcs[i-1][j],lcs[i-1][j-1]), lcs[i][j-1]) + 1;
					}
				}
			}
			System.out.println(lcs[sz_a][sz_b]);
		}	
	}
}

