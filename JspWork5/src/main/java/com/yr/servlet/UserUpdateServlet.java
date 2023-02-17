package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.jdbc.UserDao;
import cn.mldn.user.User;

@WebServlet("/userUpdate5")
public class UserUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		UserDao userDao = new UserDao();

		try {
			User user = userDao.queryId(Integer.valueOf(id));

			req.setAttribute("user", user);
			req.getRequestDispatcher("userupdate5.jsp").forward(req, resp);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		String username = req.getParameter("username");
		username = new String(username.getBytes("iso-8859-1"), "utf-8");

		String password = req.getParameter("password");

		String addr = req.getParameter("addr");
		addr = new String(addr.getBytes("iso-8859-1"), "utf-8");

		String sex = req.getParameter("sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");

		User u = new User();
		u.setId(Integer.valueOf(id));
		u.setUsername(username);
		u.setPassword(password);
		u.setAddr(addr);
		u.setSex(sex);

		UserDao userDao = new UserDao();

		try {
			userDao.update(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("userList5");

	}

}
