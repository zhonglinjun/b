<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function changeCode()
	{
		//页面都有缓存，因为页面一进来就加载过一次servlet,以后就不会再加载。
		var imgNode = document.getElementById("vimg");
		imgNode.src = "code?t="+ Math.random(); // 传了一个参数t，就可以改变验证码了呢？
	}
	function aa() {
		var hiddenCodeId = document.getElementById("hiddenCodeId").value; 
		
		if("" == hiddenCodeId || hiddenCodeId < 3){
			document.getElementById("codeDiv").style.display = "none";
		}else{
			document.getElementById("codeDiv").style.display = "";
		}
	}
</script>
<body onload="aa()"> <!-- onload 事件会在页面加载完成后立即发生  -->
	<h1>登录页面</h1>
	<form action="check" method="post">
		<table>

			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username"></td>
			</tr>

			<tr>
				<td>密码:</td>
				<td><input type="password" name="password"></td>
			</tr>
			
			
			<tr>
				<td><input type="submit" tabindex="3" value="提交" class="btn btn-primary" /></td>
				<td><input type="reset" name="reset" value="清空" class="input-button" id="btn2" /></td>
			</tr>
																			 
		</table>
			<input type="hidden" name="hiddenCode" id="hiddenCodeId" value="${hiddenCode}">
			
			<div id="codeDiv" style="display: none">
			验证码:
			<input type="text" name="randomCode"  maxlength="4" size="4"> 
			<img id="vimg" title="点击更换"  onclick="changeCode();"  src="<%=request.getContextPath() %>/code"> <br/> <!-- 返回请求URI中指示请求上下文的部分。  -->
			
			</div>
			
	</form>
	
</body>
</html>