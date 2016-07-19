import java.net.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

class broadcast_sender extends Frame implements ActionListener{
	DatagramSocket s=null;
	DatagramPacket p=null;
	String message="";
	InetAddress iadd=null;
	int port;
	Button b=null;
	TextArea display=null;
	TextField t=null;
	public void actionPerformed(ActionEvent ae){
		send(t.getText());
		if(t.getText().trim().equals("bye"))
			System.exit(0);
		t.setText("");
	}
	void send(String msg){
		for(int i=400;i<9999;i++)
		{
			try{
				byte data[]=msg.getBytes();
				p=new DatagramPacket(data,data.length,iadd,i);
				s.send(p);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		message="Sent : "+msg.trim()+"\n"+message;
		display.setText(message);
	}
	public broadcast_sender(){
		try{
			iadd=InetAddress.getByName("localhost");
			int i=port=400;
			do{
				try{
					s=new DatagramSocket(i,iadd);
					port=i;
					i=0;
				}
				catch(Exception e){
					i++;
					System.out.println("**** "+i);
				}
			}while(i!=0);
			setTitle("Broadcast"+port);
			setSize(400,300);
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
	public static void main(String a[]){
		broadcast_sender s=new broadcast_sender();
	}
}
class broadcast_reciever extends Frame{
	DatagramSocket s=null;
	DatagramPacket p=null;
	String message="";
	InetAddress iadd=null;
	int port;
	TextArea display=null;
	public broadcast_reciever(){
		try{
			iadd=InetAddress.getByName("localhost");
			int i=port=400;
			do{
				try{
					s=new DatagramSocket(i,iadd);
					port=i;
					i=0;
				}
				catch(Exception e){
					i++;
					System.out.println("**** "+i);
				}
			}while(i!=0);
			setTitle("Receiver"+port);
			setSize(300,300);
			setVisible(true);
			display= new TextArea();
			display.setEditable(false);
			add(display);
			addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
			while(true){
				byte datain[]=new byte[50];
				DatagramPacket packet=new DatagramPacket(datain,50);
				s.receive(packet);
				String str=new String(datain);
				message="Received : "+str.trim()+"\n"+message.trim();
				display.setText(message.trim());
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String a[]){
		broadcast_reciever s=new broadcast_reciever();
	}
}