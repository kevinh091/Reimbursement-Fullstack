package authentication.master;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authentication.helper.AuthenticationHelper;
import services.ServicesImpl;

public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServicesImpl service= new ServicesImpl();

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
