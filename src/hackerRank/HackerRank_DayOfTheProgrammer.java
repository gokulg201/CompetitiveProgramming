package hackerRank;
//$Id$
import java.io.*;
import java.util.*;

public class HackerRank_DayOfTheProgrammer {

    // Complete the solve function below.
   static String solve(int year) {
            String day="";
            String month="09";
            if(year>=1700 && year<=1917){
                if(isLeapYear_Julian(year)){
                    day=String.valueOf(256-244);
                }else{
                    day=String.valueOf(256-243);
                }
            }else if(year==1918){
                day=String.valueOf(256-230);
            }else if(year>1918){
                if(isLeapYear_Gregorian(year)){
                    day=String.valueOf(256-244);
                }else{
                    day=String.valueOf(256-243);
                }
            }
            return day+"."+month+"."+year;
        }
    static Boolean isLeapYear_Julian(int year){
        if((year%4)==0){
            return true;
        }
        return false;
    }
    static Boolean isLeapYear_Gregorian(int year){
        if((year%400)==0){
            return true;
        }
        if (year%4==0 && year%100!=0){
            return true;
        }
        return false;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/gokul-4406/Documents/HackerRank/DayOFProgrammer.txt"));

        int year = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = solve(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}