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
<title>查询结果</title>
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
	
<!-- 栅格系统 -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			
			<div class="row">
				<div class="col-md-12">
					<form class="" action="<%=path%>/index/query" method="post" role="search">
							<div class="form-group">
								<input type="text" value="${keyword}" name="title" class="form-control"  placeholder="Search">
							</div>
					<p class="text-right"><input type="submit"  class="btn btn-primary" value="查询"/></p>
					</form>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<blockquote>
						<p class="text-primary">找到"${keyword }"内容${querycount }个</p>
					</blockquote>
				</div>
			
			</div>
			
			<div class="row">
				<div class="col-md-12">
				<div class="panel panel-info">
					<c:forEach var="ns" items="${querylist }" varStatus="status" >
						
						<div class="panel-heading ">
							
							<div class="row">
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-2">
										<img id="sm-name" src="
									<c:choose>
										<c:when test="${empty ns.user_head_img}">
											<%=path %>/include/img/person.gif
										</c:when>
										<c:otherwise>
											<%=path %>/${ns.user_head_img}
										</c:otherwise>
									</c:choose>
									" class="img-responsive center-block img-circle" alt="图片无效">
									</div>
									<div class="col-md-10">
										<b>
										<a href="<%=path %>/topic/${ns.id}">${ns.title }</a>
										</b>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-md-offset-2">
								<!-- text-muted：字体颜色的样式 -->
								<p class="text-muted text-right">${ns.dateStr }
								</p>
							</div>
						</div>
						</div>
						
					</c:forEach>
					
				</div>
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
