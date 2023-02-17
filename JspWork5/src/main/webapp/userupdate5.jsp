<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="userUpdate5" method="post">
	
		<input type="hidden" name="id" value="${user.id}"> </br> 
		<!-- 通过转发 getRequestDispatcher同时将请求的对象传递到jsp页面，此时通过一个输入框显示id号，这样就可以在post请求中获取id
		如果不想在页面显示id，使用hidden隐藏  -->
		
		用户名：<input type="text" name="username" value="${user.username}"><br>
		密码：<input type="password" name="password" value="${user.password}"><br>

		<%-- 地址：<ex:select value="${user.addr}"></ex:select> --%>
		地址：<ex:select value="${user.addr}"></ex:select>
		
		<br> 性别：<input type="radio" name="sex" value="男" <c:if test="${user.sex=='男'}"> checked=${user.sex } </c:if>>男 
				 <input type="radio" name="sex" value="女"<c:if test="${user.sex=='女'}"> checked=${user.sex } </c:if>>女 <br> 
				 <input type="submit" value="修改">
	</form>
</body>
</html>
