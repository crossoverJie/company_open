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
    		height: 500px;
    		background-color: #000;
    	}
    	.carousel .item{
    		height: 500px;
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
					<p>谷歌chrome浏览器被称为“GoogleChrome”，是一款开源软件。与当前Web浏览器有很大不同，GoogleChrome强化了地址栏和其他功能，访问网页更加方便，速度也更快。</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img1}" alt="火狐">
				<div class="carousel-caption">
					<p>Mozilla Firefox，中文名通常称为“火狐”，是一个开源网页浏览器，使用Gecko引擎（即非ie内核），可以在多种操作系统如Windows，Mac和linux上运行。Firefox由Mozilla基金会与数百个志愿者所开发，原名“Phoenix”（凤凰），之后改名“Mozilla Firebird”（火鸟），再改为现在的名字。2012年8月，在世界范围内，Firefox占据着23%的使用份额。</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img2}" alt="IE">
				<div class="carousel-caption">
					<p>Internet Explorer，是美国微软公司推出的一款网页浏览器。原称Microsoft Internet Explorer(6版本以前)和Windows Internet Explorer(7、8、9、10、11版本)，简称IE。在IE7以前，中文直译为“网络探路者”，但在IE7以后官方便直接俗称"IE浏览器"。</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img3}" alt="欧朋">
				<div class="carousel-caption">
					<p>Opera浏览器，是一款挪威Opera Software ASA公司制作的支持多页面标签式浏览的网络浏览器，是跨平台浏览器可以在Windows、Mac和Linux三个操作系统平台上运行。Opera浏览器创始于1995年4月，到2015年8月6日，官方发布的个人电脑用的最新版本为Opera31。</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img4}" alt="Safari">
				<div class="carousel-caption">
					<p>Safari，是苹果计算机的最新操作系统Mac OS X中的浏览器，使用了KDE的KHTML作为浏览器的运算核心。</p>
				</div>
			</div>
			<div class="item">
				<img src="<%=path%>/${img5}" alt="Safari">
				<div class="carousel-caption">
					<p></p>
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
	<div class="row">
	  <div class="col-md-4">
	  	<img src="include/img/Chrome-small.jpg" class="img-circle" alt="谷歌">
	  	<h2>Chrome</h2>
	  	<p>谷歌chrome浏览器被称为“GoogleChrome”.</p>
	  </div>
	  
	  <div class="col-md-4">
	  	<img src="include/img/Firefox-small.jpg" class="img-circle" alt="">
	  	<h2>Firefox</h2>
	  	<p>Mozilla Firefox，中文名通常称为“火狐”.</p>
	  </div>
	  
	  <div class="col-md-4">
	  	<img src="include/img/Safari-small.jpg" class="img-circle" alt="">
	  	<h2>Safari</h2>
	  	<p>Safari，是苹果计算机的最新操作系统.</p>
	  </div>
	</div>
	<hr class="divider"/>
	
	<!-- 标签 -->
<div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist" id="tab-list">
    <li role="presentation" class="active"><a href="#chrome" aria-controls="home" role="tab" data-toggle="tab">Chrome</a></li>
    <li role="presentation"><a href="#firefox" aria-controls="profile" role="tab" data-toggle="tab">Firefox</a></li>
    <li role="presentation"><a href="#opera" aria-controls="messages" role="tab" data-toggle="tab">Opera</a></li>
    <li role="presentation"><a href="#safari" aria-controls="settings" role="tab" data-toggle="tab">Safari</a></li>
    <li role="presentation"><a href="#ie" aria-controls="settings" role="tab" data-toggle="tab">IE</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="chrome">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-7">
    				<p class="logo-msg">Google Chrome，又称Google浏览器，是一个由Google（谷歌）公司开发的网页浏览器。该浏览器是基于其他开源软件所撰写，包括WebKit，目标是提升稳定性、速度和安全性，并创造出简单且有效率的使用者界面。软件的名称是来自于称作Chrome的网络浏览器图形使用者界面（GUI）。软件的beta测试版本在2008年9月2日发布，提供50种语言版本，有Windows、Mac OS X、Linux、Android、以及iOS版本提供下载。</p>			
    			</div>
    			<div class="col-md-5">
    				<img src="include/img/Chrome.jpg" class="img-circle logo" alt="谷歌">
    			</div>
    		</div>
    	</div>
    	
    </div>
    <div role="tabpanel" class="tab-pane" id="firefox">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-7">
    				<img src="include/img/Firefox.jpg" class="img-circle logo" alt="谷歌">
    			</div>
    			<div class="col-md-5">
    				<p class="logo-msg">Mozilla Firefox，中文俗称“火狐”（正式缩写为Fx或fx，非正式缩写为FF），是一个自由及开放源代码网页浏览器，使用Gecko排版引擎，支持多种操作系统，如Windows、Mac OS X及GNU/Linux等。该浏览器提供了两种版本，普通版和ESR（Extended Support Release，延长支持）版，ESR版本是 Mozilla 专门为那些无法或不愿每隔六周就升级一次的企业打造。Firefox ESR 版的升级周期为 42 周，而普通 Firefox 的升级周期为 6 周。</p>			
    			</div>
    		</div>
    	</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="opera">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-7">
    				<p class="logo-msg">Opera浏览器，是一款挪威Opera Software ASA公司制作的支持多页面标签式浏览的网络浏览器，是跨平台浏览器可以在Windows、Mac和Linux三个操作系统平台上运行。Opera浏览器创始于1995年4月，到2015年8月6日，官方发布的个人电脑用的最新版本为Opera31</p>			
    			</div>
    			<div class="col-md-5">
    				<img src="include/img/Opera.jpg" class="img-circle logo" alt="">
    			</div>
    		</div>
    	</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="safari">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-7">
    				<img src="include/img/Safari.jpg" class="img-circle logo" alt="谷歌">
    			</div>
    			<div class="col-md-5">
    				<p class="logo-msg">Safari,苹果计算机的最新操作系统Mac OS X中新的浏览器，用来取代之前的Internet ExplorerforMac。Safari使用了KDE的KHTML作为浏览器的计算核心。 该浏览器已支持Windows平台，但是与运行在Mac OS X上的safari相比，有些功能出现丢失。Safari也是iPhone手机、iPodTouch、iPad平板电脑中iOS指定默认浏览器。</p>			
    			</div>
    		</div>
    	</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="ie">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-7">
    				<p class="logo-msg">2015年3月微软确认将放弃IE品牌。转而在windows 10上，Microsoft Edge取代了。微软于2015年10月宣布2016年1月起停止支持老版本IE浏览器[1]</p>			
    			</div>
    			<div class="col-md-5">
    				<img src="include/img/IE.jpg" class="img-circle logo" alt="">
    			</div>
    		</div>
    	</div>
    </div>
  </div>

</div>
	
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

<input type="hidden" id="msg" value="${msg}"/>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/index.js" type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
