//$Id$
package crackingTheCodingInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This is the TRIE implementation of finding the validity of the word given the word is stored in the dictionary (TRIE data-structure)
 * This solution is for the problem hosted on HackerRank <a href="https://www.hackerrank.com/challenges/ctci-contacts/problem">Contacts Problem</a>
 * @author gokul
 *
 */
public class Contacts {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            String contact   = scan.next();
            if (operation.equals("add")) {
                trie.addWord(contact);
            } else if (operation.equals("find")) {
                System.out.println(trie.printPossibleWord(contact).size());
            }
        }
        scan.close();
    }
}
class TrieNode{
	  HashMap<Character,TrieNode> children = new HashMap<Character, TrieNode>();
	  boolean isCompleteWord;
	  public int size;
	  
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
	class Trie{
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
	          curr.size++;
	      }
	      curr.isCompleteWord = true;
	  }
	  
	  public int find(String prefix){
	      TrieNode curr = root;
	      
	      /* Traverse down tree to end of our prefix */
	      for (int i = 0; i < prefix.length(); i++) {
	          Character ch = prefix.charAt(i);
	          curr = curr.getChild(ch);
	          if (curr == null) {
	              return 0;
	          }
	      }
	      return curr.size;
	  }
	  
	  public List<String> printPossibleWord(String prefix){
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
	  public List<String> getWords(TrieNode current, String word, List<String> possibleWords){
	      if(current == null){
	          return possibleWords;
	      }
	      if(current.isCompleteWord) {
	          possibleWords.add(word);
	      }
	      HashMap<Character,TrieNode> children=current.getChildren();
	      for(char c:children.keySet()){
	          TrieNode childNode = current.getChild(c);
	          word = word+c;
	          //recurse
	          getWords(childNode, word,possibleWords);
	      }
	      return possibleWords;
	  }
	} 