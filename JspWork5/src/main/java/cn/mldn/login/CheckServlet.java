package cn.mldn.login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 作者 Jackson Tang
 * @version 创建时间：2022年11月15日 上午10:28:50 类说明:
 * 登录认证：每次出现验证码（验证码比用户名与密码先执行) 
 * 对应的JSP提交:login1.jsp
 */
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JDBCDemo demo = new JDBCDemo();
		String username = req.getParameter("username"); //以String形式返回请求参数的值
		String password = req.getParameter("password"); //以String形式返回请求参数的值
		String hiddenCode = req.getParameter("hiddenCode");   //验证码的标识
//		String rand = (String) req.getSession().getAttribute("code"); // 获取session对象，并获取属性

		demo.setName(username); 	// 获取输入的name 设置用户名
		demo.setPassword(password); // 获取输入的password 设置密码
		
		if(hiddenCode.equals("") || Integer.valueOf(hiddenCode) < 3) // ||称为逻辑或操作符。任何两个操作数任何一个为真，条件为真。 
		{
			
			try {
				int num1 = demo.query();
				if(num1 == 1) {
//					resp.sendRedirect("success.jsp");
					
					req.getSession().setAttribute("name", "a");
					
					resp.sendRedirect("userList5");
				}else {
					if(hiddenCode.equals("")) // 为什么每次判断为空，很简单隐藏了验证码的输入框，所以没有值。 
					{
						hiddenCode = "1";
						
					}else {
						hiddenCode = String.valueOf(Integer.valueOf(hiddenCode)+ 1);
					}
					
					req.setAttribute("hiddenCode", hiddenCode); //在此请求中存储属性
					req.getRequestDispatcher("login1.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String code = req.getParameter("randomCode");   //验证码的标识
			String rand = (String) req.getSession().getAttribute("code"); // 获取session对象，并获取属性
			
			// 换了一下位置就好了
			if (code.equalsIgnoreCase(rand)) //判断输入的验证码与session里面的属性，
			{
				try {
					int num = demo.query();
					if (num == 1) {
//						resp.sendRedirect("success.jsp");
						
						req.getSession().setAttribute("name", "a");
						
						resp.sendRedirect("userList5");
					} else {
						if(hiddenCode.equals("")) {
							hiddenCode = "1";
						}else {
							hiddenCode = String.valueOf(Integer.valueOf(hiddenCode) + 1);
						}
						System.out.println(hiddenCode);
						req.setAttribute("hiddenCode", hiddenCode); 
						req.getRequestDispatcher("login1.jsp").forward(req, resp);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				if(hiddenCode.equals("")) {
					hiddenCode = "1";
				}else {
					hiddenCode = String.valueOf(Integer.valueOf(hiddenCode) + 1);
				}
				req.setAttribute("hiddenCode", hiddenCode); 
				req.getRequestDispatcher("login1.jsp").forward(req, resp); 
			}
		}
	}
}
