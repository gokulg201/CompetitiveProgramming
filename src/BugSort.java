import java.util.Scanner;

//$Id$

public class BugSort {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
        Integer tasks=Integer.parseInt(s.nextLine());
        int[] finalArray=new int[tasks];
        int currentSize=0;
        if(tasks>=1 && tasks<=500000){
        	for(int i=0;i<tasks;i++){
        		String[] task=s.nextLine().split(" ");
        		if(Integer.parseInt(task[0])==2){
        			if(currentSize<3){
        				System.out.println("Not enough enemies");
        			}else{
        				System.out.println(finalArray[currentSize/3-1]);
        			}
        		}else if(Integer.parseInt(task[0])==1){
        			int elementToadd=Integer.parseInt(task[1]);
        			if(elementToadd>=1 && elementToadd<=Math.pow(10, 9))
        				finalArray=add(elementToadd, finalArray);
        			currentSize++;
        		}
        	}
        }
	}
	public static int[] add(int add,int[] x){
	    //Declare an int array with length = x.length+1;
	    int[] bigger = new int[x.length+1];
	    /** Define a variable to indicate that if a property location is found.*/
	    boolean found = false;
	    /** Define a variable to store an index for insert*/
	    int indexToInsert = 0;
	    for (int i = 0; i < x.length; i++){
	    	if(x[i]==0 && i!=0) break;
	         if ( !found && add >= x[i]){
	             found = true;
	             indexToInsert = i;
	             bigger[indexToInsert] = add;
	             i--;
	         }
	         else{
	             if(found)
	             {
	                 bigger[i+1] = x[i]; 
	             }else
	             {
	                 bigger[i] = x[i];
	             }

	         }
	    }

	    /*
	     * If a property index is not found. Then put the value at last. 
	     */
	    if(!found)
	    {
	        indexToInsert = x.length;//
	        bigger[indexToInsert] = add;
	    }
	    return bigger;
	}
}
