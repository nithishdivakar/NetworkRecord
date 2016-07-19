import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;

public class EchoServer
{
	private ServerSocket server;
	public EchoServer(int pnum)
	{
		try
		{
			server=new ServerSocket(pnum);
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
	}
	public void serve()
	{
		try
		{
		while(true)
		{
			System.out.println("Waiting for client");
			Socket ct=server.accept();
                System.out.println("Client connected ");
			BufferedReader b=new BufferedReader(new InputStreamReader(ct.getInputStream()));
			PrintWriter p=new PrintWriter(ct.getOutputStream(),true);
			//p.println("Welcome to Server Enter 'bye' to close");
			String l;
			do
			{
				l=b.readLine(); 
                System.out.println(l);
                if(l!=null)
					p.println("Server : "+l);
			}while(!l.trim().equals("bye"));
			ct.close();
                System.out.println("Client disconnected");
		}
		}
		catch(Exception er)
		{
			System.out.println("Caught "+er);
		}
	}
	public static void main(String args[])
	{
		
			EchoServer es=new EchoServer(9999);
			es.serve();
	}
		
}
class myframe extends Frame implements ActionListener{
	String msg=null;
	TextArea text=null;
	TextField text1=null;
	Button b=null;
	Panel p=null;
	client c;
	public myframe(){
		init();
	}
	void init(){
		msg="";
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){	System.exit(0);}});
		setLayout(new BorderLayout());
		//p=new Panel();
		//add(p,BorderLayout.SOUTH);
		//setLayout(new BorderLayout());
		setTitle("Client");	
		setSize(300,300);
		setVisible(true);
		text=new TextArea("");
		text.setSize(180,50);
		text.setEditable(false);
		add(text,BorderLayout.NORTH);
		text1=new TextField(12);
		text1.setSize(100,100);
		add(text1,BorderLayout.CENTER);
		b=new Button("Send");
		b.addActionListener(this);
		b.setSize(100,100);
		add(b,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent ae){
		String snd=text1.getText();
		msg=c.send(snd)+"\nClient : "+snd+"\n"+msg;
		text.setText((msg));
		text1.setText((""));
		repaint();
	}
	public void paint(Graphics g)	{
		g.drawString(msg,300,300);	
	}
	public static void main(String args[]){
		myframe f=new myframe();
		f.c=new client();
	}
}
class client{
	Socket cs=null;
	BufferedReader br=null;
	PrintWriter pw=null;
	client(){
		try{
			cs=new Socket("127.0.0.1",9999);
			br=new BufferedReader(new InputStreamReader(cs.getInputStream()));
			pw=new PrintWriter(cs.getOutputStream(),true);
		}
		catch(Exception e){
			System.out.println(" ");
		}
	}
	String send(String l){
		try{
			pw.println(l);
			if(l.trim().equals("bye")){
				cs.close();
				System.exit(0);
			}
			l=br.readLine();
		}
		catch(Exception e){
		}
		return l;
	}
}
