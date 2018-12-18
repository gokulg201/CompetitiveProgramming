//$Id$
package hackerEarthPractice;

import java.util.Scanner;

public class BobDirection {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int i = 0;
		int[] inpArray = new int[1];
		while(true){
			int number =  in.nextInt();
			if(number != 42){
				if(inpArray.length < i+1){
					inpArray = resizeArray(inpArray);
				}
				inpArray[i++] = number;
			}else{
				break;
			}
		}
		int[] temp = new int[i];
		for(int j=0;j < i;j++){
			temp[j] = inpArray[j];
		}
		temp = sort(temp);
		for(int j = 0;j < i;j++){
			System.out.println(temp[j]);
		}
	}
	public static int[] resizeArray(int[] array){
		int[] temp = new int[2*array.length];
		for(int i=0;i<array.length;i++){
			temp[i] = array[i];
		}
		return temp;
	}
	public static int[] sort(int[] a) {
		int N=a.length;
		for(int i=0;i<N;i++){
			int min=i;
			for(int j=i+1;j<N;j++)
				if(less(a[j], a[min])) min=j;
			exch(a, i, min);
		}
		return a;
	}
	private static final boolean less(int v,int w){
		return v < w;
	}
	private static final void exch(int[] a, int i, int j){
		int temp=a[i];
		a[i]=a[j];
		a[i]=temp;
	}
}
