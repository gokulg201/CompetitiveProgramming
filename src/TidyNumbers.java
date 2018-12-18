import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

//$Id$

public class TidyNumbers {
	private String solveTest() throws IOException {
		/*
        int k = nextInt();
        for(int i=k;i>0;i--){
        	int[] numArray=digitArray(i, noOfDigits(i));
        	Boolean status=true;
        	for(int j=0;j<numArray.length-1;j++){
        		if(numArray[j]<numArray[j+1]){
        			status=false;
        			break;
        		}
        	}
        	if(status){
        		return i;
        	}
        }
        return 0;
        */
		String s=next();
		int i=0;
		while(i<s.length()-1 && s.charAt(i+1)>=s.charAt(i)) i++;
		if(i==s.length()-1) return s;
		String res="";
		for(int j=0;j<s.length()-1;j++){
			res+=j<i?s.charAt(j): j==i ? (char)s.charAt(j)-1 : '9';
		}
	  if (res.charAt(0) == '0') res = res.substring(1);
        return res;
    }
	public int noOfDigits(int number){
		int noOfDigits=0;
		while(number>0){
			number=number/10;
			noOfDigits++;
		}
		return noOfDigits;
	}
	public int[] digitArray(int number,int arraySize){
		int[] array=new int[arraySize];
		for(int i=0;i<arraySize;i++){
			array[i]=number%10;
			number=number/10;
		}
		return array;
	}
	 private void solve() throws IOException {
	        int n = nextInt();
	        for (int i = 0; i < n; i++) {
	            String res = solveTest();
	            System.out.println("Case #" + (i + 1) + ": " + res);
//	            out.println("Case #" + (i + 1) + ": " + res);
	        }
	    }


	    BufferedReader br;
	    StringTokenizer st;
	    PrintWriter out;
	    Scanner in=new Scanner(System.in);
	    String next() throws IOException {
	        while (st == null || !st.hasMoreTokens()) {
//	            st = new StringTokenizer(br.readLine());
	            st = new StringTokenizer(in.nextLine());
	        }
	        return st.nextToken();
	    }
	    int nextInt() throws IOException {
	        return Integer.parseInt(next());
	    }
		public static void main(String[] args) throws FileNotFoundException {
	        new TidyNumbers().run();
	    }
	//    Scanner in = new Scanner(System.in);
	    private void run() throws FileNotFoundException {
	        br = new BufferedReader(new FileReader("/Users/gokul-4406/Downloads/B-large-practice.in"));
	        out = new PrintWriter(this.getClass().getSimpleName().substring(0, 1) + ".out");
	        try {
	            solve();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        out.close();
	    }
}
