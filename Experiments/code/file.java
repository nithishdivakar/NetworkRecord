import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;

class fileserver
{
	private ServerSocket server;
	public fileserver(int pnum)
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
                System.out.println("Client connected Welcome to Server");
			BufferedReader b=new BufferedReader(new InputStreamReader(ct.getInputStream()));
			PrintStream p=new PrintStream(ct.getOutputStream(),true);
			FileInputStream f=null;
			//p.println("Welcome to Server Enter 'bye' to close");
			String l;
				l=b.readLine(); 
				System.out.println("Client requesting file "+l+" ");
                f=new FileInputStream(l);
				int i;
				do
				{
					i=f.read();
					p.write(i);
				}while(i!=-1);
			System.out.println("File transferring successfully to client ");
			f.close();
			ct.close();
		}
		}
		catch(Exception er)
		{
			System.out.println("Caught "+er);
		}
	}
	public static void main(String args[])
	{
		
			fileserver es=new fileserver(9999);
			es.serve();
	}
		
}

class fileclient extends Frame implements ActionListener{
	TextField text=null;
	TextField text1=null;
	Button b=null;
	client c;
	public fileclient(){
		init();
	}
	void init(){
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
		setLayout(new BorderLayout());
		setTitle("Client");	
		setSize(300,120);
		setVisible(true);
		text=new TextField(12);
		text.setText("Type File name to download ");
		text.setEditable(false);
		add(text,BorderLayout.NORTH);
		text1=new TextField(12);
		add(text1,BorderLayout.CENTER);
		b=new Button("Send");
		b.addActionListener(this);
		add(b,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent ae){
		String snd=text1.getText();
		text1.setText((""));
		FileDialog f=new FileDialog(this,"Save file as ",1);
		f.setVisible(true);
		c.send(snd,f.getDirectory()+f.getFile());
		text.setText("File saved in "+f.getDirectory());
	}
	public static void main(String args[]){
		fileclient f=new fileclient();
		f.c=new client();
	}
}
class client{
	Socket cs=null;
	DataInputStream dis=null;
	PrintWriter pw=null;
	client(){
		try{
			cs=new Socket("127.0.0.1",9999);
			dis=new DataInputStream(cs.getInputStream());
			pw=new PrintWriter(cs.getOutputStream(),true);
		}
		catch(Exception e){
		}
	}
	void send(String fileName,String newName){
		int fread;
		try{
			pw.println(fileName);
			FileOutputStream f=new FileOutputStream(newName);
			do
			{
				fread=dis.read();
				if(fread!=-1)
					f.write(fread);
			}while(fread!=-1);
			f.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
