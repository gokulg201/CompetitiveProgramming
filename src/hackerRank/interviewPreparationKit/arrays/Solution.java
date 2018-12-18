//$Id$
package hackerRank.interviewPreparationKit.arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Solution {

  /*
   * Complete the contacts function below.
   */
  static Integer[] contacts(String[][] queries) {
      /*
       * Write your code here.
       */
	HashMap<String, Integer> counter = new HashMap<String, Integer>();  
   List<Integer> contacts = new ArrayList<Integer>();
   for (int i = 0; i < queries.length ; i++) {
          String operation = queries[i][0];
          String contact   = queries[i][1];
          if (operation.equals("add")) {
              for(int j = 0;j < contact.length();j++){
            	  String splitWord = contact.substring(0, j+1);
            	  if(counter.containsKey(splitWord)){
            		  counter.put(splitWord, counter.get(splitWord) + 1);
            	  }else{
            		  counter.put(splitWord, 1);
            	  }
              }
          } else if (operation.equals("find")) {
        	  if(counter.containsKey(contact)){
        		  contacts.add(counter.get(contact));
        	  }else{
        		  contacts.add(0);
        	  }
          }
      }
  Integer[] result = new Integer[contacts.size()];
  return contacts.toArray(result);
  }

  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/gokul-4406/Downloads/output.txt"));

      int queriesRows = Integer.parseInt(scanner.nextLine().trim());

      String[][] queries = new String[queriesRows][2];

      for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
          String[] queriesRowItems = scanner.nextLine().split(" ");

          for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
              String queriesItem = queriesRowItems[queriesColumnItr];
              queries[queriesRowItr][queriesColumnItr] = queriesItem;
          }
      }

      Integer[] result = contacts(queries);

      for (int resultItr = 0; resultItr < result.length; resultItr++) {
          bufferedWriter.write(String.valueOf(result[resultItr]));

          if (resultItr != result.length - 1) {
              bufferedWriter.write("\n");
          }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();
  }
}

