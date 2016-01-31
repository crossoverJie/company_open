<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>发帖</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="<%=path%>/css/index.css" type="text/css"
	rel="stylesheet" />
<script src="<%=path%>/ckeditor/ckeditor.js" type="text/javascript"></script>
    <style type="text/css">
    </style>
</head>
<body>
<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>

	<hr/>
<!-- 栅格系统 -->
<div class="container-fluid">
	<div class="row">
		
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-info">
				
				<div class="panel-heading">
					<h3 class="panel-title">发帖</h3>
				</div>

			
				<div class="panel-body">
				<form id="createTopicForm" method="post" action="<%=path %>/createTopic">
				  <div class="form-group">
				    <input type="text" required="required" name="title" class="form-control" id="title" placeholder="主题/标题">
				  </div>
				  <div class="form-group">
				    <textarea required="required" class="form-control" id="content" name="content"  rows="20" cols="">
				    </textarea>
				    <script type="text/javascript">CKEDITOR.replace('content');</script>
				  </div>
				  
				  <button type="submit" class="btn btn-success">发&nbsp;&nbsp;&nbsp;&nbsp;帖</button>
				</form>
				</div>
			</div>
			
		</div>
		
  
  
	</div>
</div>




<!-- 弹出框 模态框关于 -->
<div class="modal fade" id="about" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">关于</h4>
      </div>
      <div class="modal-body">
      	<p>
      		自由开发者crossoverJie
      	</p>
   		<address>
   			<a href="mailto:731756881@qq.com">731756881@qq.com</a>
   		</address>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
	<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	<script src="<%=path%>/js/createTopic.js" type="text/javascript"></script>
	
</body>
</html>
