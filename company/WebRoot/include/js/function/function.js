var datagridD;
// 初始化datagrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'parent_id',
	title : '父节点',
	width : 200,
	align : 'center'
},{
	field : 'function_name',
	title : '功能名称',
	width : 100,
	align : 'center'
},{
	field : 'function_url',
	title : '功能地址',
	width : 100,
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
		queryFunction();
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
		removeFunction();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyFunction();
	}
} 
];

function queryFunction(){
	$("#queryFunctionWin").window("open") ;
}
function add(){
	$("#addFunctionWin").window("open") ;
	var type = $("#function_type_add").combobox("getValue") ;
	if(type == "1"){
		$("#function_name_add").attr("disabled","disabled") ;
	}
}

function submitQuery(){
	var username = $("#username_query").val() ;
	var realname = $("#realname_query").val() ;
	if(username==""){
		username=undefined;
	}
	if(realname ==""){
		realname=undefined;
	}
	var json ={
		"username":username,
		"realname":realname
	};
	$("#function_list").datagrid('options').url = 'user/getFunctionList';
	$("#function_list").datagrid('options').queryParams = json;
	$("#function_list").datagrid('load');
	$('#queryFunctionWin').window("close");
	
}

function modifyFunction(){
	var target = $('#function_list').datagrid('getSelections');
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
		$("#modifyFunctionWin").window("open") ;
		$("#username_edit").val(target[0].username);
		$("#realname_edit").val(target[0].realname);
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
	var target = $('#function_list').datagrid('getSelections');
	var username = $("#username_edit").val() ;
	var realname = $("#realname_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(username =="" || realname == "" || remark ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"username":username,
		"realname":realname,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"user/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#function_list").datagrid('reload');	
        		  	$("#modifyFunctionWin").window("close") ;
        			$.messager.show( {
        				msg : '新增成功',
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
	var function_type = $("#function_type_add").combobox("getValue") ;
	var function_name = $("#function_name_add").val() ;
	var function_url = $("#function_url_add").val() ;
	var remark= $("remark_add").val() ;
	var remark = $("#remark_add").val() ;
	if(function_name =="" || remark =="" ){
		$("#showMsg").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg").html("");
	}
	
	var json = {
		"function_name": function_name,
		"remark":remark
	};

    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"function/create", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#function_list").datagrid('reload');	
        		  	$("#addFunctionWin").window("close") ;
        			$.messager.show( {
        				msg : '新增成功',
        				title : '提示'
        			});
        	  }
        }            
  }); 
}

//删除数据
function removeFunction() {

	var list = new Array();
	var rows = $('#function_list').datagrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
				}
				$.ajax( {
					type:"POST", 
					url : 'user/delete?ids=' + list + '',
					cache : false,
					success : function(r) {
					$("#function_list").datagrid('clearSelections'); // 清空选择状态
					$("#function_list").datagrid('reload');
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
	$("#addFunctionWin").window("close") ;
	$("#modifyFunctionWin").window("close") ;
	$("#queryFunctionWin").window("close") ;
	$('#function_list').datagrid({
		url : 'function/getFunctionList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
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
	
	$("#function_type_add").combobox({
		onSelect:function(obj){
			var type = obj.value;
			if(type=='2'){
				$("#function_name_add").removeAttr("disabled") ;
			}else if(type='1'){
				$("#function_name_add").attr("disabled","disabled") ;
			}
		}
	});
	
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}
