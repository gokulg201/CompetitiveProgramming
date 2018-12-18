/* IMPORTANT: Multiple classes and nested static classes are supported */

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class Candies {
	Character[] specialCandies={'a','e','i','o','u','A','E','I','O','U'};
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
    	  Scanner s = new Scanner(System.in);
    	  int testCases=Integer.valueOf(s.nextLine());
    	  if(testCases>=1 && testCases<=10){
    		  for(int i=0;i<testCases;i++){
        		  String input=s.nextLine();
        		  Long lengthOfString=Long.valueOf(input.split(" ")[0]);
        		  int noOfSpecialCandies=Integer.valueOf(input.split(" ")[1]);
        		  String candies=s.nextLine();
        		  Candies c=new Candies();
        		  if(lengthOfString>=1 && lengthOfString<=1000000){
        			  if(noOfSpecialCandies>=0 && noOfSpecialCandies<=5){
            			  System.out.println(c.maxNoOfCandies(candies, noOfSpecialCandies));
            		  }  
        		  }
        	  }
    	  }
    }
    public Character isSpecialCandy(Character candy){
    	Character specialCandy='0';
    	for(int i=0;i<specialCandies.length;i++){
    		if(candy.equals(specialCandies[i])){
    			return specialCandy=specialCandies[i];
    		}
    	}
    	return specialCandy;
    }
    public int maxNoOfCandies(String candies,Integer noOfSpecialCandies){
    	char[] candyArray=candies.toCharArray();
    	Set<Character> specialCandiesEncountered=new HashSet<Character>();
    	int maxCount=0;
    	for(int i=0;i<candyArray.length;i++){
    		specialCandiesEncountered.clear();
    		int count=0;
    		if(candyArray.length-i<maxCount){
    			break;
    		}
    		for(int j=i;j<candyArray.length;j++){
    			Character specialCandy=isSpecialCandy(Character.valueOf(candyArray[j]));
        		if(!specialCandy.equals('0')){
        			specialCandiesEncountered.add(Character.toLowerCase(specialCandy));
        		}
        		if(specialCandiesEncountered.size()>noOfSpecialCandies){
        			break;
        		}
        		count++;
    		}
    		if(maxCount<count){
    			maxCount=count;
    		}
    	}
    	return maxCount;
    }
}