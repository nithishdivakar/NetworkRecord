import java.net.*;
import java.io.*;
import java.util.Date;
class HTTPClient{
	public static void main(String args[]) throws Exception {
		int c; 
		URL hp = new URL("http://www.rtcgeci.host22.com/team.php");
		URLConnection hpCon = hp.openConnection();
		int len = hpCon.getContentLength(); 
		if(len == -1) 
			System.out.println("Content length unavailable.");
		else
			System.out.println("Content-Length: " + len);
		if(len != 0){
			InputStream input = hpCon.getInputStream(); 
			int i = len;
			while (((c = input.read()) != -1)){
				if(c=='\t'){
					System.out.print("\n");
				}
				System.out.print((char)c);
			}
			input.close();
		}
		else{
			System.out.println("No content available.");
		}
	}
}