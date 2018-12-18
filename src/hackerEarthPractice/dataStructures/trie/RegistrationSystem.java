//$Id$
package hackerEarthPractice.dataStructures.trie;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class RegistrationSystem {
	 
	  public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	    Solver solver = new Solver();
	    solver.solve(1, scanner, out);
	    out.close();
	  }
	 
	  static class Solver {
	 
	    public void solve(int testNumber, Scanner in, PrintWriter out) {
	      int n = in.nextInt();
	      HashMap<String, Integer> count = new HashMap<>();
	      while (n-- > 0) {
	        String s = in.next();
	        if (!count.containsKey(s)) {
	          out.println(s);
	          count.put(s, 0);
	        } else {
	          int cur = count.get(s);
	          String t = s + cur;
	          while (count.containsKey(t)) {
	            cur++;
	            t = s + cur;
	          }
	          out.println(t);
	          count.put(t, 0);
	          count.put(s, cur + 1);
	        }
	      }
	    }
	  }
	}