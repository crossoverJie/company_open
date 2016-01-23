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
<title>欢迎来到咨询网</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
       
    	body{
    		padding-top: 50px;
    		padding-bottom: 50px;
    	}
    	.carousel{
    		height: 460px;
    		background-color: #000;
    	}
    	.carousel .item{
    		height: 460px;
    		background-color: #000;
    	}
		.carousel img{
    		width: 100%;
    		height: 100%;
    	}
    	.carousel-caption p{
    		margin-bottom: 20px;
    		font-size: 20px;
    		/* 行间距 */
    		line-height: 1.8;
	    		
    	}
    	hr .divider{
    		margin: 40px 0;
    	}
    	.hr-right{
    		margin: 80px 0 40px 80px;
    	}
    	.logo{
    		height: 500px;
    		width: 500px
    	}
    	
    	.logo-msg{
    		padding: 40px 0;
    	}
    	/**改变脚注的高度**/
    	.panel-footer{
    		height: 35px;
    	}
    	.panel-body p{
	   		 overflow:hidden;
	   		 text-overflow:ellipsis;
	   		 -o-text-overflow:ellipsis;
	   		 white-space:nowrap;
	   		 width:100%;
    	}
    	/* 选择thumbnail类内部的所有img元素 */
    	.thumbnail img{
    		
    	}
    	/* 为热门主题面板加上470px的高度 */
    	.panel-primary , .panel-danger{
    		height: 470px;
    	}
    </style>
</head>
<body>

	<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#demo-navbar"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">咨询网 </a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="demo-navbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">science <span class="glyphicon glyphicon-phone" aria-hidden="true"></span> 
					<span class="sr-only">(current)</span>
					</a></li>
					<li><a href="#">basketball <span class="glyphicon glyphicon-adjust" aria-hidden="true"></span> </a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">特点 <span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a href="#chrome" >Chrome</a></li>
							<li><a href="#firefox" >Firefox</a></li>
							<li><a href="#opera">Opera</a></li>
							<li><a href="#safari">Safari</a></li>
							<li><a href="#ie">IE</a></li>
						</ul>
					</li>
					<li><a href="#" data-toggle="modal" data-target="#about">关于</a></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">查询</button>
				</form>
			</div>
		</div>
	</nav>

	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<c:forEach var="img" items="${imgs }" varStatus="status" >
				<li data-target="#carousel-example-generic" data-slide-to="${status.index }"
				class="
					<c:if test="${status.index == 0}">
					active
					</c:if>
				"
				></li>
			</c:forEach>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<c:forEach var="img" items="${imgs }" varStatus="status" >
				<div class="item
					<c:if test="${status.index == 0}">
					active
					</c:if> 
				">
					<img src="<%=path%>/${img.path}" alt="Safari">
					<div class="carousel-caption">
						<p>${img.remark}</p>
					</div>
				</div>
				
			</c:forEach>
			
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">上一页</span> </a> <a class="right carousel-control"
			href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一页</span> </a>
	</div>
	
	
<!-- 栅格系统 平均分三列 -->
<div class="container" id="summary-container">

	<!-- 分割线暂时注释掉
	<hr class="divider"/> -->
	<div class="row">
		<div class="col-md-8">
			<div class="panel panel-info">
				<c:forEach var="ns" items="${news }" varStatus="status" >
					
					<div class="panel-heading ">
					
						<!-- 利用一个栅格系统向右偏移完成布局 -->
						<div class="row">
							<div class="col-md-6">
								<b>${ns.title }</b>
							</div>
							<div class="col-md-4 col-md-offset-2">
								<!-- text-muted：字体颜色的样式 -->
								<p class="text-muted text-right">${ns.dateStr }
								</p>
							</div>
						</div>
						
						
					</div>
						<div class="panel-body">
							<a href="#" class="thumbnail">
      							<img src="<%=path%>/upload/Stephen curry.jpg" alt="">
    						</a>
						   ${ns.content }...
						</div>
					<div class="panel-footer">
						<p class="text-right">
							<button type="button" class="btn btn-default btn-xs" >
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
								2
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
							12
							
						</p>
					</div>
				<hr class="divider"/>
					
				</c:forEach>
				
				
			</div>
			
			
		</div>
		<div class="col-md-4">
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					热门帖子
				</div>
			  	<div class="panel-body">
			    Basic panel example
			  	</div>
			</div>
			
			<hr class="hr-right"/>
			
			<div class="panel panel-danger">
				<div class="panel-heading">
					活跃用户
				</div>
			  	<div class="panel-body">
			    Basic panel example
			  	</div>
			</div>
			
			<hr class="hr-right"/>
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					活跃用户
				</div>
			  	<div class="panel-body">
			    Basic panel example
			  	</div>
			</div>
			
		</div>
	</div>


<!-- 分页 -->
<nav class="text-right">
  <ul class="pagination pagination-lg">
    <li class="
    	<c:if test="${currentpage == 1}">
			disabled
		</c:if>
    ">
      <a  href="<%=path%>/index/turnToIndex/${currentpage -1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="
    	<c:if test="${currentpage == 1}">
			active
		</c:if>
    "><a href="<%=path%>/index/turnToIndex/1">1</a></li>
    
    <li class="
    	<c:if test="${currentpage == 2}">
			active
		</c:if>
    "><a href="<%=path%>/index/turnToIndex/2">2</a></li>
    
    <li class="
    	<c:if test="${currentpage == 3}">
			active
		</c:if>
    "><a href="<%=path%>/index/turnToIndex/3">3</a></li>
    
    <li class="
    	<c:if test="${currentpage == 4}">
			active
		</c:if>
    "><a href="<%=path%>/index/turnToIndex/4">4</a></li>
    
    <li class="
    	<c:if test="${currentpage == 5}">
			active
		</c:if>
    "><a href="<%=path%>/index/turnToIndex/5">5</a></li>
    
    <li><a href="#">.....</a></li>
    
    <li>
      <a href="<%=path%>/index/turnToIndex/${currentpage +1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>


	<!-- 分割线 -->
	<hr class="divider"/>
	
	

</div>


<!-- 弹出框 模态框 -->
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

<nav class="navbar navbar-default navbar-right navbar-fixed-bottom">
  <div class="container">
  	<footer>
		<p class="pull-right" ><a href="#top" class="btn btn-default">
			<span class="glyphicon glyphicon-circle-arrow-up"></span>
		</a></p>
		<p>© 2016 crossoverJie</p>
	</footer>
  </div>
</nav>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/index.js" type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
