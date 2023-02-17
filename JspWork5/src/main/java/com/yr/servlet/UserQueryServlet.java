package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.jdbc.UserDao;
import cn.mldn.user.Page;
import cn.mldn.user.User;

@WebServlet("/userList5")
public class UserQueryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		Page<User> page = new Page<User>(); // 申明一个page 实例化对象

		String pageSize = req.getParameter("pageSize");// 获取请求参数
		if (pageSize == null || pageSize.equals(""))  	// 判断pageSize等于null 或为空
		{
			page.setPageSize(5);  					  	// 设置默认每页显示数量
			
		} else {
			
			page.setPageSize(Integer.valueOf(pageSize));	// 反之将页数设置pageSize页
		}
		
		String pageNo = req.getParameter("pageNo");		// 获取请求参数
		if (pageNo == null || pageNo.equals(""))  		// 判断pageNo等于null 或为空
		{
			page.setPageNo(1);  					  	// 设置默认当前页
		} else {
			
			page.setPageNo(Integer.valueOf(pageNo));	// 反之将页数设置pageSize页
		}
		
		
		UserDao userDao = new UserDao();			  	// 申明一个userDao 实例化对象

		try {
			userDao.query(page); 						// 调用query方法并传递参数
			req.setAttribute("page", page);				// 设置属性
			req.getRequestDispatcher("userlist5.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
