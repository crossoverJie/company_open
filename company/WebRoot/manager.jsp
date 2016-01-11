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
<title>某管理系统</title>
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
	    }
    	.nav-sidebar{
    		margin-right: -21px;
    		margin-left: -20px;
    		margin-bottom: 20px;
    	}
    	/* 让首页两字移动上去不变色 点击不变色 */
    	.nav-sidebar > .active > a , .nav-sidebar > .active > a:HOVER,.nav-sidebar > .active > a:FOCUS{
    		color: #fff;
    		background-color: #428bca;;
    	}
    	.sidebar{
    		position: fixed;
    		top: 50px;
    		bottom: 0;
    		left: 0;
    		z-index: 1000;
    		display: block;
    		padding: 20px;
    		overflow-x:hidden;
    		overflow-y:auto;
    		background: #ddd;
    		border-right: 1px solid #eee;
    	}
    	hr .divider{
    		margin: 40px 0;
    	}
    </style>
</head>
<body>

	<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#demo-navbar"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">某管理系统 </a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="demo-navbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页<span class="sr-only">(current)</span>
					</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">功能<span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a href="#chrome" >Chrome</a></li>
							<li><a href="#firefox" >Firefox</a></li>
							<li><a href="#opera">Opera</a></li>
							<li><a href="#safari">Safari</a></li>
							<li><a href="#ie">IE</a></li>
						</ul>
					</li>
					<li><a href="#" data-toggle="modal" data-target="#about">帮助</a></li>
				</ul>
				<form class="navbar-form navbar-right " role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="用户名">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="密码">
					</div>
					<button type="submit" class="btn btn-default">登录</button>
				</form>
			</div>
		</div>
	</nav>

	<!-- 栅格系统 分为3:9 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 sidebar" >
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">首页</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="#">信息建立</a></li>
					<li><a href="#">信息查询</a></li>
					<li><a href="#">信息管理</a></li>
				</ul>
				
				<ul class="nav nav-sidebar">
					<li><a href="#">设置</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				
			</div>
			<div class="col-md-10 col-md-offset-2">
				<h1>管理控制平台</h1>
				<hr class="divider"/>
				<p>
				<button type="button" class="btn btn-default">（默认样式）Default</button>

				<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
				<button type="button" class="btn btn-primary">（首选项）Primary</button>
				
				<!-- Indicates a successful or positive action -->
				<button type="button" class="btn btn-success">（成功）Success</button>
				
				<!-- Contextual button for informational alert messages -->
				<button type="button" class="btn btn-info">（一般信息）Info</button>
				
				<!-- Indicates caution should be taken with this action -->
				<button type="button" class="btn btn-warning">（警告）Warning</button>
				
				<!-- Indicates a dangerous or potentially negative action -->
				<button type="button" class="btn btn-danger">（危险）Danger</button>
				</p>
				
				<!-- 面板 -->
				<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
					  <div class="panel-heading">最新提醒</div>
					  <div class="panel-body">
					  	<div class="alert alert-success" role="alert">提示您的订单（2014001）已经审批通过！</div>
					  	<div class="alert alert-danger" role="alert">提示您的订单（2014002）被打回！ </div>
					  	<div class="alert alert-warning" role="alert">提示您的订单（2013001）客户付款延迟！</div>
					  </div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">我的任务</h3>
					  </div>
					  <div class="panel-body">
					  	<ul class="nav nav-pills nav-stacked">
						  <li role="presentation"><a href="#" class="alert alert-info" >订单审批<span class="badge pull-right">42</span></a></li>
						  <li role="presentation"><a href="#" class="alert alert-info">收款确认<span class="badge pull-right">20</span></a></li>
						  <li role="presentation"><a href="#" class="alert alert-info">付款确认<span class="badge pull-right">10</span></a></li>
						</ul>
					  </div>
					</div>
				</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
					  <div class="panel-heading">最新订单</div>
					  <div class="table">
					  <div class="panel-body">
					  	<table class="table table-striped">
					  		<thead>
					  			<tr>
					  				<th>#</th>
					  				<th>产品</th>
					  				<th>数量</th>
					  				<th>总金额</th>
					  				<th>业务员</th>
					  			</tr>
					  		</thead>
					  		<tbody>
					  			<tr>
					  				<td>1</td>
				                    <td>Apple Macbook air</td>
				                    <td>10</td>
				                    <td>80000</td>
				                    <td>王小贱</td>
					  			</tr>
					  			<tr>
				                    <td>2</td>
				                    <td>Apple iPad air</td>
				                    <td>20</td>
				                    <td>65000</td>
				                    <td>尹开花</td>
				                  </tr>
			                  <tr>
			                    <td>3</td>
			                    <td>Apple Macbook pro</td>
			                    <td>5</td>
			                    <td>50000</td>
			                    <td>刘老根</td>
			                   </tr>
					  		</tbody>
					  	</table>
						  	<p><a class="btn btn-primary" href="#" role="button">查看详细&raquo;</a></p>
					  	</div>
					  </div>
					</div>
					</div>
					
					<div class="col-md-6">
						<div class="panel panel-default">
					  <div class="panel-heading">工程进度</div>
					  <div class="panel-body">
					  	<h3><span class="label label-primary">水井挖掘工程</span></h3>
					  	<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						    60%
						  </div>
						</div>
						
						<h3><span class="label label-danger">基建工程</span></h3>
					  	<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
						    70%
						  </div>
						</div>
					  </div>
					</div>
					</div>
				</div>
			
		</div>
	</div>
	




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#demo-navbar .dropdown-menu a").click(function(){
				var href = $(this).attr("href") ;
				$("#tab-list a[href='"+href+"']").tab("show") ;
			});
		}) ;
	</script>
</body>
</html>
