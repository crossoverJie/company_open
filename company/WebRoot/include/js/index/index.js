$(function(){
	$('#password').window('close');
	var centerTabs = $('#tabs').tabs({
		border : false,
		fit : true
	});
	
	
	
}) ;

function getMain(obj,url){
	var name = $(obj).html() ;
	if (!$('#tabs').tabs('exists', name)) {
	$('#tabs').tabs('add',{
		title: name,
		content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>',
		closable: true
	});
	}else{
		$('#tabs').tabs('select', name);
		// 刷新页面
		var tab = $('#tabs').tabs('getSelected');

		$('#tabs').tabs('update', {
			tab : tab,
			options : {
				title : name,
				content : '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>',
				closable : true
			}

		});
	}
}

function loginOut(){
	window.location="loginOut" ;
}