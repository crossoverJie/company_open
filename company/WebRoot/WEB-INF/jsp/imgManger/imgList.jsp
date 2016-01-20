<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/css/frontskin.css" type="text/css"
	rel="stylesheet" />
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/imgManager/imgManager.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>



	<table id="img_list"></table>


	<div id="queryImgWin" class="easyui-window" align="left" title="查询"
		style="width:460px; height: 200px">
		<form id="queryImgForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">名称:</div></td>
					<td><input class="easyui-validatebox" id="img_name_query" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryImgForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>

	<!-- add -->
	<div id="addImgWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'" maximized="true"
		style="padding: 10px;">

		<form id="addImgForm" action="img/create" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="exampleInputEmail1">名称</label> <input
					type="text" name="name" class="form-control" id="name_add" required autofocus="autofocus"
					placeholder="名称">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">备注</label> 
				<textarea class="form-control" name="remark" placeholder="备注" required></textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputFile">文件</label> 
				<input type="file" required  id="file" name="file">
				<p class="help-block">请选择高度不超过512px的图片</p>
			</div>
			<button type="submit"  class="btn btn-default">提交</button>
			<button type="reset" class="btn btn-danger">重置</button>
		</form>
		
		
<div class="modal fade" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
      	只能上传图片
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
		
	</div>


</body>
</html>
