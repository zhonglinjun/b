package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.jdbc.UserDao;
import cn.mldn.user.User;

@WebServlet("/userAdd5")
public class UserAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		username = new String(username.getBytes("iso-8859-1"),"utf-8");
		
		String password = req.getParameter("password");
		
		
		String addr = req.getParameter("addr");
		addr = new String(addr.getBytes("iso-8859-1"),"utf-8");
		 
		 System.out.println(addr);
		
		String sex = req.getParameter("sex");
		sex = new String(sex.getBytes("iso-8859-1"),"utf-8");
		
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setAddr(addr);
		u.setSex(sex);
		
		UserDao userDao = new UserDao();
		
		try {
			userDao.add(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("userList5");
	}
}
