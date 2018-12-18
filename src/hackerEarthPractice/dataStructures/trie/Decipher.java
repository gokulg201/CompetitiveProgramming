//$Id$
package hackerEarthPractice.dataStructures.trie;

import java.util.Scanner;

/**
 * <a href="https://www.hackerearth.com/practice/data-structures/advanced-data-structures/trie-keyword-tree/practice-problems/algorithm/b-yet-another-problem-with-strings/">Problem Link</a>
 * @author gokul-4406
 *
 */
public class Decipher {
	static int LAST_YES = 0;
	static int N = 0;
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int N = Integer.parseInt(str.split(" ")[0]);
		Decipher.N = N;
		int Q = Integer.parseInt(str.split(" ")[1]);
		String[] S = new String[N];
		for(int i = 0;i < N;i++){
			S[i] = scanner.nextLine();
		}
		for(int i = 0;i < Q;i++){
			int query_type = scanner.nextInt();
			if(query_type == 1){
				processQuery1(i, scanner.next(), S);
			}else if(query_type == 2){
				S = processQuery2(i, scanner.nextInt(),scanner.nextInt(), S);
			}else{
				
			}
		}
	}
	private static void processQuery1(int i,String query,String[] S){
		String t = query;
		String decipheredText = "";
		for(char c:t.toCharArray()){
			c = (char)(97+(c-97+LAST_YES)%26);
			decipheredText += c;
		}
		for(String s:S){
			if(decipheredText.length() >= s.length() && decipheredText.contains(s)){
				System.out.println("YES");
				LAST_YES = i;
				return ;
			}
		}
		System.out.println("NO");
	}
	private static String[] processQuery2(int queryIndex,int i,int alpha,String[] S){
		i = (i + LAST_YES) % N;
		char c1=(char)(97+(alpha+LAST_YES)%26);
		S[i] = new StringBuilder(S[i]).append(c1).toString();
		return S;
	}
}
