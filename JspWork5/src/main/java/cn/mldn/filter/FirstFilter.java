package cn.mldn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = "/*" )
public class FirstFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("销毁");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String url = request.getRequestURI();
		System.out.println(url);
		
		//需要把登陆页面与验证码放开
		if(url.endsWith("login1.jsp") || url.endsWith("code") || url.endsWith("check") )
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else
		{
			//如果session有值，表示已经登陆成功了
			Object a = request.getSession().getAttribute("name");
			
			if(a == null || a.equals(""))
			{
				response.sendRedirect("login1.jsp");
			}
			else
			{
				filterChain.doFilter(servletRequest, servletResponse);
			}
		}
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化");
	}

}
