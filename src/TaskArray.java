import java.util.*;

//$Id$

public class TaskArray {
	static List<Integer> outputs=new ArrayList<Integer>();
	public static void main(String args[] ) throws Exception {
		Scanner s=new Scanner(System.in);
		String input=s.nextLine();
		Integer sizeOfArray=Integer.valueOf(input.split(" ")[0]);
		Integer noOfTasks=Integer.valueOf(input.split(" ")[1]);
		Integer[] array=new Integer[sizeOfArray];
		if(sizeOfArray>=2 && sizeOfArray<=100000){
			if(noOfTasks>=1 && noOfTasks<=100000){
				String arrayInput=s.nextLine();
				for(int j=0;j<sizeOfArray;j++){
					if(Integer.valueOf(arrayInput.split(" ")[j])>=0 && Integer.valueOf(arrayInput.split(" ")[j])<=1000000000){
						array[j]=Integer.valueOf(arrayInput.split(" ")[j]);
					}else{
						break;
					}
				}
			}
		}
		Integer[] noOfOperations=new Integer[noOfTasks];
		if(noOfTasks>=1 && noOfTasks<=100000){
			for(int i=0;i<noOfTasks;i++){
				noOfOperations[i]=Integer.valueOf(s.nextLine());
			}
		}
		for(int i=0;i<noOfTasks;i++){
			if(noOfOperations[i]>=0 && noOfOperations[i]<sizeOfArray){
				new TaskArray().findTheSum(noOfOperations[i], array);
			}
		}
		TaskArray.printTheOutputs();
		
	}
	public static void printTheOutputs(){
		for(int i=0;i<outputs.size();i++){
			System.out.println(outputs.get(i));
		}
	}
	public void findTheSum(Integer noOfOperations,Integer[] arrayInput){
		Integer[] diffAddedArray=arrayInput;
		for(int i=0;i<noOfOperations;i++){
			diffAddedArray=performOperations(diffAddedArray);
		}
		outputs.add(sumOfElements(diffAddedArray));
	}
	public Integer[] performOperations(Integer[] arrayInput){
		arrayInput=sortArray(arrayInput);
		Integer difference=arrayInput[arrayInput.length-1]-arrayInput[0];
		Integer[] modifiedArray=removeFrstLast(arrayInput);
		Integer[] diffAddedArray=new Integer[modifiedArray.length+1];
		for(int i=0;i<modifiedArray.length;i++){
			diffAddedArray[i]=modifiedArray[i];
		}
		diffAddedArray[modifiedArray.length]=difference;
		return diffAddedArray;
	}
	public Integer[] sortArray(Integer[] array){
		Arrays.sort(array);
		return array;
	}
	public Integer[] removeFrstLast(Integer[] sortedArray){
		return Arrays.copyOfRange(sortedArray, 1,sortedArray.length-1);
	}
	public Integer sumOfElements(Integer[] sortedArray){
		Integer sum=0;
		for(int i=0;i<sortedArray.length;i++){
			sum+=sortedArray[i];
		}
		return sum;
	}
	
}
