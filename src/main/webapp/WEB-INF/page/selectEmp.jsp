<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>员工列表</title>
<link href="${base }/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>员工信息</h1>
	<hr />
	<h3>欢迎您的登录</h3>
	<a href="${base }/user/logout.do">退出登陆</a>
	<hr />
	<table class='register'>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>职称</th>
			<th>薪资</th>
			<th>入职时间</th>
			<th>操作</th>
		</tr>
		<c:if test="${!empty emps && fn:length(emps)>=0 }" var="notNull">
			<c:forEach items="${emps }" var="employee" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><a
						href="${base }/permission/emp/${employee.id }/toedit.do">${employee.name
							}</a></td>
					<td>${employee.title }</td>
					<td>${employee.salary }</td>
					<td><fmt:formatDate value="${employee.hiredate }"
							pattern="yyyy-MM-dd" /></td>
					<td><a href="javascript:void(0);"
						onclick="deleteEmp('${employee.id}','${status.count }')">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${!notNull }">
			<tr>
				<td colspan='7'>暂无员工信息...</td>
			</tr>
		</c:if>
	</table>

	<a href="${base }/permission/emp/1/2/list.do"><input type="button" value="返回"></a>
</body>
<script>
function deleteEmp(empId, status) {
	if (confirm("确定要删除该数据嘛?")) {
		//var table = document.getElementById("myTable");
		var xmlHttp = AjaxFunction();
		var url = "${base}/permission/emp/"+empId+"/delete.do";
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取响应中的文本
				var result = xmlHttp.responseText;
				if (result == 0) {
					//table.deleteRow(status);
					location.reload();
				}
			}
		};
	}
}
</script>
</html>