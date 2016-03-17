<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link href="<%=path%>/include/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css" rel="stylesheet" />
<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js" type="text/javascript"></script>
<title>欢迎进入登录</title>
</head>

<body>
	<div id="loginWin" class="easyui-window" title="登录"
		style="width:350px;height:188px;padding:5px;" minimizable="false"
		maximizable="false" resizable="false" collapsible="false" closable="false" draggable="false">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding:5px;background:#fff;border:1px solid #ccc;">
				<form id="loginForm" method="post">
					<div style="padding:5px 0;">
						<label for="login">帐号:</label> 
						<input id="name" type="text" name="username"
							style="width:260px;"></input>
					</div>
					<div style="padding:5px 0;">
						<label for="password">密码:</label> <input type="password" id="password"
							name="password" style="width:260px;"></input>
					</div>
					<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align:right;padding:5px 0;">
				<script language="javascript" type="text/javascript" src="http://js.users.51.la/18800358.js"></script>
		<noscript><a href="http://www.51.la/?18800358" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/18800358.asp" style="border:none" /></a></noscript>
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)" onclick="login()">登录</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cleardata()">重置</a>
					
					
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			login();
		}
	}
	function cleardata(){
		$("#loginForm").form("clear") ;
	}
	function login(){
		$.messager.progress({title:'进入',msg:'进入系统',text:'进入系统中。。'});
		if($("#name").val()=="" || $("#password").val()==""){
         $("#showMsg").html("用户名或密码为空，请输入");
         $("#name").focus();
    }else{
            //ajax异步提交  
           $.ajax({            
                  type:"POST",   //post提交方式默认是get
                  url:"login/check", 
                  data:$("#loginForm").serialize(),   //序列化               
                  error:function(request) {      // 设置表单提交出错                 
                      $("#showMsg").html(request);  //登录错误提示信息
                  },
                  success:function(data) {
                  	  $.messager.progress('close');
                  	  if(data=="false"){
                  	  	  $("#showMsg").html("用户名或密码错误");  
                  	  }else{
	                      document.location = "user/turnToIndex";
	                      /*     $.ajax({            
						        type:"POST",   //post提交方式默认是get
						        url:"user/turnToIndex",
						        async : false 
						  }); */
                  	  }
                  }            
            });       
        } 
	}
</script>

</html>
