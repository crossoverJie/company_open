<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
			    <input type="password" required="required" class="form-control" id="password2" placeholder="确认密码">
							    
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