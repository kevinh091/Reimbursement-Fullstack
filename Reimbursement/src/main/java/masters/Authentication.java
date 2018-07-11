package masters;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.AuthenticationHelper;
import services.AuthenticationServicesImpl;

public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static AuthenticationServicesImpl service= new AuthenticationServicesImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		AuthenticationHelper.process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		AuthenticationHelper.process(request, response);
	}

}
