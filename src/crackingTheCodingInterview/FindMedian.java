//$Id$
package crackingTheCodingInterview;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This is the MAX_HEAP and MIN_HEAP Implementation of Finding the median of a given set of integers
 * @author gokul-4406
 *
 */
public class FindMedian {
	public static double[] getMedians(int[] array){
		double[] medians = new double[array.length];
		/*By Default PriorityQueue is based on MIN_HEAP*/
		/*Higer half of the elements constitute a MIN_HEAP - so that the smallest of the higher half can be taken out for median calculation*/
		PriorityQueue<Integer> highers = new PriorityQueue<Integer>();
		/*Lower half of the elements constitute a MAX_HEAP - so that the largest of the lower half can be used for median calculation*/
		/*Changing the comparator condition to change it to a MAX_HEAP*/
		PriorityQueue<Integer> lowers = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return -1 * (o1.compareTo(o2)); 
			}
		});
		for(int i = 0;i < array.length;i++){
			int numberToAdd = array[i];
			addNumber(numberToAdd, lowers, highers);
			reBalanceHeaps(lowers, highers);
			medians[i]=getMedian(lowers, highers);
		}
		return medians;
	}
	public static void addNumber(int numberToAdd, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		if(lowers.size() < 1 || numberToAdd < lowers.peek()){
			lowers.add(numberToAdd);
		}else{
			highers.add(numberToAdd);
		}
	}
	public static void reBalanceHeaps(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		PriorityQueue<Integer> smallerHeap = lowers.size() < highers.size() ? lowers : highers;
		PriorityQueue<Integer> largerHeap = lowers.size() < highers.size() ? highers : lowers;
		
		if(largerHeap.size() - smallerHeap.size() >= 2){
			smallerHeap.add(largerHeap.poll());
		}
	}
	public static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		PriorityQueue<Integer> smallerHeap = lowers.size() < highers.size() ? lowers : highers;
		PriorityQueue<Integer> largerHeap = lowers.size() < highers.size() ? highers : lowers;
		if(smallerHeap.size() == largerHeap.size()){
			return ((double)largerHeap.peek() + smallerHeap.peek()) / 2;
		}else{
			return (double)largerHeap.peek();
		}
	}
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args){
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            int aItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            a[i] = aItem;
        }
        double[] medians = getMedians(a);
        for(int i=0 ; i < a.length; i++ ){
            System.out.println(medians[i]); 
        }
        scanner.close();
    }
}
