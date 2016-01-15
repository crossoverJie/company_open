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
<script src="<%=path%>/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/news/news.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>
<body>
	<table id="news_list"></table>
	

	
	<div id="queryNewsWin" class="easyui-window" align="left" title="查询" style="width:460px; height: 200px">
		<form id="queryNewsForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">角色名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="news_name_query"  />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryNewsForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<!-- add -->
	<div id="addNewsWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'" maximized="true"
		style="padding: 10px;">
		
		<form id="addUserForm" method="post">
			<table style="overflow: hidden;width: 100%">
				<tr>
					<th>标题</th>
					<td ><input type="text" id="news_name_add" /></td>
				</tr>
				<tr>
					<th>内容</th>
					<td>
					<textarea name="remark_add" id="remark_add"  rows="" cols=""></textarea>
					<script type="text/javascript">CKEDITOR.replace('remark_add');</script>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="turnToAdd()"
					iconCls="icon-ok">新 增</a> <a href="javascript:void(0)"
					onclick="formClear('addNewsForm')" class="easyui-linkbutton"
					iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<div id="modifyNewsWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyNewsForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>角色名</th>
					<td ><input type="text" id="news_name_edit" style="width:200px;" /></td>
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
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyNewsWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
