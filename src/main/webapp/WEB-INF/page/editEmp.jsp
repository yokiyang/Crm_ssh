<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改员工</title>
<link href="${base }/style/style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
	<h1>修改员工</h1>
	<hr />
	<form action="${base }/permission/emp/addOrUpdate.do" method="post">
		<input style="display:none" name="id" value="${id }"/>
		<table class="register">
			<tr>
				<th>姓名</th>
				<td><input type="text" name="name" id="name" value="${emp.name }"/></td>
			</tr>
			<tr>
				<th>职称</th>
				<td><input type="text" id="tit" value="${emp.title }" style="display:none" />
				<select name="title">
						<option name="opt" value="经理">经理</option>
						<option name="opt" value="销售">销售</option>
						<option name="opt" value="财务">财务</option>

				</select></td>
			</tr>
			<tr>
				<th>薪资</th>
				<td><input type="text" name="salary" id="salary" value="${emp.salary }" onkeyup="value=value.replace(/[^\\d.]/g,'')" /></td>
			</tr>
			<tr>
				<th>入职时间</th>
				<td><input type="text" name="hiredate" id="hiredate"
					class="Wdate" onclick="WdatePicker({maxDate:'%y-%M-%d'})" value="${emp.hiredate }"/></td>
			</tr>
		</table>
		<p class="buttonArea">
			<input type="submit" value="确定" /> <input type="reset" value="重置" />
		</p>
	</form>
</body>
<script src="${base }/js/My97DatePicker/WdatePicker.js">
		</script>
<script type="text/javascript">
			var options= document.getElementsByName("opt");
			var tit= document.getElementById("tit").value;
			for(var i=0;i<options.length;i++){
				if(options[i].value==tit){
					options[i].selected=true;
				}
			}
		</script>
</html>