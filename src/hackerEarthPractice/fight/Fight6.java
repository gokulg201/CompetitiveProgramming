//$Id$
package hackerEarthPractice.fight;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Fight6 {
	static class Disk implements Comparable<Disk>{
		int radius;
		int height;
		Disk(int radius,int height){
			this.radius = radius;
			this.height =height;
		}
		@Override
		public int compareTo(Disk o) {
			if(this.radius < o.radius && this.height < o.height){
				return -1;
			}else if(this.radius > o.radius && this.height > o.height){
				return 1;
			}else{
				return 0;
			}
		}
	}
	public static Comparator<Disk> diskOrder(){
		return new Comparator<Fight6.Disk>() {
			@Override
			public int compare(Disk o1, Disk o2) {
				if(o1.radius < o2.radius && o1.height < o2.height){
					return 1;
				}else if(o1.radius > o2.radius && o1.height > o2.height){
					return -1;
				}else{
					return 0;
				}
			}
		};
	}
	public static void main(String[] args){
		PriorityQueue<Disk> queueStack = new PriorityQueue<Fight6.Disk>(diskOrder());
		Stack<Disk> mainStack = new Stack<Fight6.Disk>();
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for(int i =0;i < t;i++){
			int n = scanner.nextInt();
			for(int j = 0;j < n;j++){
				int r = scanner.nextInt();
				int h = scanner.nextInt();
				Disk disk = new Disk(r, h);
				queueStack.add(disk);
			}
			while(!queueStack.isEmpty()){
				if(mainStack.isEmpty()){
					mainStack.push(queueStack.poll());
					continue;
				}
				if(mainStack.peek().compareTo(queueStack.peek()) > 0){
					mainStack.push(queueStack.poll());
				}else{
					queueStack.poll();
				}
			}
			int height = 0;
			while(!mainStack.isEmpty()){
				height+= mainStack.pop().height;
			}
			System.out.println(height);
		}
	}
}
