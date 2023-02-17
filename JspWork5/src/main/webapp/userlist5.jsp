<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
		function aa() {
			var pageSize = document.getElementById("pageSize").value;
			window.location.href="userList5?pageSize="+pageSize;
		}
</script>

<body>
	<table border="1" rules="all">
		<tr>
			<th>id</th>
			<th>username</th>
			<th>addr</th>
			<th>sex</th>
			<th>password</th>
			<th>delete</th>
			<th>update</th>
		</tr>
		<c:forEach items="${page.list }" var="u"> <!-- 为什么用page.list 因为传递的是page对象，list是page对象里面的集合  -->
			<tr>
				<td>${u.id }</td>
				<td>${u.username }</td>
				<td>${u.addr }</td>
				<td>${u.sex }</td>
				<td>${u.password }</td>
				<th><a href="userDelete5?id=${u.id }">删除</a></th>
				<th><a href="userUpdate5?id=${u.id }">修改</a></th>
			</tr>
		</c:forEach>
	</table>
		<br><br>
		总共有 <b>${page.count}</b>   条,
		每页有<b>${page.pageSize}</b> 条,
		当前浏览第 <b>${page.pageNo}</b>   页,
		总共有 <b>${page.pageCount}</b>   页,

<%-- 		总共<b>${page.count } </b>	条 --%>
<%-- 		当前页数 / 总页数(${page.pageNo}/${page.pageCount}) --%>
		
		每页<select name="pageSize" onchange="aa()" id="pageSize">
			<option value="5" <c:if test="${page.pageSize == 5 }">selected</c:if>>5</option>
			<option value="10" <c:if test="${page.pageSize == 10 }">selected</c:if>>10</option>
			<option value="20" <c:if test="${page.pageSize == 20 }">selected</c:if>>20</option>
		</select> 条
		
		<br><br><br>
		<a href="userList5?pageNo=1&pageSize=${page.pageSize}">首页</a>
		
		<c:if test="${page.pageNo !=1 }">
			<a href="userList5?pageNo=${page.pageNo - 1 }&pageSize=${page.pageSize}">上页</a>
		</c:if>
		
		<c:if test="${page.pageNo != page.pageCount }">
			<a href="userList5?pageNo=${page.pageNo + 1 }&pageSize=${page.pageSize}">下页</a>
		</c:if>
	
		<a href="userList5?pageNo=${page.pageCount}&pageSize=${page.pageSize}">尾页</a>
	<br><br>	
	<a href="useradd5.jsp">新增</a>
	
</body>
</html>
