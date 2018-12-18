import java.util.Scanner;

//$Id$

public class Winner {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
        Integer noOftestCases=Integer.parseInt(s.nextLine());
        if(noOftestCases<=100000){
        	int finalFlashScore=0;
    		int finalCisicoScore=0;
    		int totalFlashTime=0;
    		int totalCiscoTime=0;
    		
    		
        	for(int i=0;i<noOftestCases;i++){
            	String[] scores=s.nextLine().split(" ");
            	int[] flashScore=new int[scores.length];
            	int[] ciscoScore=new int[scores.length];
            	for(int j=0;j<scores.length;j++){
            		flashScore[j]=Integer.parseInt(scores[j]);
            		ciscoScore[j]=Integer.parseInt(scores[j]);
            		if(flashScore[j]>=100 && flashScore[j]<=1000000){
            			continue;
            		}else{
            			return;
            		}
            	}
            	String[] decrease=s.nextLine().split(" ");
//            	int[] decreaseScore=new int[decrease.length];
//            	for(int j=0;j<decrease.length;j++){
//            		decreaseScore[j]=Integer.parseInt(decrease[j]);
//            		if(decreaseScore[j]>=0 && decreaseScore[j]<=1000000){
//            			continue;
//            		}else{
//            			return;
//            		}
//            	}
            	String[] flash=s.nextLine().split(" ");
            	String[] cisco=s.nextLine().split(" ");
            	int[] flashTime=new int[flash.length];
            	for(int j=0;j<flash.length;j++){
            		
            		flashTime[j]=Integer.parseInt(flash[j]);
            		if(flashTime[j]>=0 && flashTime[j]<=1000000){
            			continue;
            		}else{
            			return;
            		}
            	}
//            	int[] ciscoTime=new int[cisco.length];
//            	for(int j=0;j<flash.length;j++){
//            		ciscoTime[j]=Integer.parseInt(cisco[j]);
//            		if(ciscoTime[j]>=0 && ciscoTime[j]<=1000000){
//            			continue;
//            		}else{
//            			return;
//            		}
//            	}
//            	winner(initialScores, decreaseScore, flashTime, ciscoTime);
            }
        }
	}
	public static void winner(int[] initialScores,int[] decreaseScore,int[] flashTime,int[] ciscoTime){
		int[] flashScore=new int[initialScores.length];
		int[] ciscoScore=new int[initialScores.length];
		int finalFlashScore=0;
		int finalCisicoScore=0;
		int totalFlashTime=0;
		int totalCiscoTime=0;
		for(int i=0;i<initialScores.length;i++){
			flashScore[i]=Math.max(initialScores[i]-(decreaseScore[i]*flashTime[i]),initialScores[i]/2);
			finalFlashScore+=flashScore[i];
			if(totalFlashTime<flashTime[i]){
				totalFlashTime=flashTime[i];
			}
			
			ciscoScore[i]=Math.max(initialScores[i]-(decreaseScore[i]*ciscoTime[i]),initialScores[i]/2);
			if(totalCiscoTime<ciscoTime[i]){
				totalCiscoTime=ciscoTime[i];
			}
			finalCisicoScore+=ciscoScore[i];
		}
		if(finalCisicoScore>finalFlashScore){
			System.out.println("Cisco");
		}else if(finalCisicoScore<finalFlashScore){
			System.out.println("Flash");
		}else{
			if(totalFlashTime<totalCiscoTime){
				System.out.println("Flash");
			}else if(totalFlashTime>totalCiscoTime){
				System.out.println("Cisco");
			}else{
				System.out.println("Tie");
			}
		}
		
	}
}
