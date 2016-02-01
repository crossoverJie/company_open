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
<title>${topic.title }</title>
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
    <style type="text/css">
    	#baseSetTable tr .td-head{
    		width: 100px;
    	}
    	.col-md-12 img{
    	    height: 150px;
    		width: 150px;
    	}
    	
    	/* 给初始化文章内容的时候为所有照片设置尺寸 */
    	#topic-content img{
    		width: 845px;
    	}
    </style>
</head>
<body class="">
<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>

	
	
	<hr/>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<img src="
				<c:choose>
					<c:when test="${empty topicuser}">
						<%=path %>/include/img/person.gif
					</c:when>
					<c:otherwise>
						<%=path %>/${topicuser}
					</c:otherwise>
				</c:choose>
				" class="img-responsive center-block img-circle" alt="Responsive image">
				<div class="center-block">
					<p class="text-center text-info"><b>${author.username}</b></p>
				</div>
				
			</div>
		</div>
	</div>
	<hr/>
<!-- 栅格系统 -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<div class="jumbotron">
			  <h3>${topic.title }</h3>
			</div>
			
			<div class="panel panel-default">
			  <div class="panel-body " id="topic-content" >
			  	${topic.content }
			  </div>
			</div>
			
			
		</div>
		
		<div class="col-md-3 col-md-offset-1">
			<div class="panel panel-primary">
			  <div class="panel-heading">最新动态</div>
			  <div class="panel-body">
			    <ul>
			    
			    	<c:forEach var="ns" items="${allauthor }" varStatus="status" >
			    		<li>
			    			<a href="<%=path%>/topic/${ns.id}">${ns.title }</a>
			    		</li>
			    		<br/>
			    	</c:forEach>
			    </ul>
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

<!-- 引入底部jsp -->
<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>

<!-- 弹出框 模态框登录 -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_login">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel_login">登录</h4>
      </div>
      <div class="modal-body">
			<form id="loginForm" method="post" >
			  <div class="form-group">
			    <label for="exampleInputEmail1">用户名</label>
			    <input type="email" name="text" required="required" class="form-control" id="login-name" placeholder="用户名">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">密码</label>
			    <input type="password" name="password" required="required" class="form-control" id="login-password" placeholder="密码">
			  </div>
			  <div class="modal-footer">
				  <button type="button" onclick="login();" class="btn btn-primary">登录</button>
			      <button type="reset" class="btn btn-danger">重置</button>
		      </div>
			</form>
      </div>
    </div>
  </div>
</div>

<!-- 弹出框 模态框注册 -->
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_register">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel_register">注册</h4>
      </div>
      <div class="modal-body">
			<form method="post" id="registerUserForm" action="<%=path%>/index/register">
			  
			  <div class="form-group" id="div-username">
				<label for="register-username">用户名</label>
				<input type="text" name="username" class="form-control" id="register-username" required="required"
				min="1" maxlength="20" placeholder="用户名(1-20位中英文、数字。下划线)"/>
			  </div>
			  <div class="form-group" id="div-email">
			    <label for="register-email">邮箱</label>
			    <input type="email" name="email" required="required" class="form-control" id="register-email" placeholder="邮箱">
			  </div>
			  
			  <div class="form-group " id="div-password1">
			    <label for="exampleInputPassword1">密码</label>
			    <input type="password" required="required" class="form-control" id="password1" name="password"
			    min="6" maxlength="20" placeholder="密码(6-20位数字、英文)">
			  </div>
			  <div class="form-group" id="div-password2">
			    <label for="exampleInputPassword2">确认密码</label>
			    <input type="password" required="required" class="form-control" id="password2" placeholder="确认密码">\
							    
			  </div>
			  <div class="modal-footer">
				  <button type="submit" class="btn btn-primary">注册</button>
			      <button type="reset" class="btn btn-danger" >重置</button>
		      </div>
			</form>
      </div>
    </div>
  </div>
</div>




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
