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
<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/index/index.js" type="text/javascript"></script>
<base href="<%=basePath%>">

<title>欢迎进入XX企业后台管理</title>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div data-options="region:'north',border:false"
		style="background:#E1EFED;padding:10px;">
		<%-- <img src="<%=basePath%>include/trotter.gif" /> --%>
		<label>LOGO</label>

		<div style="color: black;margin-left: 20px;padding-top: 3px;">
			欢迎您,【${user.username }】 <a id="loginOut" href="javascript:void(0)"
				onclick="loginOut()" data-options="iconCls:'icon-remove'"
				style="margin-left: 1000px" class="easyui-linkbutton" plain="true">注销</a>
			<a data-options="iconCls:'icon-edit'" href="javascript:void(0)"
				class="easyui-linkbutton" plain="true" onclick="showInfo()">修改密码</a>
		</div>


	</div>
	<div data-options="region:'west',split:true,title:'导航菜单'"
		style="width:250px;">
		<div id="menu" class="easyui-accordion" data-options="multiple:true">
			<div title="系统管理" data-options="iconCls:'icon-sys',selected:true"
				style="overflow:auto;padding:10px;">
				<p>
					<a href="javascript:void(0)" onClick="getMain(this,'user/turnToUserList')">用户管理</a>
				</p>
				<p>
					<a href="javascript:void(0)" onClick="getMain(this,'role/turnToRoleList')">角色管理</a>
				</p>
			</div>
			
			<div title="信息管理"
				data-options="iconCls:'icon-sys' "
				style="padding:10px;">content2</div>
				
			<div title="Title3" data-options="iconCls:'icon-sys' ">content3</div>
		</div>
	</div>

	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" style=""></div>
	</div>
	<div data-options="region:'south',border:false"
		style="height:40px;background:#A9FACD;padding:20px;">
		<center>技术支持：crossoverJie</center>
	</div>

	<div id="password" class="easyui-window" align="left" title="修改密码"
		style="width:500px; height: 200px">
		<form id="queryKQ_mainForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br/>
				<tr>
					<td class="thead">
						<div align="center">当前密码:</div></td>
					<td><input class="easyui-validatebox" type="password"
						id="currentPwd" style="width: 149px;" /></td>
				</tr>
				<tr>
					<td class="thead">
						<div align="center">新密码:</div></td>
					<td><input class="easyui-validatebox" type="password"
						data-options="required:true" missingMessage="必填项" id="pwd"
						style="width: 149px;" /></td>
				</tr>

				<tr>
					<td class="thead">
						<div align="center">确认密码:</div></td>
					<td><input class="easyui-validatebox" type="password"
						required="required" missingMessage="必填项" id="rpwd"
						style="width: 149px;" validType="equals['#pwd']" /></td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submit()"
					class="easyui-linkbutton" iconCls="icon-ok">提交</a> <a
					href="javascript:void(0)" onclick="formClear('queryKQ_mainForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
</body>
</html>
