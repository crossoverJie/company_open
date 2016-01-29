$(function(){

	$("#createTopicForm").submit(function(){
		var title = $("#title").val() ;
		var content = CKEDITOR.instances.content.getData();
		if(title == "" || content ==""){
			alert("请填写内容！") ;
			return false ;
		}else{
			return true ;
		}
		
		
	});
	
});

