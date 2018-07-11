package startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.DAOimpl;

@WebListener
public class Config implements ServletContextListener {
	public static DAOimpl dao = new DAOimpl();
	
    public void contextInitialized(ServletContextEvent event) {
    	// Do your thing during webapp's startup.
    }
    public void contextDestroyed(ServletContextEvent event) {
    	// Do your thing during webapp's shutdown.
    }
    
}
