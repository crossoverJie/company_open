var datagridD;
// 初始化datagrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'news_name',
	title : '角色名',
	width : 200,
	align : 'center'
},{
	field : 'remark',
	title : '备注',
	width : 100,
	align : 'center'
}

];

var tabrs;
tabrs = [ {

	text : '查询',
	iconCls : 'icon-search',
	handler : function() {
		queryNews();
	}

}, '-', {
	text : '新增',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		removeNews();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyNews();
	}
}

];



function queryNews(){
	$("#queryNewsWin").window("open") ;
}
function add(){
	$("#addNewsWin").window("open") ;
	
}

function submitQuery(){
	var news_name = $("#news_name_query").val() ;
	if(news_name==""){
		news_name=undefined;
	}
	var json ={
		"news_name":news_name
	};
	$("#news_list").datagrid('options').url = 'news/getNewsList';
	$("#news_list").datagrid('options').queryParams = json;
	$("#news_list").datagrid('load');
	$('#queryNewsWin').window("close");
	
}

function modifyNews(){
	var target = $('#news_list').datagrid('getSelections');
	if (target.length < 1) {
		$.messager.show( {
			msg : '请选择一条数据进行修改!',
			title : '提示'
		});
	}else if(target.length >1){
		$.messager.show( {
			msg : '只能选择一条数据进行修改!',
			title : '提示'
		});
	}else{
		$("#modifyNewsWin").window("open") ;
		$("#news_name_edit").val(target[0].news_name);
		$("#remark_edit").val(target[0].remark) ;
	}
}

function closeWin(obj){
	$("#"+obj).window('close') ;
}
/**
 * 保存修改
 */
function saveEdit(){
	var target = $('#news_list').datagrid('getSelections');
	var news_name = $("#news_name_edit").val() ;
	var realname = $("#realname_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(news_name ==""|| remark ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"news_name":news_name,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"news/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#news_list").datagrid('reload');	
        		  	$("#modifyNewsWin").window("close") ;
        			$.messager.show( {
        				msg : '修改成功',
        				title : '提示'
        			});
        	  }
        }            
  });
}
/**
 * 新增用户之前的验证
 */
function turnToAdd(){
	var news_name = $("#news_name_add").val() ;
	var remark = $("#remark_add").val() ;
	if(news_name =="" || remark =="" ){
		$("#showMsg").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg").html("");
	}
	
	var json = {
		"news_name": news_name,
		"remark":remark
	};

    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"news/create", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#news_list").datagrid('reload');	
        		  	$("#addNewsWin").window("close") ;
        			$.messager.show( {
        				msg : '新增成功',
        				title : '提示'
        			});
        	  }
        }            
  }); 
}

//删除数据
function removeNews() {

	var list = new Array();
	var rows = $('#news_list').datagrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
				}
				$.ajax( {
					type:"get", 
					url : 'news/delete?ids=' + list + '',
					cache : false,
					success : function(r) {
					$("#news_list").datagrid('clearSelections'); // 清空选择状态
					$("#news_list").datagrid('reload');
					$.messager.show( {
						msg : "删除成功！",
						title : '提示'
					});
				}
				});
			}
		});
	} else {
		$.messager.show( {
			msg : '请至少选中一行!',
			title : '提示'
		});
	}

}

$(function(){
	$("#addNewsWin").window("close") ;
	$("#modifyNewsWin").window("close") ;
	$("#queryNewsWin").window("close") ;

	$('#news_list').datagrid({
		url : 'news/getNewsList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ datagridD ],
		rownumbers : true,
		idField : 'id',
		striped : true,
		pageSize : 25,
		pageList : [ 5,25, 35, 45, 55 ],
		nowrap : true,
		loadMsg : '数据加载中...请稍等',
		pagination : true,
		height : 'auto',
		fit : true,
		toolbar : tabrs,
		border : false,
		onDblClickRow : function(rowIndex, rowData) {

		}
	});
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}
