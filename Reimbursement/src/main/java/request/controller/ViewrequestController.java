package request.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import masters.Request;
import reimbursement.Reimbursement;
import services.RequestServiceImpl;

public class ViewrequestController {

	private static RequestServiceImpl service= Request.service;

	public static void viewRequests(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")) {
			HttpSession session=request.getSession(false);
			if(session ==null || (int)session.getAttribute("userrole")!=2) {
				respondEmpty(request,response);
			}else { //session is not null
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

				PrintWriter out;
				try {
					out = response.getWriter();
					List<Reimbursement> l= service.managerReimbursements(body);
					out.print(new Gson().toJson(l));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void updateRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession(false); //verify the caller is a manager
		if(session ==null || (int)session.getAttribute("userrole")!=2) {
			respondEmpty(request,response);
		}else {
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
			
			try {
				int id=Integer.parseInt(body.split(";")[0]);
				int status= Integer.parseInt(body.split(";")[1]);
				int resolver = (int)session.getAttribute("userid");
				if(service.updateReimbursement(id, resolver, status)>0) {
					response.getWriter().print("{yes}");
				}
			}catch(NumberFormatException e){
				respondEmpty(request,response);
				return;
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void respondEmpty(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().print("{}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
