//$Id$
package hackerRank.dataStructures.trie;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Solution to hackerrank problem <a href="https://www.hackerrank.com/challenges/no-prefix-set/problem">Contacts Problem</a>
 * @author gokul-4406
 *
 */
public class NoPrefixSet {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int noOfInputs = Integer.parseInt(in.nextLine());
		String[] words  = new String[noOfInputs];
		for(int i = 0;i < noOfInputs;i++){
			words[i] = in.nextLine();
		}
		checkForNoPrefix(new Trie(), words);
	}
	public static void checkForNoPrefix(Trie root,String[] words){
		for(String word:words){
			for(int i = 0;i < word.length();i++){
				String sub = word.substring(0, i + 1);
				TrieNode match = root.find(sub);
				if(match != null && match.isCompleteWord){
					System.out.println("BAD SET");
					System.out.println(word);
					return;
				}
			}
			if(root.find(word) != null){
				System.out.println("BAD SET");
				System.out.println(word);
				return;
			}
			root.addWord(word);
		}
		System.out.println("GOOD SET");
	}
}
class TrieNode{
	  HashMap<Character,TrieNode> children = new HashMap<Character, TrieNode>();
	  boolean isCompleteWord;
	  
	  public void putChild(char ch){
	      children.putIfAbsent(ch, new TrieNode());
	  }
	  public TrieNode getChild(char ch){
	      return children.get(ch);
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
} 