package masters;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.AuthenticationHelper;
import helpers.RequestHelper;
import services.RequestServiceImpl;

public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static RequestServiceImpl service= new RequestServiceImpl();
	
    public Request() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestHelper.process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestHelper.process(request, response);
	}

}
