//$Id$
package DynamicProgramming;

public class WildCardMatching {
	/**
	 * Problem Statement:
	 *  
	 */
	public static boolean match(String s , String pattern){
		char[] text = s.toCharArray();
		char[] pat = pattern.toCharArray();
		//Simplifying the pattern, by grouping all consecutive *'s to one
		int writeIndex = 0;
		boolean isFirst = true;
		for(int i = 0 ;i < pat.length;i++){
			if(pat[i] == '*'){
				if(isFirst){
					pat[writeIndex++] = pat[i];
					isFirst = false;
				}
			}else{
				pat[writeIndex++] = pat[i];
				isFirst = true;
			}
		}
		int n = text.length;
		int m = writeIndex;
		boolean[][] match = new boolean[n+1][m+1];
		for(int i = 0 ; i <= n;i++){
			for(int j = 0 ;j <= m;j++){
				//No wildcard and no text
				if(i == 0 && j == 0){
					match[i][j] = true;
					continue;
				}
				//No Text
				if(i == 0){
					if(j == 1 && pat[0] == '*'){
						match[i][j] = true;//As empty text can match a pattern with just '*'
						continue;
					}
					match[i][j] = false;//As a pattern cannot match empty text
					continue;
				}
				//No Pattern
				if(j == 0){
					match[i][j] = false;
					continue;
				}
				if(pat[j - 1] == text[i - 1] || pat[j - 1] == '?'){
					match[i][j] = match[i - 1][j - 1];
				}else{
					if(pat[j - 1] == '*'){
						match[i][j] = match[i][j - 1] //Treating '*' as contributing empty space
									|| match[i - 1][j]; //Treating '*' as a wildcard for any sequence	
					}
				}
			}
		}
		return match[n][m];
	}
	/*
	 * Optimised version
	 */
	public static boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
		char[] pat = p.toCharArray();
		//Simplifying the pattern, by grouping all consecutive *'s to one
		int writeIndex = 0;
		boolean isFirst = true;
		for(int i = 0 ;i < pat.length;i++){
			if(pat[i] == '*'){
				if(isFirst){
					pat[writeIndex++] = pat[i];
					isFirst = false;
				}
			}else{
				pat[writeIndex++] = pat[i];
				isFirst = true;
			}
		}
		int n = text.length;
		int m = writeIndex;
        int i = 0, j = 0, index_txt = -1, index_pat = -1;
		while(i < n){
            if(j < m && (pat[j] == text[i] || pat[j] == '?')){
                i++;
                j++;
            }else if(j < m && pat[j] == '*'){
                index_txt = i;
                index_pat = j;
                j++;
            }else if(index_pat != -1){
                i = index_txt + 1;
                j = index_pat + 1;
                index_txt++;
            }else{
                return false;
            }
        }
        while(j < m && pat[j] == '*'){//For trailing '*'
                j++;
            }
            if(j == m){
                return true;
            }
            return false;
    }
	public static void main(String[] args){
		System.out.println(isMatch("aa", "a"));
	}
}
