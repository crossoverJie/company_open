$(function(){
	
	//将内容中所有的图片的尺寸统一设置。
	$("#topic-content img").removeAttr("style") ;
	
	/*检查邮箱是否被注册*/
	$("#register-email").blur(function(){
		var email = $("#register-email").val() ;
		if(email != undefined && email != ""){
			$.ajax( {
				type:"POST", 
				url : '../index/checkIsRepeat?email='+email,
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
		var json ={
			"username":username
		}
		if(username != undefined && username != ""){
			$.ajax( {
				type:"POST", 
				data:json,
				url : '../index/checkIsRepeat',
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
//		判断目前是否重复的用户名或者是邮箱 有的话就不让提交
		var email_class = $("#div-email span").attr("class") ;
		var username_class =$("#div-username span").attr("class") ;
		if(email_class.indexOf("glyphicon-remove") >0 || username_class.indexOf("glyphicon-remove") >0  ){
			return false ;
		}
		
	});
	
	
});

/**
 * 评论的翻页  0：上一页    1：下一页
 * @param obj
 */
function paginationComment(obj){
	if(obj==0){
		
	}else{
		$.ajax( {
			type:"POST", 
			url : "../comment/create",
			data : json,
			success : function(r) {
				
			}
		});
	}
}

/**
 * 提交评论
 */
function comment(){
	var content = CKEDITOR.instances.content.getData();
	var news_id = $("#topic_id").val() ;
	if(content == "" || content == undefined){
		alert("请填写评论");
		return ;
	}
	var json ={
		"content":content,
		"news_id":news_id
	}
	
	$.ajax( {
		type:"POST", 
		url : "../comment/create",
		data : json,
		success : function(r) {
			var url = window.location.href ;
			window.location.href= url ;//然后再刷新当前界面
		}
	});
}

/**
 * 回复评论
 */
function replyComment(){
	var content = CKEDITOR.instances.reply_content.getData();
	if(content == "" || content == undefined){
		alert("请填写评论");
		return ;
	}
	var comment_id=$("#reply_comment_id").val();//获取回复帖子的ID
	var news_id = $("#topic_id").val() ;
	var json ={
		"content":content,
		"parent_id":comment_id,
		"news_id":news_id
	};
	$.ajax( {
		type:"POST", 
		url : "../comment/createParent",
		data : json,
		success : function(r) {
			var url = window.location.href ;
			window.location.href= url ;//然后再刷新当前界面
		}
	});
	
}

/**
 * 打开回复框
 * 判断是否登录。
 * @param obj
 */
function openReply(obj){
	var username = $("#session_username").val() ;
	if(username == ""){
		alert("请登录之后再发表回复") ;
		$('#login').modal('show') ;
		return ;
	}
	
	
	//获取回复的人的名称
	var username=$(obj).parent().parent().prev().children().children(".col-md-10")
	.children().children(".col-md-2").html();
	
	var content=$(obj).parent().parent().prev().children().children(".col-md-10")
	.children().children(".col-md-10").html();
	
	$("#reply_username").html("回复"+username) ;
	$("#reply_username_content").html(content) ;
	var comment_id=$(obj).parent().parent().prev().children().children(".comment_id").val();
	$("#reply_comment_id").val(comment_id) ;
	$("#reply").modal("show") ;
}

/**
 * 登录的方法
 */
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
		url : '../index/login',
		data :json,
		success : function(r) {
			if(r=="true"){
				//alert(window.location.pathname) ;
//				alert(window.location.href);
				var url = window.location.href ;
				
				window.location.href= url ;//然后再刷新当前界面
			}else{
				alert("用户名或密码错误！");
			}
		}
	});
}

/**
 * 点赞
 */
function onPraise(){
	var news_id = $("#topic_id").val() ;
	var json={
		"news_id":news_id
	};
	//获取当前帖子点赞的数量
	var praiseCount = 0;
	$.ajax({
		type:"POST", 
		url : '../getPraiseCount',
		data :json,
		async:false,
		success : function(r) {
			praiseCount =r ;
		}
	});
	
	
	var flag = false ;
	
	var button_class = $("#onPraise").attr("class") ;
	var index = button_class.indexOf("btn-primary") ;
	if(index >0){
		//已经点赞了   再次点击就需要取消点赞
		flag = true ;
	}else{
		//没有点赞 点击需要进行点赞
		flag = false ;
	}
	
	if(!flag){
		//进行点赞
		$.ajax({
			type:"POST", 
			url : '../onPraise',
			data :json,
			success : function(r) {
				if(r=="true"){
//				var url = window.location.href ;
//				window.location.href= url ;//然后再刷新当前界面
					var num = parseInt(praiseCount) +1;
					$("#onPraise").removeClass() ;
					$("#onPraise").addClass("btn btn-primary");
					$("#onPraise").html("<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span>已赞" +
							"<span class='badge'>"+num+"</span>") ;
				}else{
					alert("请登录之后再点赞") ;
					$('#login').modal('show') ;
					return ;
				}
			}
		});
	}else{
		//取消点赞
		$.ajax({
			type:"POST", 
			url : '../cancelPraise',
			data :json,
			success : function(r) {
				if(r=="true"){
					var num = parseInt(praiseCount) -1;
					$("#onPraise").removeClass() ;
					$("#onPraise").addClass("btn btn-success");
					$("#onPraise").html("<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span>赞" +
							"<span class='badge'>"+num+"</span>") ;
				}else{
					alert("请登录之后再点赞") ;
					$('#login').modal('show') ;
					return ;
				}
			}
		});
	}
}

document.onkeydown = function(e) {
	var ev = document.all ? window.event : e;
	if (ev.ctrlKey && ev.keyCode == 13) {
		login();
	}
}