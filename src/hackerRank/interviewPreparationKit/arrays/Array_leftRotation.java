//$Id$
package hackerRank.interviewPreparationKit.arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Array_leftRotation {
    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
    	int size=a.length;
    	int i, j, k, temp;
    	for (i = 0; i < gcd(d, size); i++) {
    		temp=a[i];
    		j=i;
    		while(true){
    			k=j+d;
    			if(k >= size)
    				k = k-size;
    			if(k == i)
    				break;
    			a[j]=a[k];
    			j=k;
    		}
    		a[j]=temp;
    	}
    	return a;
    }
    /*UTILITY FUNCTIONS*/
     
    /* function to print an array */
    static void printArray(int arr[]) 
    {
        int i;
        for (i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
    /*Fuction to get gcd of a and b*/
    static int gcd(int a, int b) 
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);
        Array_leftRotation.printArray(a);
        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
