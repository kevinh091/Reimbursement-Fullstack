package helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authentication.controller.LoginController;
import authentication.controller.LogoutController;

public class AuthenticationHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case "/api/Authentication/login":
			LoginController.loginCheck(request,response);
			break;
		case "/api/Authentication/logout":
			LogoutController.logout(request,response);
			break;
		default:;
		}
	}
}
