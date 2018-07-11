package helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authentication.controller.LoginController;
import authentication.controller.LogoutController;
import request.controller.NewrequestController;

public class RequestHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case "/api/Request/new":
			NewrequestController.newRequest(request, response);
			break;
		case "/api/Request/my":
			LogoutController.logout(request,response);
			break;
		default:;
		}
	}
}
