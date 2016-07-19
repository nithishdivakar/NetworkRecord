import java.net.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

class server implements Runnable{
	ServerSocket s=null;
	Socket c[]=null;
	BufferedReader in[]=null;
	PrintWriter out[]=null;
	Thread t[]=null;
	int number_client;
	String message=null;
	String send(String message){
		for(int i=0;i<number_client;i++){
			out[i].println(message);
		}
		return message;
	}
	void recieve(){
		
	}
	public void run(){
		int client_no=Integer.parseInt(Thread.currentThread().getName());
		do{
			try{
				String s=in[client_no].readLine();
				if(s.trim().equals("bye"))
					break;
				s="Client "+client_no+" : "+s;
				send(s);
				System.out.println(s);	//
			}
			catch(Exception e){
				System.out.println("Error");
				break;
			}
		}while(true);
		System.out.println("Client "+client_no+" disconnected");
	}
	public server(){
		int MAX=number_client=20;
		try{
			s=new ServerSocket(768);
			c=new Socket[number_client];
			in=new BufferedReader[number_client];
			out=new PrintWriter[number_client];
			t=new Thread[number_client];
			number_client=0;
			for(int i=0;i<MAX;i++){
				c[i]=s.accept();
				number_client++;
				in[i]=new BufferedReader(new InputStreamReader(c[i].getInputStream()));
				out[i]=new PrintWriter(c[i].getOutputStream(),true);
				t[i]=new Thread(this,""+i);
				t[i].start();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String a[]){
		server s=new server();
	}
}

class client extends Frame implements ActionListener{
	Socket c=null;
	BufferedReader in=null;
	PrintWriter out=null;
	Button b=null;
	TextArea display=null;
	TextField t=null;
	String msg="";
	public client(){
		try{
			setTitle("Client");
			c=new Socket("localhost",768);
			setSize(200,300);
			setVisible(true);
			
			display= new TextArea();
			display.setEditable(false);
			t=new TextField(10);
			t.setEditable(true);
			b=new Button("Send");
			
			setLayout(new BorderLayout());
			
			add(display,BorderLayout.NORTH);
			add(t,BorderLayout.CENTER);
			add(b,BorderLayout.SOUTH);
			
			addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
			b.addActionListener(this);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae){
		out.println(t.getText());
		if(t.getText().trim().equals("bye"))
			System.exit(0);
		t.setText("");
	}
	public static void main(String args[]){
		client c1=new client();
		try{
			c1.in=new BufferedReader(new InputStreamReader(c1.c.getInputStream()));
			c1.out=new PrintWriter(c1.c.getOutputStream(),true);
		}
		catch(Exception e){
			System.out.println(e);
		}
		String s=null;
		do{
			try{				
				s=c1.in.readLine();
				c1.msg=s+"\n"+c1.msg;
				c1.display.setText(c1.msg);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}while(!s.trim().equals("bye"));
	}
}