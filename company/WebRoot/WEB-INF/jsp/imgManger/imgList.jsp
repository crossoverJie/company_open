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

<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/css/frontskin.css" type="text/css"
	rel="stylesheet" />
<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/imgManager/imgManager.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="role_list"></table>
	
	<div id="accreditNewsWin" class="easyui-window" align="left" title="授权" style="width:230px; height: 460px">
		
		<div class="easyui-layout" fit="true">
			<div region="south" border="true" style="height:30px;padding:2px;overflow: hidden;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="subSq()" style="float: right;">确认授权</a>
			</div>
			<div region="center" border="true" >
				<ul  id="sq" class="easyui-tree">  </ul>
			</div>
		</div>
	</div>
	
	<div id="queryRoleWin" class="easyui-window" align="left" title="查询" style="width:460px; height: 200px">
		<form id="queryRoleForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">角色名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="role_name_query"  />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryRoleForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<!-- add -->
	<div id="addRoleWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'"
		style="width: 360px; height: 180px; padding: 10px;">
		<form id="addUserForm" method="post">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>角色名</th>
					<td ><input type="text" id="role_name_add" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="remark_add" style="width:200px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="turnToAdd()"
					iconCls="icon-ok">新 增</a> <a href="javascript:void(0)"
					onclick="formClear('addRoleForm')" class="easyui-linkbutton"
					iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<div id="modifyRoleWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyRoleForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>角色名</th>
					<td ><input type="text" id="role_name_edit" style="width:200px;" /></td>
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
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyRoleWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
