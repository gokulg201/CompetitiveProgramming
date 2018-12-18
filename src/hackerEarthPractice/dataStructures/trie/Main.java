//$Id$
package hackerEarthPractice.dataStructures.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
	static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
		
		short nextShort()
        {
            return Short.parseShort(next()); 
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
	
	static String add(String ip,int ly)
	{
		char c[]=ip.toCharArray();
		for(int i=0;i<c.length;i++)
		{
			c[i]=(char)(97+(c[i]-97+ly)%26);
		}
		return new String(c);
	}
	
	public static void main(String args[]) throws Exception
	{
		FastReader sc =new FastReader();
		int n=sc.nextInt();
		int q=sc.nextInt();
		int ly=0;
		String ip[]=new String [n];
		int len[]=new int[n];
		
		for(int i=0;i<n;i++)
		{
			ip[i]=sc.next();
			len[i]=ip[i].length();
		}
		
		for(int i=0;i<q;i++)
		{
			int choice=sc.nextInt();
			if(choice ==1)
			{
				String check1=sc.next();
				check1=add(check1,ly);
				int len1=check1.length();
				int flag=0;
				for(int j=0;j<n;j++)
				{
					if((len1>=len[j]) && check1.contains(ip[j]))
					{
						flag=1;
						ly=i;
						break;
					}
				}
				if(flag!=1)
					System.out.println("NO");
				else
					System.out.println("YES");
			}
			else
			{
				int ind=sc.nextInt();
				int alpha=sc.nextInt();
			
				char c1=(char)(97+(alpha+ly)%26);
							
				ip[(ind+ly)%n]=new StringBuilder(ip[(ind+ly)%n]).append(c1).toString();
				len[(ind+ly)%n]++;
			}
		}
		
		
	}
}