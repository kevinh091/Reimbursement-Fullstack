package authentication.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import masters.Authentication;
import services.ServicesImpl;
import user.User;

public class LogoutController {
	private static ServicesImpl service= Authentication.service;

	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("username") +"logged out");
		session.invalidate();  
	}
} 
