//$Id$
package hackerEarthPractice.dataStructures.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
	class Trie{
		class TrieNode{
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
		  TrieNode root = new TrieNode();
		  HashMap<String, Integer> weightMap = new HashMap<String, Integer>();
		  Trie(){};
		  
		  Trie(String[] words,int[] weights){
			  int i = 0;
		      for(String word:words){
		          addWord(word,weights[i++]);
		      }
		  }
		  
		  public void addWord(String word, int weight){
		      TrieNode curr = root;
		      for(int i = 0;i < word.length();i++){
		          Character ch = word.charAt(i);
		          curr.putChild(ch);
		          curr = curr.getChild(ch);
		      }
		      curr.isCompleteWord = true;
		      updateWeight(word, weight);
		  }
		  
		  private TrieNode find(String prefix){
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
	  private void updateWeight(String word,int weight){
		  weightMap.put(word, weight);
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
//		          word = word+c;
	          //recurse
	          getWords(childNode, word+c,possibleWords);
	      }
	      return possibleWords;
	  }
	} 
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int insertions = Integer.parseInt(str.split(" ")[0]);
		int query = Integer.parseInt(str.split(" ")[1]);
		Trie trie = new SearchEngine().new Trie();
		for(int i = 0;i < insertions;i++){
			String word = scanner.nextLine();
			trie.addWord(word.split(" ")[0], Integer.parseInt(word.split(" ")[1]));
		}
		for(int i = 0;i < query;i++){
			String prefix = scanner.nextLine();
			int max  = -1;
			List<String> matchedWords = trie.findPossibleWord(prefix);
			for(String matchedWord:matchedWords){
				if(max < trie.weightMap.get(matchedWord)){
					max = trie.weightMap.get(matchedWord);
				}
			}
			System.out.println(max);
		}
	}
}