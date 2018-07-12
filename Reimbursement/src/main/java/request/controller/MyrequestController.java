package request.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import masters.Request;
import reimbursement.Reimbursement;
import services.RequestServiceImpl;

public class MyrequestController {
	
	private static RequestServiceImpl service= Request.service;

	public static void getMyRequests(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("GET")) {
			HttpSession session=request.getSession(false);
			if(session ==null) {
				System.out.println("session is null");
				try {
					response.getWriter().print("{}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else { //session is not null
				PrintWriter out;
				try {
					out = response.getWriter();
					List<Reimbursement> l= service.listReimbursements((int)session.getAttribute("userid"));
					out.print(new Gson().toJson(l));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
