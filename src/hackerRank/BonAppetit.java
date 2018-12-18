//$Id$
package hackerRank;

import java.util.Scanner;

public class BonAppetit {
	static void splitwise(int[] items,int itemAnnaDoesntEat,int chargedAmount){
		int sum=0;
		for(int i=0;i<items.length;i++){
			if(i!=itemAnnaDoesntEat)
				sum+=items[i];
		}
		sum=sum/2;
		if(chargedAmount==sum){
			System.out.println("Bon Appetit");
		}else{
			System.out.println(Math.subtractExact(chargedAmount, sum));
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		try{
			String n_k=in.nextLine();
			Integer noOfItems=Integer.valueOf(n_k.split(" ")[0]);
			Integer itemAnnaDoesntEat=Integer.valueOf(n_k.split(" ")[1]);
			String[] items=in.nextLine().split(" ");
			int[] itemsArray=new int[items.length];
			if(noOfItems>=2 && noOfItems<=100000){
				for(int i=0;i<noOfItems;i++){
					itemsArray[i]=Integer.valueOf(items[i]);
				}
				Integer chargedAmount=Integer.valueOf(in.nextLine());
				if(itemAnnaDoesntEat<noOfItems)
				splitwise(itemsArray, itemAnnaDoesntEat, chargedAmount);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			in.close();
		}
		
		
	}
}
