package request.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import masters.Request;
import services.RequestServiceImpl;

public class NewrequestController {
	private static RequestServiceImpl service= Request.service;

	public static void newRequest(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("POST")) {
			HttpSession session=request.getSession(false);
			if(session ==null) {
				System.out.println("session is null");
				try {
					response.getWriter().print("{}");
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
			service.insertReimbursement(jsonObject.get("amount").getAsInt(), 
					jsonObject.get("description").getAsString(), 
					null,
					(int)session.getAttribute("userid"),
					jsonObject.get("type").getAsInt());
		}
	}
}
