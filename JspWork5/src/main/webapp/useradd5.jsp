<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page errorPage="error.jsp"%>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="userAdd5" method="post">
		用户名：<input type="text" name="username"><br>
		密码：<input type="password" name="password"><br>
		地址：<ex:select ></ex:select>
		<br>
		性别：<input type="radio" name="sex" value="男">男  <input type="radio" name="sex" value="女">女<br>
		<input type="submit" value="新增">
	</form>
</body>
</html>
