//$Id$
package hackerRank;

import java.util.Scanner;

public class PayPalC1 {
	static String evenlengthPalindrome(String n)
    {
        String res = n;
        for (int j = n.length() - 1; j >= 0; --j)
            res += n.charAt(j);
        return res;
    }
	static int maxRepeating(int arr[], int n)
    {
		int count[]= new int[10];
        for (int i = 0; i< n; i++){
        	count[arr[i]]++;
        }
            
        int max = count[0], result = -1;
        for (int i = 0; i < 10; i++)
        {
            if (count[i] >= max)
            {
                if(count[i]==max){
                	if(result==-1){
                    	result = i;
                    }else{
                    	if(result > i)
                    		result = i;
                    }
                }else{
                	result = i;
                }
                max = count[i]; 	
            }
        }
        return result;
    }
	 public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		try{
			int T=Integer.parseInt(in.nextLine());
			if(T>=1 && T<=100000){
				for(int i=0;i<T;i++){
					String n =in.nextLine();
					String pal=evenlengthPalindrome(n);
					if(Long.parseLong(n)>=1 && Long.parseLong(n)<=Math.pow(10, 18)){
						 int[] palindrome =new int[pal.length()];
						    for(int j=0;j<pal.length();j++){
						    	palindrome[j]=Character.getNumericValue((pal.charAt(j)));
						    }
						    System.out.println(maxRepeating(palindrome, palindrome.length));
					}
				}
			}
		}finally{
			in.close();
		}
    }
}
