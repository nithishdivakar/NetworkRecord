import java.net.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

class socket extends Frame implements ActionListener{
	DatagramSocket s=null;
	DatagramPacket p=null;
	String message="";
	InetAddress iadd=null;
	int port,port1;
	Button b=null;
	TextArea display=null;
	TextField t=null;
	public void actionPerformed(ActionEvent ae){
		send(t.getText());
		t.setText("");
	}
	void send(String msg){
		byte data[]=msg.getBytes();
		p=new DatagramPacket(data,data.length,iadd,port1);
		try{
			s.send(p);
			message="Sent : "+msg.trim()+"\n"+message;
			display.setText(message);
		}
		catch(Exception e){
		}
	}
	public socket(){
		try{
			iadd=InetAddress.getByName("localhost");
			int i=port=400;
			port1=port+1;
			do{
				try{
					s=new DatagramSocket(i,iadd);
					if(i!=port){
						port1=i-1;
						port=i;
					}
					i=0;
				}
				catch(Exception e){
					i++;
					System.out.println("**** "+i);
				}
			}while(i!=0);
			setTitle("Port="+port);
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
		}
	}
	public static void main(String a[]){
		socket s=new socket();
	}
}