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
<title>${frontuser.username }</title>
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
<script src="<%=path%>/ckeditor_full/ckeditor.js" type="text/javascript"></script>
    <style type="text/css">
    	#baseSetTable tr .td-head{
    		width: 100px;
    	}
    	.col-md-12 img{
    	    height: 200px;
    		width: 200px;
    	}
    	
    	/* 给初始化文章内容的时候为所有照片设置尺寸 */
    	#topic-content img{
    		width: 845px;
    	}
    	.review-panel{
    		height: 330px;
    	}
    	.hint-p{
    		padding-top: 90px;
    	}
    	#head-topic{
    		width: 75px;
    		height: 75px;
    	}
    	
    	.postlist{
    		height: 20px;
    	}
    </style>
</head>
<body class="">
<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>

	<div class="row">
		<div class="col-md-12">
			
			<div class="col-md-2">
				<img alt="${frontuser.username }" class="img-rounded" src="<%=path %>/${frontuserheading }">
			</div>
			
			<div class="col-md-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3>
						<p class="text-danger">
						${frontuser.username }
						<span class="glyphicon glyphicon-option-vertical"></span>
						${frontuser.realname }
						</p>
						</h3>
					</div>
					<div class="panel-body">
						<p>简介：${frontuser.remark}</p>
					</div>
				</div>
				
				<ul class="nav nav-pills" role="tablist" id="tab-list">
			    <li role="presentation" class="active"><a href="#postlist" aria-controls="home" role="tab" data-toggle="tab">发帖<span class="badge">4</span></a></li>
			    <li role="presentation"><a href="#replylist" aria-controls="profile" role="tab" data-toggle="tab">回复</a></li>
			    <li role="presentation"><a href="#collectlist" aria-controls="profile" role="tab" data-toggle="tab">收藏</a></li>
			  	</ul>
			  	
			  	
			  	<div class="tab-content">
		    	<div role="tabpanel" class="tab-pane active" id="postlist">
		    		<div class="panel panel-primary ">
		    			<div class="panel panel-heading">
		    				<p>2月14日 18:52 发帖 那位大神能告诉我为什么zealer app用尽了以...</p>
		    			</div>
		    			<div class="panel panel-body">
		    				<p>哪位大神知道？怎么破？</p>
		    			</div>
		    			
		    			<div class="panel panel-heading">
		    				<p>2月14日 18:52 发帖 那位大神能告诉我为什么zealer app用尽了以...</p>
		    			</div>
		    			<div class="panel panel-body">
		    				<p>哪位大神知道？怎么破？</p>
		    			</div>
					</div>
		    	</div>
		    
		    
				<div role="tabpanel" class="tab-pane" id="replylist">
					<div class="panle panel-info">
					</div>
				</div>
			
			<div role="tabpanel" class="tab-pane" id="collectlist">
				<div class="panle panel-info">
				</div>
			</div>
			
			
  		</div>
				
			</div>
			
		</div>
	</div>
	






<!-- 引入底部jsp -->
<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>

<!-- 弹出框 模态框关于 -->
<!-- 弹出框 模态框登录 -->
<jsp:include page="../../../../public/login&register.jsp"></jsp:include>
<!-- 弹出框 模态框注册 -->





	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
	<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	<script src="<%=path%>/js/topDetail.js" type="text/javascript"></script>
</body>
</html>
