package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.jdbc.UserDao;

@WebServlet("/userDelete5")
public class UserDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
	
		UserDao userDao = new UserDao();
		
		try {
			userDao.delete(Integer.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("userList5");
	}

}
