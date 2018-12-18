package hackerRank;
//$Id$

import java.io.*;
import java.util.*;

public class HackerRank_Birds {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(int max, int[] ar) {
    	int minIndex=ar.length;
    	for(int i=0;i<ar.length;i++){
    		if(ar[i]==max){
    			if(minIndex>i){
    				minIndex=i;
    			}
    		}
    	}
    	return minIndex;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int max=0;
        for (int arItr = 0; arItr < arCount; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr]);
            ar[arItem]+=1;
            if(max<=ar[arItem]){
            	max=ar[arItem];
            }
        }
        
        int result = migratoryBirds(max, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
