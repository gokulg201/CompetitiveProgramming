//$Id$
package hackerEarthPractice.dataStructures.trie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author gokul-4406
 *
 */
public class AutoSuggestion {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
        	String word = scan.nextLine();
        	trie.addWord(word);
        }
        int f = Integer.parseInt(scan.nextLine());
        for(int i = 0;i < f;i++){
        	String find = scan.nextLine();
        	List<String> results = trie.findPossibleWord(find);
        	if(results.size() < 1){
        		trie.addWord(find);
        		System.out.println("No suggestions");
        		continue;
        	}
        	results.sort(new Comparator<String>() {
      	      public int compare(String o1, String o2) {
    	          return o1.compareTo(o2);
    	        }
    	        });
        	for(String result:results){
        		System.out.println(result);
        	}
        }
        scan.close();
	}
	static class TrieNode{
		  HashMap<Character,TrieNode> children = new HashMap<Character, TrieNode>();
		  boolean isCompleteWord;
		  
		  public void putChild(char ch){
		      children.putIfAbsent(ch, new TrieNode());
		  }
		  public TrieNode getChild(char ch){
		      return children.get(ch);
		  }
		  public HashMap<Character,TrieNode> getChildren(){
		      return this.children;
		  }
		  public boolean hasChild(char ch){
			  return children.get(ch) != null;
		  }
		}
	static class Trie{
	  TrieNode root = new TrieNode();
	  
	  Trie(){};
	  
	  Trie(String[] words){
	      for(String word:words){
	          addWord(word);
	      }
	  }
	  
	  public void addWord(String word){
	      TrieNode curr = root;
	      for(int i = 0;i < word.length();i++){
	          Character ch = word.charAt(i);
	          curr.putChild(ch);
	          curr = curr.getChild(ch); 
	      }
	      curr.isCompleteWord = true;
	  }
	  
	  public TrieNode find(String prefix){
	      TrieNode curr = root;
	      
	      /* Traverse down tree to end of our prefix */
	      for (int i = 0; i < prefix.length(); i++) {
	          Character ch = prefix.charAt(i);
	          curr = curr.getChild(ch);
	          if (curr == null) {
	              return null;
	          }
	      }
	      return curr;
	  }
	  public List<String> findPossibleWord(String prefix){
	      TrieNode curr = root;
	      for(int i = 0;i < prefix.length(); i++){
	          char ch = prefix.charAt(i);
	          if(curr.hasChild(ch)){
	        	  curr = curr.getChild(ch);
	          }else{
	        	  return getWords(null, prefix, new ArrayList<String>());
	          }
	      }
	      return getWords(curr, prefix,new ArrayList<String>());
	  }
	  private List<String> getWords(TrieNode current, String word, List<String> possibleWords){
	      if(current == null){
	          return possibleWords;
	      }
	      if(current.isCompleteWord) {
	          possibleWords.add(word);
	      }
	      HashMap<Character,TrieNode> children=current.getChildren();
	      for(char c:children.keySet()){
	          TrieNode childNode = current.getChild(c);
	          //recurse
	          getWords(childNode, word+c,possibleWords);
	      }
	      return possibleWords;
	  }
	} 
}
