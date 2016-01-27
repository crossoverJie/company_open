$(function(){
	$("#currentPwd").blur(function(){
		var currenPwd=$("#currentPwd").val() ;
		var id = $("#hiddenId").val() ;//当前用户的ID
		var json ={
				"id" : id,
				"password" :currenPwd
			};
		if(currenPwd != undefined && currenPwd != ""){
			
			$.ajax( {
				type:"POST", 
				url : '../../index/checkCurrentPwd',
				data :json,
				cache : false,
				async:false,
				success : function(r) {
					if(r=="true"){
						$("#div-currentpPwd").addClass("has-success has-feedback") ;
						$("#currentPwd").after("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span>") ;
						$("#div-currentpPwd").removeClass("has-error") ;
						$("#div-currentpPwd span").remove(".glyphicon-remove");
					}else{
						$("#div-currentpPwd").addClass("has-error has-feedback") ;
						$("#currentPwd").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
						$("#div-currentpPwd").removeClass("has-success") ;
						$("#div-currentpPwd span").remove(".glyphicon-ok");
					}
				}
			});
			
		}
	});
});

function savePassWord(){
	var currenPwd=$("#currentPwd").val() ;
	var pwd = $("#newPwd").val() ;
	var pwd2 =$("#newPwd2").val() ;
	var id = $("#hiddenId").val() ;//当前用户的ID
	if(currenPwd=="" || pwd =="" || pwd2 ==""){
		alert("请将信息填写完整") ;
		return ;
	}
	if(pwd != pwd2){
		alert("两次密码输入不一致") ;
		return ;
	}
	var jsonPwd ={
		"id" : id,
		"password" :currenPwd
	}
	var pwd_class = $("#div-currentpPwd span").attr("class") ;
	if(pwd_class.indexOf("glyphicon-remove") >0){
		return ;
	}

	
	var json = {
		"id":id,
		"password":pwd
	};
	$.ajax( {
		type:"POST", 
		url : '../../index/updatePwd',
		cache : false,
		async:false,
		data :json,
		success : function(r) {
			window.location.href="../../index/turnToIndex/1" ;
		}
	});
}