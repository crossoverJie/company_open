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
<script src="<%=path%>/ckeditor_full/ckeditor.js" type="text/javascript"></script>
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
			
			<hr/>
			<!-- 显示回复 -->
			<h3>
				<span class="label label-primary">
					全部回复
				</span>
			</h3>
			<div class="panel panel-sucess">
			
				<c:forEach var="com" items="${comments }" varStatus="status" >
				
				
				<div class="panel-heading">
					<div class="row">
							<div class="col-md-10">
								<div class="row">
									<div class="col-md-2">
										<img id="sm-name" src="
											<%=path %>/${com.user_head_img}
									" class="img-responsive center-block img-circle" alt="图片无效">
									<p class="text-center">
									${com.username }
									</p>
									</div>
									<div class="col-md-10">
										<p>
											${com.content }
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<!-- text-muted：字体颜色的样式 -->
								<p class="text-muted text-right">${com.dateStr}
								</p>
							</div>
						</div>
					
				</div>
				</c:forEach>
				
				
			</div>
			
			<hr/>
			<div class="panel panel-info review-panel">
				
				<c:choose>
				<c:when test="${empty user}">
					<p class="text-center text-danger hint-p">
						您需要登录之后才能回复&nbsp;
						<a href="javascriot:void(0)" data-toggle="modal" data-target="#login">登录
									<span class="glyphicon glyphicon-home"></span></a>
						&nbsp;&nbsp;
						<a href="javascriot:void(0)" data-toggle="modal" data-target="#register">注册
									<span class="glyphicon glyphicon-user"></span></a>
					</p>
						
				</c:when>
				
				<c:otherwise>
				<div class="row">
					<div class="col-md-2">
						<img id="head-topic" src="
									<c:choose>
										<c:when test="${empty headimg}">
											<%=path %>/include/img/person.gif
										</c:when>
										<c:otherwise>
											<%=path %>/${headimg}
										</c:otherwise>
									</c:choose>
									" class="img-responsive center-block img-circle" alt="图片无效">
					</div>
					
					
					<div class="col-md-10">
						<div class="form-group">
						    <textarea required="required" class="form-control" id="content" name="content" >
						    </textarea>
						    <input type="hidden" id="topic_id" value="${topic.id }"/>
						    <script type="text/javascript">CKEDITOR.replace('content');</script>
						 </div>
					</div>
					
				</div>
				<p class="text-right">
					<a class="btn btn-primary" onclick="comment();" href="javascript:void(0)" >提交</a>
				</p>
				</c:otherwise>
				</c:choose>
				
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
