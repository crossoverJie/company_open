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
    	#summary-container .col-md-4{
    		text-align: center;
    	}
    	hr .divider{
    		margin: 40px 0;
    	}
    	.logo{
    		height: 500px;
    		width: 500px
    	}
    	
    	.logo-msg{
    		padding: 40px 0;
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
					<li class="active"><a href="#">science <span class="glyphicon glyphicon-earphone" aria-hidden="true"></span> 
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
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
				
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			<li data-target="#carousel-example-generic" data-slide-to="4"></li>
			<li data-target="#carousel-example-generic" data-slide-to="5"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="<%=path%>/${img0}" alt="谷歌">
				<div class="carousel-caption">
					<p>${msg0 }</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img1}" alt="火狐">
				<div class="carousel-caption">
					<p>${msg1 }</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img2}" alt="IE">
				<div class="carousel-caption">
					<p>${msg2 }</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img3}" alt="欧朋">
				<div class="carousel-caption">
					<p>${msg3 }</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img4}" alt="Safari">
				<div class="carousel-caption">
					<p>${msg4 }</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img5}" alt="Safari">
				<div class="carousel-caption">
					<p>${msg5}</p>
				</div>
			</div>
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

	<!-- 分割线 -->
	<hr class="divider"/>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">${news_title0 }</div>
					<div class="panel-body">
					   ${news_content0 }
					</div>
				<div class="panel-footer">
					<p class="text-right">
						<button type="button" class="btn btn-default btn-xs" disabled="disabled">
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
							2
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						12
						
					</p>
					
				</div>
			</div>
			
			
		</div>
	</div>




	<!-- 分割线 -->
	<hr class="divider"/>
	<footer>
		<p class="pull-right" ><a href="#top">回到顶部</a></p>
		<p>© 2016 crossoverJie</p>
	</footer>
	

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

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/index.js" type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
