package startup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.DAOimpl;

@WebListener
public class Config implements ServletContextListener {
	public static DAOimpl dao = new DAOimpl();
	
    public void contextInitialized(ServletContextEvent event) {
    	// Do your thing during webapp's startup.
    	try {
			System.setOut(new PrintStream(new FileOutputStream("~/reimbursement.log")));
			System.out.println("e");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    public void contextDestroyed(ServletContextEvent event) {
    	// Do your thing during webapp's shutdown.
    }
    
}
