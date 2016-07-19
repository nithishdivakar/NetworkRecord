/*
<applet code=MyApplet.class width=350 height=350></applet>
<!--
*/
import java.applet.*;
import java.awt.*;
public class MyApplet extends Applet{
	public void paint(Graphics g){
		double i,x1,y1,x2,y2;
		int r=100,xc=150,yc=150,color=0;
		for(i=0;true;i+=(3.14/180)){
			x1=(int)(r*Math.sqrt(2)*Math.cos(i))+xc;
			y1=(int)(r*Math.sqrt(2)*Math.sin(i))+yc;
			x2=(int)(r*Math.sqrt(2)*Math.cos(i+3.14/2))+xc;
			y2=(int)(r*Math.sqrt(2)*Math.sin(i+3.14/2))+yc;
			g.setColor(Color.red);
			g.drawOval(xc-r,yc-r,2*r,2*r);
			g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
			try{
				Thread.sleep(8);
			}
			catch(Exception e){
			}
			g.setColor(Color.white);
			g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
		}
	}
}
//-->;