<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mu" uri="/myUtil" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<link type="text/css" rel="stylesheet" href="${base }/style/style.css"></link>
	<script>
		function changeToken(){
			document.getElementById("code").src="token#" + new Date().getTime();
		}
	</script>
		</head>
		<body>
			<h1>登录</h1>
			<br/>
			<c:choose>
				<c:when test="${param.info==1 }">
					<p style="color:red;font-weight:bolder">对不起,用户名或者密码有误,请重新输入!</p>
				</c:when>
				<c:when test="${param.info==2 }">
					<p style="color:red;font-weight:bolder">对不起,验证码有误,请重新输入!</p>
				</c:when>
				<c:when test="${param.info==3 }">
					<p style="color:red;font-weight:bolder">对不起,请先登陆!</p>
				</c:when>
				<c:otherwise>
					<p></p>
				</c:otherwise>
			</c:choose>
			<br />
		
			<form method="post" action="${base }/user/login.do">
				<table class="login">
					<tr>
						<th>用户名</th>
							<c:set var="user" value="${mu:toUTF8(cookie.userInfo.value) }"/> 
						<td><input type="text" name="username" id="username"
							<c:if test="${!empty fn:split(user,':')[0] }">
								value="${fn:split(user,':')[0] }"
							</c:if>
						/></td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input type="password" name="password" id="password"
							<c:if test="${!empty fn:split(user,':')[1] }">
								value="${fn:split(user,':')[1] }"
							</c:if>
						/></td>
					</tr>
					<tr>
						<th>验证码</th>
						<td><input type="text" name="token" id="token"/></td>
					</tr>
				</table>
				<p>
					<img id="code" src="${base }/token" />
					<a href="javascript:changeToken()">看不清,换一个</a>
				</p>
				<p><input type="checkbox" name="noLogin" value="0"/>七天免登陆</p>
				<p>
					<input type="submit" value="登录" />
				</p>
			</form>
		</body>
		</html>

	



