<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=path%>/include/css/frontskin.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />

<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/function/function.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="function_list"></table>
	
	<div id="queryFunctionWin" class="easyui-window" align="left" title="查询" style="width:460px; height: 200px">
		<form id="queryFunctionForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">用户名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="username_query"  />
					</td>
				</tr>
				<tr>
					<td class="thead">
						<div align="center">真实姓名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="realname_query" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryFunctionForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<!-- add -->
	<div id="addFunctionWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'"
		style="width: 500px; height: 180px; padding: 10px;">
		<form id="addFunctionForm" method="post">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>功能类型</th>
					<td >
						<select id="function_type_add" class="easyui-combobox" style="width:100px;">   
						    <option value="1">一级功能</option>   
						    <option value="2">二级功能</option>   
						</select>  
					</td>
					<th>一级功能</th>
					<td ><input type="text" id="funtion_type_one_add" style="width:100px;" /></td>
				</tr>
				<tr>
					<th>功能名称</th>
					<td><input type="text" id="function_name_add" style="width:100px;" /></td>
				</tr>
				<tr>
					<th>功能地址</th>
					<td><input type="text" id="function_url_add" style="width:100px;" /></td>
					
					<th>功能备注</th>
					<td><input type="text" id="function_remark_add" style="width:100px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="turnToAdd()"
					iconCls="icon-ok">新 增</a> <a href="javascript:void(0)"
					onclick="formClear('addFunctionForm')" class="easyui-linkbutton"
					iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<div id="modifyFunctionWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyFunctionForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>用户名</th>
					<td ><input type="text" id="username_edit" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" id="realname_edit" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="remark_edit" style="width:200px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg_edit"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="edit_btn" class="easyui-linkbutton"
					onclick="saveEdit()"
					iconCls="icon-ok">修 改</a> <a href="javascript:void(0)"
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyFunctionWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
