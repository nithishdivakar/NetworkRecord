import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

interface AddServerInf extends Remote{
	public String add(String a,String b)throws RemoteException;
}
class AddServerImp extends UnicastRemoteObject implements AddServerInf{
	public AddServerImp()throws RemoteException{
	}
	public String add(String A,String B) throws RemoteException{
		String c="";
		int i,carry=0,la,lb,lc,sum;
		char a[]=A.toCharArray();
		char b[]=B.toCharArray();
		la=A.length()-1;
		lb=B.length()-1;
		lc=(la>lb)?la+1:lb;
		for(i=lc;lb>=0&&la>=0;lb--,la--){
			sum=a[la]+b[lb]-48+carry;
			if(sum-48>9){
				sum=sum-10;
				carry=1;
			}
			else
				carry=0;
			c=(char)sum+c;
		}
		for(i=la;la>=0;la--){
			sum=a[la]+carry;
			if(sum-48>9){
				sum=sum-10;
				carry=1;
			}
			else
				carry=0;
			c=(new String())+(char)sum+c;
		}
		for(i=lb;lb>=0;lb--){
			sum=b[lb]+carry;
			if(sum-48>9){
				sum=sum-10;
				carry=1;
			}
			else
				carry=0;
			c=(new String())+(char)sum+c;
		}
		if(carry==1){
			c='1'+c;
		}
		return c;
	}
}
class AddServer{
	public static void main(String a[]){
		try{
			AddServerImp server=new AddServerImp();
			Naming.rebind("AddServer1",server);
		}
		catch(Exception e){
			System.out.println("Error :"+e);
		}
	}
}
class AddClient{
	public static void main(String args[]){
		try{
			AddServerInf server=(AddServerInf)Naming.lookup("rmi://localhost/AddServer1");
			String str;
			String a,b,sum;
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter first number : ");
			str=br.readLine();
			a=(str);
			System.out.print("Enter second number : ");
			str=br.readLine();
			b=(str);
			sum=server.add(a,b);
			System.out.print(a+" + "+b+" = "+sum);
		}
		catch(Exception e){
		}
	}
}