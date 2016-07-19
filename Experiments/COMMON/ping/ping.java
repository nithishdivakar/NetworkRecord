import java.net.*;
import java.io.*;
import java.util.regex.*;

public class Ping{
	public static void main(String args[]){
		long connectStart=0,connectFinish = 0;
		String host;
		int responseCode=0;
		if(args.length<1){
			System.out.println("Usage:\tPing host");
			return;
		}
		else if(Pattern.matches(".*([://]).*",args[0]))
			host=args[0];
		else
			host="http://"+args[0];
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(host).openConnection();
			connection.setRequestMethod("HEAD");
			connectStart = System.currentTimeMillis();
			responseCode = connection.getResponseCode();
			connectFinish = System.currentTimeMillis();
			if (responseCode < 100) {
				System.out.println("undetermined.");
			}
			else if(responseCode < 200) {
				System.out.println("informal (shouldn't happen on a GET/HEAD)");
			}
			else if(responseCode < 300) {
				System.out.println("\n\tSuccess\n\tTime Delay:"+(-connectStart+connectFinish));
			}
			else if(responseCode < 400) {
				System.out.println("\n\tRedirect\n\tTime Delay:"+(-connectStart+connectFinish));
			}
			else if(responseCode <500 ) {
				System.out.println("Client error");
			}
			else if(responseCode < 600) {
				System.out.println("Server Error");
			}
		}
		catch(Exception e){
			System.out.println("Can not connect");
		}
	}
}
