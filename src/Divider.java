//$Id$

public class Divider {
	public void solve(){
		int i=1;
		while(i>0){
			if(((2520*i)+1)%11==0){
				System.out.println((2520*i)+1);
				System.out.println(i);
				break;
			}
			i++;
		}
	}
	public static void main(String[] args){
		new Divider().solve();
	}
}
