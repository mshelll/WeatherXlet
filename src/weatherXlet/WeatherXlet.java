package weatherXlet;

import javax.microedition.xlet.UnavailableContainerException;
import javax.microedition.xlet.Xlet;
import javax.microedition.xlet.XletContext;
import javax.microedition.xlet.XletStateChangeException;

import UserInterface.WeatherScreen;
import com.sun.lwuit.Form;

import com.sun.lwuit.Display;


public class WeatherXlet implements Xlet{

	private XletContext context;
	private java.awt.Container container = null;
	
	public void initXlet(XletContext arg0) throws XletStateChangeException {
		// TODO Auto-generated method stub
		System.out.println("****Inside initXlet*****");
		try	{
			this.context = arg0;
			container = context.getContainer();
			container.setVisible(true);
			Display.init(container);
			//Display.getInstance().setPureTouch(true);
		}
		
		catch (UnavailableContainerException e) {
			e.printStackTrace();
			throw new XletStateChangeException("Error");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new XletStateChangeException("Error");
		}
	}
	
	public void startXlet() throws XletStateChangeException {
		// TODO Auto-generated method stub
		System.out.println("****Inside startXletdsgfdgdz*******");
		new WeatherScreen().show();

	}
	
	public void destroyXlet(boolean arg0) throws XletStateChangeException {
		// TODO Auto-generated method stub
		System.out.println("***Inside destroyXlet ******");
	}
	
	
	public void pauseXlet() {
		// TODO Auto-generated method stub
		System.out.println("****Inside pauseXlet*******");
	}

}
