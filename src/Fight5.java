import java.util.Scanner;

//$Id$

public class Fight5 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for(int i = 0;i < T;i++){
			int N = scanner.nextInt();
			int M = scanner.nextInt();
			int L = scanner.nextInt();
			int R = scanner.nextInt();
			int C = scanner.nextInt();
			int P = scanner.nextInt();
			int Q = scanner.nextInt();
			int S = scanner.nextInt();
			
			int[] money = new int[N + 1];
			int temp_L = L;
			int temp_R = R;
			for(int k = L;k <= R;k++){
				 money[k] = money[k] + C;
			}
			for(int j = 1;j < M;j++){
				L = (temp_L * P + temp_R) % N + 1;
				R = (temp_R * Q + temp_L) % N + 1;
				temp_L = L;
				temp_R = R;
				if(L > R){
					int temp = L;
					L = R;
					R = temp;
				}
				C = (C * S) % 1000000 + 1;
				for(int k = L;k <= R;k++){
					 money[k] = money[k] + C;
				}
			}
			int max = 0;
			int pos = 0;
			for(int j = 0;j < money.length;j++){
				if(max < money[j]){
					max = money[j];
					pos = j;
				}
			}
			System.out.println(pos+" "+max);
		}
	}
}
