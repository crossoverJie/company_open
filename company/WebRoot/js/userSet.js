$(function(){
	
	var sex = $("#hiddenSex").val() ;
	if(sex != "" && sex != undefined){
		if(sex=="男" || sex=="1"){
			$("#man").attr("checked","checked");
		}else{
			$("#woman").attr("checked","checked");
		}
	}
	
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
	
	/**
	 * 上传或者是修改头像
	 */
	$("#addImgForm").submit(function(){
		var file = $("#file").val() ;
		var index = file.lastIndexOf(".");
		file = file.substring(index + 1);
		file = file.toLowerCase() ;
		if( file == "jpg" || file == "gif" || file == "png" ){
			return true;
		}else{
			alert("只能上传图片") ;
			return false ;
		}
	});
	
	
});

/**
 * 修改基本设置
 */
function saveBaseSet(){
	var id = $("#hiddenId").val() ;//当前用户的ID
	var sex =$('input:radio[name=sex]:checked').val();
	if(sex==""){
		sex=undefined ;
	}
	var remark = $("#remark").val() ;
	var json={
		"id":id,
		"sex":sex,
		"remark":remark
	};
	$.ajax( {
		type:"POST", 
		url : '../../index/updateBaseUserInfo',
		cache : false,
		data :json,
		success : function(r) {
			window.location.href="../../user/frontUserSet/"+id ;
		}
	});
}

/**
 * 修改密码
 */
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
		url : '../../index/updateUserInfo?flag=0',
		cache : false,
		async:false,
		data :json,
		success : function(r) {
			window.location.href="../../index/turnToIndex/1" ;
		}
	});
}