//$Id$
package hackerRank.interviewPreparationKit.arrays;

import java.io.IOException;
import java.util.Scanner;

public class _2DArrays {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
    	int result=0;
    	int row_len=arr.length;
    	int col_len=arr[0].length;
    	for(int i=0;i<row_len-2;i++){
    		for(int k=0;k<col_len-2;k++){
    			int row1=0,row3=0,row2=arr[i+1][k+1];
    			for(int j=k;j<=k+2;j++){
        			row1=row1+arr[i][j];
        			row3=row3+arr[i+2][j];
        		}
    			if(i==0 && k==0){
    				result=row1+row2+row3;
    			}else{
    				result=Math.max(row1+row2+row3, result);
    			}
    		}
    	}
    	return result;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];
        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
            	if(j<arrRowItems.length){
            		int arrItem = Integer.parseInt(arrRowItems[j]);
                    arr[i][j] = arrItem;
            	}else{
            		arr[i][j] = 0;
            	}
            }
        }

        int result = hourglassSum(arr);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
