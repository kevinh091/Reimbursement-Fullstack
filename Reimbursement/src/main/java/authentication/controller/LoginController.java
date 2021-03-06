package authentication.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import masters.Authentication;
import services.AuthenticationServicesImpl;
import user.User;

public class LoginController {
	private static AuthenticationServicesImpl service= Authentication.service;
	private final static Logger logger = Logger.getLogger(LoginController.class);

	public static void loginCheck(HttpServletRequest request, HttpServletResponse response) {
		logger.info("yo");
		System.out.println("yo1");
		if(request.getMethod().equals("GET")) {
			HttpSession session=request.getSession(false);
			if(session ==null) {
				System.out.println("session is null");
				try {
					response.getWriter().print("{}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("session is NOT null");
				response.setContentType("application/json");
				PrintWriter out;
				try {
					User user = service.getUser((String) session.getAttribute("username"));
					out = response.getWriter();
					out.print(new Gson().toJson(user));
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		if(request.getMethod().equals("POST")) {
			HttpSession testSession=request.getSession(false); //user already logged in
			if(testSession!=null) {
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print("{}");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			
			final StringBuilder builder = new StringBuilder();
			String body = null;
			try (BufferedReader reader = request.getReader()) {  //because it's a post, read body
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
			if(user!=null && user.getPassword().equals(inputPassword)) {  // login successful
				logger.info(username+"login successful");
				System.out.println("yes");
				HttpSession session=request.getSession();  
				session.setAttribute("username", username);
				session.setAttribute("password", inputPassword);
				session.setAttribute("userid", user.getUserId());
				session.setAttribute("userrole", user.getUserRole());
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
			else {  //login failed
				logger.info("login failed");
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
}
