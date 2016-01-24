$(function(){
	$("#demo-navbar .dropdown-menu a").click(function(){
		var href = $(this).attr("href") ;
		$("#tab-list a[href='"+href+"']").tab("show") ;
	});
	
	$("#register-email").blur(function(){
		var email = $("#register-email").val() ;
		if(email != undefined){
			$.ajax( {
				type:"POST", 
				url : '../../index/checkEmail?email='+email,
				cache : false,
				success : function(r) {
					if(r=="true"){
						
					}else{
						alert("邮箱不可注册");
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
			return false ;
		}
	});
	
});