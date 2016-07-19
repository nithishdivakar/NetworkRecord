/*
 Directory Structure
 
 *HOME DIRECTORY
 |---HTTPServer.java
 |---HTTPServer.class
 |---Home.html
 |---page1.html
 |---page2.html
 |---error.html
 */

import java.io.*;
import java.net.*;
import java.util.*;
class HTTPServer extends Thread {
	
	Socket connectedClient = null;
	BufferedReader inFromClient = null;
	DataOutputStream outToClient = null;
	
	public HTTPServer(Socket client) {
		connectedClient = client;
	}
	
	public void run() {
		try {
			
			System.out.println("\n\nNEW REQUEST\n\n");
			System.out.println( "The Client "+connectedClient.getInetAddress() + ":" + connectedClient.getPort() + " is connected");
			
			inFromClient = new BufferedReader(new InputStreamReader (connectedClient.getInputStream()));
			outToClient = new DataOutputStream(connectedClient.getOutputStream());
			
			String requestString = inFromClient.readLine();
			String headerLine = requestString;
			
			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			String httpQueryString = tokenizer.nextToken();
			
			StringBuffer responseBuffer = new StringBuffer();
			
			System.out.println("Parameters of the HTTP Request");
			
			while (inFromClient.ready()){
			    // Reading the HTTP complete HTTP Query
			    responseBuffer.append(requestString + "<BR>");
			    System.out.println(requestString);
			    requestString = inFromClient.readLine();
			}
			if (httpMethod.equals("GET")) {
				if (httpQueryString.equals("/")) {//no resource is requested
					
					String header="<b>Parameters of the HTTP Request</b></br>";
					sendResponse(200, header+responseBuffer.toString(), false);
				} 
				else {
					//This is interpreted as a file name
					String fileName = httpQueryString.replaceFirst("/", "");
					if (new File(fileName).isFile()){
						sendResponse(200, fileName, true);
					}
					else {
						sendResponse(404, "error.html", true);
					}
				}
			}
			else sendResponse(404,"error.html", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendResponse (int statusCode, String responseString, boolean isFile)throws Exception {
		
		final String HTML_START="<html><title>HTTP Server in java</title><body>";
		final String HTML_END  ="</body></html>";
		
		String statusLine = null;
		String serverdetails = "Server: Java HTTPServer";
		String contentLengthLine = null;
		String fileName = null;
		String contentTypeLine = "Content-Type: text/html" + "\r\n";
		FileInputStream fin = null;
		
		if (statusCode == 200)
			statusLine = "HTTP/1.1 200 OK" + "\r\n";
		else
			statusLine = "HTTP/1.1 404 Not Found" + "\r\n";
		
		if (isFile) {
			fileName = responseString;
			fin = new FileInputStream(fileName);
			contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
			if (!fileName.endsWith(".htm") && !fileName.endsWith(".html"))
				contentTypeLine = "Content-Type: \r\n";
		}
		else {
			responseString = HTML_START + responseString +HTML_END;
			contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
		}
		
		outToClient.writeBytes(statusLine);
		outToClient.writeBytes(serverdetails);
		outToClient.writeBytes(contentTypeLine);
		outToClient.writeBytes(contentLengthLine);
		outToClient.writeBytes("Connection: close\r\n");
		outToClient.writeBytes("\r\n");
		
		if (isFile) sendFile(fin, outToClient);
		else outToClient.writeBytes(responseString);
		
		outToClient.close();
	}
	
	public void sendFile (FileInputStream fin, DataOutputStream out){// throws Exception {
		byte[] buffer = new byte[1024] ;
		int bytesRead;
		try{
			while ((bytesRead = fin.read(buffer)) != -1 ) {
				out.write(buffer, 0, bytesRead);
			}
			fin.close();
		}catch(Exception e){
			try {
				out.writeBytes("File Read ERROR");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void main (String args[]) throws Exception {
		ServerSocket Server = new ServerSocket (6789, 10, InetAddress.getByName("6.6.6.6"));
		System.out.println ("HTTP SERVER Running on port 6789");
		while(true) {
			Socket connected = Server.accept();
		    (new HTTPServer(connected)).start();
		}
	}
}