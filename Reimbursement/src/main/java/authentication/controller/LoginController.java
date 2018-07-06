package authentication.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import services.ServicesImpl;
import user.User;

public class LoginController {
	private static ServicesImpl service= new ServicesImpl();
	
	public static void loginCheck(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("GET")) {
			// return to main page or something. POST only here
		}
		
	    final StringBuilder builder = new StringBuilder();
	    String body = null;
	    try (BufferedReader reader = request.getReader()) {
	        if (reader == null) {
	            System.out.println("request is empty");
	        }
	        String line;
	        while ((line = reader.readLine()) != null) {
	            builder.append(line);
	        }
	        body = builder.toString();
	    } catch (final Exception e) {
	    }
	    
	    System.out.println("body: " +body);
		String username=body.split(";")[0];
		String inputPassword= body.split(";")[1];
		User user = service.getUser(username);
		System.out.println(username);
		System.out.println(inputPassword);
		if(user!=null && user.getPassword().equals(inputPassword)) {  // login successful
			System.out.println("yes");
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", inputPassword);
			response.setContentType("application/json");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(new Gson().toJson(user));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("yes");
			response.setContentType("application/json");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("{}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
