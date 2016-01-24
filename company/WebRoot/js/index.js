$(function(){
	$("#demo-navbar .dropdown-menu a").click(function(){
		var href = $(this).attr("href") ;
		$("#tab-list a[href='"+href+"']").tab("show") ;
	});
	
	/*检查邮箱是否被注册*/
	$("#register-email").blur(function(){
		var email = $("#register-email").val() ;
		if(email != undefined){
			$.ajax( {
				type:"POST", 
				url : '../../index/checkIsRepeat?email='+email,
				cache : false,
				success : function(r) {
					if(r=="true"){
						$("#div-email").addClass("has-success has-feedback") ;
						$("#register-email").after("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span>") ;
						/*如果第一次重复了 换了一个账号之后成功了 要把第一次加的class和span取消掉*/
						$("#div-email").removeClass("has-error") ;
						$("#div-email span").remove(".glyphicon-remove");
					}else{
						$("#div-email").addClass("has-error has-feedback") ;
						$("#register-email").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
						$("#div-email").removeClass("has-success") ;
						$("#div-email span").remove(".glyphicon-ok");
					}
				}
			});
		}
	});
	
	/*检查用户名是否被注册*/
	$("#register-username").blur(function(){
		var username = $("#register-username").val() ;
		if(username != undefined){
			$.ajax( {
				type:"POST", 
				url : '../../index/checkIsRepeat?username='+username,
				cache : false,
				success : function(r) {
					if(r=="true"){
						$("#div-username").addClass("has-success has-feedback") ;
						$("#register-username").after("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span>") ;
						/*如果第一次重复了 换了一个账号之后成功了 要把第一次加的class和span取消掉*/
						$("#div-username").removeClass("has-error") ;
						$("#div-username span").remove(".glyphicon-remove");
					}else{
						$("#div-username").addClass("has-error has-feedback") ;
						$("#register-username").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
						$("#div-username").removeClass("has-success") ;
						$("#div-username span").remove(".glyphicon-ok");
					}
				}
			});
		}
	});
	
	$("#registerUserForm").submit(function(){
		var pwd1 = $("#password1").val() ;
		var pwd2 = $("#password2").val() ;
		if(pwd1 != pwd2){
			alert("两次输入密码不同") ;
			$("#div-password1").addClass("has-error has-feedback") ;
			$("#div-password2").addClass("has-error has-feedback") ;
			$("#password1").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
			$("#password2").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
			return false ;
		}
	});
	
	$("#loginForm").submit(function(){
        $.ajax({
			type:"POST", 
			url : '../../index/login',
			data : $(this).serialize(),
			success : function(r) {
				alert("00000");
			}
		});
	});
	
});

function login(){
	var username = $("#login-name").val() ;
	var password = $("#login-password").val() ;
	if(username =="" || password == ""){
		alert("请将信息填写完整") ;
		return ;
	}
	var json ={
		"username":username,
		"password":password
	}
	
    $.ajax({
		type:"POST", 
		url : '../../index/login',
		data :json,
		success : function(r) {
			if(r=="true"){
				window.location.href="../../index/turnToIndex/1" ;
			}else{
				alert("用户名或密码错误！");
			}
		}
	});
}