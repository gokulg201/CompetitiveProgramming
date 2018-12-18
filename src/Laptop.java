import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//$Id$

public class Laptop {
	Hashtable<String, Integer> laptopCountMap=new Hashtable<String, Integer>();
	Integer maxCount=0;
	Set<String> listOfLapTops=new HashSet();
	public static void main(String[] args){
		Laptop lap=new Laptop();
		Scanner s = new Scanner(System.in);
  	  	int noOfInputs=Integer.valueOf(s.nextLine());
  	  	for(int i=0;i<noOfInputs;i++){
  	  		String lapTop=s.nextLine();
  	  		lap.checkForKeyndUpdateCount(lapTop);
  	  		lap.checkMaxCountAndUpdateLapTop(lap.laptopCountMap.get(lapTop), lapTop);
  	  	}
  	  System.out.println(lap.printMaxSoughtLap());
	}
	public String printMaxSoughtLap(){
		Iterator<String> it = listOfLapTops.iterator();
		String small=it.next();
	     while(it.hasNext()){
	    	 String current=it.next();
	    	 if ( current.compareTo(small) < 0 )
	                small = current;
	     }
		return small;
	}
	public void checkMaxCountAndUpdateLapTop(Integer count,String laptop){
		if(maxCount<count){
	  			maxCount=count;
	  			listOfLapTops.clear();
	  			listOfLapTops.add(laptop);
	  		}else if(maxCount==count){
	  			listOfLapTops.add(laptop);
	  		}
	}
	public void checkForKeyndUpdateCount(String key){
		if(laptopCountMap.containsKey(key)){
			Integer count=laptopCountMap.get(key);
			laptopCountMap.put(key, count+1);
		}else{
			laptopCountMap.put(key, 1);
		}
	}
	
}
