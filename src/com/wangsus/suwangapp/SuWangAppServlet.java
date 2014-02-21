package com.wangsus.suwangapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SuWangAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user != null) {
//			resp.setContentType("text/plain");
//			resp.getWriter().println("Hello, " + user.getNickname());
			req.setAttribute("name", user.getNickname());
			req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);			
		} else {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
	}
}
