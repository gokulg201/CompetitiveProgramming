//$Id$
package hackerRank;

import java.util.Scanner;

public class PayPalC3 {
	String[][] forest;
	int N;
	int M;
	int count=0;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		try{
			PayPalC3 payPal=new PayPalC3();
			
			String forestLimts = in.nextLine();
			payPal.N=Integer.parseInt(forestLimts.split(" ")[0]);
			payPal.M=Integer.parseInt(forestLimts.split(" ")[1]);
			if(payPal.N<=1000 && payPal.M<=1000 && payPal.N>=1 && payPal.M>=1){
				payPal.forest=new String[payPal.N][payPal.M];
				for(int i=0;i<payPal.N;i++){
					payPal.forest[i]=in.nextLine().split("");
				}
				int T=Integer.parseInt(in.nextLine());
				if(T>=1 && T<=100000){
					for(int i=0;i<T;i++){
						int K=Integer.parseInt(in.nextLine());
						if(K>=0 && K<=1000)
							payPal.findCount(K);
					}
				}
			}
		}finally{
			in.close();
		}
	}
	public void findCount(int maxSide){
		count=0;
		for(int i=maxSide;i>0;i--){
			noOfSquares(i);
		}
		System.out.println(count);
	}
	public void noOfSquares(int n){
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(forest[i][j].equals("*")){
					if((i+n)<=N && (j+n)<=M){
						checkForSquare(i, j, n);
					}
				}
			}
		}
	}
	public void checkForSquare(int startingRow,int startingColumn,int n){
		int i=0;
		int j=0;
		for(i=startingRow;i<startingRow+n;i++){
			for(j=startingColumn;j<startingColumn+n;j++){
				if(forest[i][j].equals(".")){
					break;
				}
			}
			if(j!=startingColumn+n){
				break;
			}
		}
		if(i==startingRow+n && j==startingColumn+n){
			count++;
		}
	}
}
