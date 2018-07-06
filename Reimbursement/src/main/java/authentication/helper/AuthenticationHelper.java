package authentication.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authentication.controller.LoginController;

public class AuthenticationHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case "/Reimbursement/api/authentication":
			LoginController.loginCheck(request,response);
			break;
		default:;
		}
	}
}
