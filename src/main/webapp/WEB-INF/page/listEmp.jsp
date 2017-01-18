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
	<br />
	<h3>欢迎您的登录</h3>
	<a href="${base }/user/logout.do">退出登陆</a>
	<br />
	<form method="post" action="${base }/permission/emp/selectname.do">
		姓名:<input type="text" name="name" /> <input class="select"
			type="submit" value="查询" />
	</form>
	<br />

	<form method="post" action="${base }/permission/emp/selecttitle.do">
		<select name="title">
			<option value="">--请选择职称--</option>
			<option value="经理">经理</option>
			<option value="销售">销售</option>
			<option value="财务">财务</option>
		</select> <input type="submit" class="select" value="查询" />
	</form>
	<br />

	<form method="post" action="${base }/permission/emp/selectsalary.do">
		薪资:<input type="text" name="minsalary"
			onkeyup="value=value.replace(/[^\d.]/g,'')" />~ <input type="text"
			name="maxsalary" onkeyup="value=value.replace(/[^\d.]/g,'')" /> <input
			type="submit" class="select" value="查询">
	</form>
	<br />

	<form method="post" action="${base }/permission/emp/selecthiredate.do">
		雇佣日期:<input id="d4311" name="begin" class="Wdate" type="text"
			onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})" />~
		<input id="d4312" name="end" class="Wdate" type="text"
			onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})" />
		<input type="submit" class="select" value="查询" />
	</form>
	<br/>
	<table class='register' id='myTable'>
		<tr>
			<th>&nbsp;</th>
			<th>序号</th>
			<th>姓名</th>
			<th>职称</th>
			<th>薪资</th>
			<th>入职时间</th>
			<th>操作</th>
		</tr>

		<c:if
			test="${!empty pageBean.employees && fn:length(pageBean.employees)>0 }"
			var="notNull">
			<c:forEach items="${pageBean.employees }" var="employee"
				varStatus="status">
				<tr>
					<td><input name="check" type="checkbox"
						value="${employee.id }"></td>
					<td>${status.count }</td>
					<td><a
						href="${base }/permission/emp/${employee.id }/toedit.do">${employee.name
							}</a></td>
					<td>${employee.title }</td>
					<td id="td" onclick="editTb('${employee.id}',this)">${employee.salary}</td>
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
	<br />
	<!-- <input type="hidden" name="action" value="batchRemove" /> -->
	<input type="button" class="select" value="批量删除"
		onclick="batchDelete()" />

	<br />
	<input type="button" class="select" value="添加员工"
		onclick="window.location='${base }/permission/addEmp.jsp'">
	<br />
	<hr />
	<div>
		<c:if test="${pageBean.pageNow!=1 }">
			<a href="${base }/permission/emp/1/${pageBean.pageSize }/list.do">首页</a>
			&nbsp;
			<a
				href="${base }/permission/emp/${pageBean.pageNow-1 }/${pageBean.pageSize}/list.do">上一页</a>
			&nbsp;
		</c:if>
		<c:choose>
			<c:when test="${pageBean.pageNow-3>=0 }">
				<c:set var="begin" value="${pageBean.pageNow-1 }" />
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="1" />
			</c:otherwise>
		</c:choose>
		<c:forEach var="p" begin="${begin }" end="${begin+2 }">
			<c:if test="${p<=pageBean.pageCount }">
				<a href="${base }/permission/emp/${p}/${pageBean.pageSize}/list.do">${p
					}</a>
			</c:if>
		</c:forEach>
		&nbsp;
		<c:if
			test="${pageBean.pageNow!=pageBean.pageCount && pageBean.pageCount!=0 }">
			<a
				href="${base }/permission/emp/${pageBean.pageNow+1 }/${pageBean.pageSize}/list.do">下一页</a>
			&nbsp;
			<a
				href="${base }/permission/emp/${pageBean.pageCount}/${pageBean.pageSize}/list.do">尾页</a>
			&nbsp;
		</c:if>
		当前 <span style="color:red"> <c:if
				test="${pageBean.pageCount==0 }" var="isEmpty">
					0/0
				</c:if> <c:if test="${!isEmpty }">
					${pageBean.pageNow }/${pageBean.pageCount }
				</c:if>
		</span>页 &nbsp; 跳转到第<input type="text" id="goPage"
			style="width:30px;text-align:center" />页 <input type="button"
			value="GO" onclick="go()" /> &nbsp; 每页显示: <select name="toSize"
			onchange="to(this)">
			<option value="2"
				<c:if test="${pageBean.pageSize==2 }">
					selected
				</c:if>>2</option>
			<option value="5"
				<c:if test="${pageBean.pageSize==5 }">
					selected
				</c:if>>5</option>
			<option value="10"
				<c:if test="${pageBean.pageSize==10 }">
					selected
				</c:if>>10</option>
		</select>条
	</div>

</body>
<script src="${base }/js/My97DatePicker/WdatePicker.js">
	
</script>
<script>
	function go() {
		var count = parseInt(document.getElementById("goPage").value);
		var reg = /^[1-9]\d*/;
		var pageCount = parseInt("${pageBean.pageCount}");

		if (reg.test(count)) {
			if (count > pageCount) {
				alert("不能超过最大页数!!!");
			} else {
				window.location = "${base }/permission/emp/"+count+"/${pageBean.pageSize}/list.do";
			}
		} else {
			alert("请您输入正整数!!!");
		}
	}

	function to(obj) {
		var value = obj.value;
		var url = "${base }/permission/emp/1/" + value+"/list.do";
		window.location = url;
	}

	function AjaxFunction() {
		var xmlHttp; // 缓存XHR对象便于 Ajax 使用
		try {
			xmlHttp = new XMLHttpRequest(); // Opera 8.0+, Firefox, Safari
		} catch (e) {
			// Internet Explorer Browsers
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					// 错误处理
					alert("Your browser broke!");
				}
			}
		}
		return xmlHttp;
	}

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

	function batchDelete() {
		if (confirm("确定要删除这些数据嘛?")) {
			//var table = document.getElementById("myTable");
			var checkboxes = document.getElementsByName("check");
			var ids = "";
			for ( var i = checkboxes.length - 1; i >= 0; i--) {
				if (checkboxes[i].checked) {
					ids += checkboxes[i].value + ":";
				}
			}
			ids = ids.substring(0, ids.length - 1);
			var xmlHttp = AjaxFunction();
			var url = "${base}/permission/emp/batchdelete.do";
			xmlHttp.open("POST", url, true);
			xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			xmlHttp.send("ids=" + ids);
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					// 获取响应中的文本
					var result = xmlHttp.responseText;
					if (result == 0) {
						for ( var i = checkboxes.length - 1; i >= 0; i--) {
							if (checkboxes[i].checked) {
								checkboxes[i].checked = "";
							}
						}
						location.reload();
					}
				}
			};

		}
	}

	var flag = true;
	function editTb(empId, obj) {
		if (flag) {
			var tempValue = obj.innerHTML;
			obj.innerHTML = "";
			var input = document.createElement("input");
			input.type = 'text';
			input.style.width = "60px";
			input.value = tempValue;
			obj.appendChild(input);
			input.select();
			flag = false;
			input.onkeydown = function(event) {
				var ev = event || window.event;
				var keyCode = ev.keyCode || ev.which;
				if (keyCode == 13) {
					var sal = this.value;
					var xmlhttp;
					if (window.XMLHttpRequest) {
						xmlhttp = new XMLHttpRequest();
					} else {
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					var url = "${base}/permission/emp/editSalary/" + empId
							+ "/" + sal+".do";
					xmlhttp.open("get", url, true);
					xmlhttp.send();
					xmlhttp.onreadystatechange = function() {
						if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
							var s = xmlhttp.responseText;
							if (s == "0") {
								flag = true;
								obj.removeChild(input);
								obj.innerHTML = sal;
								alert("修改成功!");
							} else {
								flag = true;
								obj.removeChild(input);
								obj.innerHTML = tempValue;
								alert("修改失败,请重新修改!");
							}
						}
					};
				}
				if (keyCode == 27) {
					flag = true;
					obj.removeChild(input);
					obj.innerHTML = tempValue;
				}
			};
		}
		;
	}
</script>
</html>




