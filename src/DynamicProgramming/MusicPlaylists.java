//$Id$
package DynamicProgramming;

public class MusicPlaylists {
	static int[][] memo;
	static int mod = (int) Math.pow(10, 9) + 7;
	public static int combinations(int n, int l ,int k){
		if(memo[n][l] != 0){
			return memo[n][l];
		}
		if(n == l || n == k + 1) {
			System.out.println(factorial(n));
			memo[n][l] = factorial(n) % mod;
			return memo[n][l];
		}
		return (combinations(n - 1, l - 1, k) * n + combinations(n, l - 1, k) * (n-k)) % mod;
	}
	public static int factorial(int n){
		return n > 0 ? factorial(n - 1) * n : 1;
	}
	public int numMusicPlaylists(int N, int L, int K) {
       int mod = (int)Math.pow(10, 9) + 7;
       long[][] dp = new long[L+1][N+1];
       dp[0][0] = 1;
       for (int i = 1; i <= L; i++){
           for (int j = 1; j <= N; j++){
               dp[i][j] = (dp[i-1][j-1] * (N - (j-1)))%mod; 
               if (j > K){
                   dp[i][j] = (dp[i][j] + (dp[i-1][j] * (j-K))%mod)%mod;
               }
           }
       }
       return (int)dp[L][N];
   }
	public static void main(String[] args){
		int n = 16, l = 16, k =4;
		memo = new int[n+1][l+1];
		System.out.println(combinations(n,l,k));
	}
}
