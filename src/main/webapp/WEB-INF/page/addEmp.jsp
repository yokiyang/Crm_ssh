<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加员工页面</title>
<link href="${base }/style/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<h1>添加员工</h1>
	<hr />

	<form method="post" action="${base }/permission/emp/addOrUpdate.do">

		<table class='register'>
			<tr>
				<th>姓名</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>职称</th>
				<td><select name="title">
						<option value="">--请选择职称--</option>
						<option value="经理">经理</option>
						<option value="销售">销售</option>
						<option value="财务">财务</option>
				</select></td>
			</tr>
			<tr>
				<th>工资</th>
				<td><input type="text" name="salary" /></td>
			</tr>
			<tr>
				<th>入职时间</th>
				<td><input type="text" name="hiredate" id="hiredate"
					class="Wdate" onclick="WdatePicker({maxDate:'%y-%M-%d'})" /></td>
			</tr>
		</table>
		<input type="submit" value="确认添加" />

	</form>

	<br />
</body>
<script src="${base }/js/My97DatePicker/WdatePicker.js">
	
</script>
</html>