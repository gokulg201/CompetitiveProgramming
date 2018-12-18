//$Id$
package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
 * https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room/0
 * @author gokul-4406
 *
 */
public class ActivitySelection {
	static class Activity{
		int start;
		int end;
		int order;
		public Activity(int start, int end, int order){
			this.start = start;
			this.end = end;
			this.order = order;
		}
	}
	static void selection(int[] start,int[] end){
		List<Activity> activities = new ArrayList<ActivitySelection.Activity>();
		if(start.length == end.length){
			for(int i = 0;i < start.length;i++){
				activities.add(new Activity(start[i], end[i],i));
			}
		}
		activities.sort(new ActivityComparator());
		int i = 0;
		System.out.print((activities.get(i).order+1)+" ");
		for(int j = 1;j < activities.size() ;j++){
			if(activities.get(j).start >= activities.get(i).end){
				System.out.print((activities.get(j).order+1)+" ");
				i = j;
			}
		}
		System.out.print("\n");
	}
	static class ActivityComparator implements Comparator<Activity>{
		@Override
		public int compare(Activity o1, Activity o2) {
			if(o1.end > o2.end){
				return 1;
			}else if(o1.end < o2.end){
				return -1;
			}
			return 0;
		}
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0){
			int n = in.nextInt();
			int[] start = new int[n];
			int[] end = new int[n];
			for(int i = 0;i < n;i++){
				start[i] = in.nextInt();
			}
			for(int i = 0;i < n;i++){
				end[i] = in.nextInt();
			}
			selection(start, end);
		}
	}
}
