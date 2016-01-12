var treegridD;
// 初始化treegrid

treegridD = [{
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
	width : 180,
	align : 'center'
},{
	field : 'remark',
	title : '备注',
	width : 150,
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
}, '-', {
	text : '刷新',
	iconCls : 'icon-reload',
	handler : function() {
		refresh();
	}
} 
];

function queryFunction(){
	$("#queryFunctionWin").window("open") ;
}
function add(){
	$("#addFunctionWin").window("open") ;
	var type = $("#function_type_add").combobox("getValue") ;
	if(type=="1"){
		$("#funtion_type_one_add").combobox({
			disabled:true
		})
	}
	
	$("#funtion_type_one_add").combobox({
		url:"function/getAllone",
		valueField:'id', 
		textField:'function_name'   
	});
}

function submitQuery(){
	var function_name = $("#function_name_query").val() ;
	var function_url = $("#function_url_query").val() ;
	if(function_name==""){
		function_name=undefined;
	}
	if(function_url ==""){
		function_url=undefined;
	}
	var json ={
		"function_name":function_name,
		"function_url":function_url
	};
	$("#function_list").treegrid('options').url = 'function/getFunctionList';
	$("#function_list").treegrid('options').queryParams = json;
	$("#function_list").treegrid('load');
	$('#queryFunctionWin').window("close");
	
}

function modifyFunction(){
	var target = $('#function_list').treegrid('getSelections');
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
		$("#function_name_edit").val(target[0].name);
		$("#function_url_edit").val(target[0].function_url);
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
	var target = $('#function_list').treegrid('getSelections');
	var function_name = $("#function_name_edit").val() ;
	var function_url = $("#function_url_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(function_name =="" || function_url == "" || remark ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"function_name":function_name,
		"function_url":function_url,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"function/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#function_list").treegrid('reload');	
        		  	$("#modifyFunctionWin").window("close") ;
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
	var function_type = $("#function_type_add").combobox("getValue") ;
	var function_name = $("#function_name_add").val() ;
	var function_url = $("#function_url_add").val() ;
	var remark= $("#function_remark_add").val() ;
	var function_type_one_add = $("#funtion_type_one_add").combobox("getValue");
	
	//定义一个变量保存功能名称 因为有不同的情况 选择一级功能和二级功能。
	var name="" ;
	if(function_name != ""){
		name= function_name;
	}else if(function_type_one_add != ""){
		name= function_type_one_add;
	}
	
	var parent_id  ;
	if(function_type_one_add != ""){
		parent_id = function_type_one_add;
	}else {
		parent_id = -1;
	}
	
	if(name =="" || remark =="" || function_type==""|| function_url=="" ){
		$("#showMsg").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg").html("");
	}
	
	var json = {
		"function_name": function_name,
		"function_type":function_type,
		"function_url":function_url,
		"parent_id":parent_id,
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
        		  	$("#function_list").treegrid('reload');	
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
	var parent_id= "";
	var rows = $('#function_list').treegrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
					parent_id = rows[0].parent_id;
				}
				$.ajax( {
					type:"POST", 
					url : 'function/delete?ids=' + list + '&parent_id='+parent_id,
					cache : false,
					success : function(r) {
					$("#function_list").treegrid('clearSelections'); // 清空选择状态
					$("#function_list").treegrid('reload');
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
	
	$('#function_list').treegrid({    
		url : 'function/getFunctionList', 
	    idField:'id',    
	    treeField:'name',   
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		rownumbers : true,
		toolbar : tabrs,
		loadMsg : '数据加载中...请稍等',
		height : 'auto',
		animate:true,
		fit : true,
	    columns:[[
	              {title:'功能名称',field:'name',width:180},  
	              {title:'功能地址',field:'function_url',width:180},    
	              {title:'备注',field:'remark',width:180},    
	          ]]
	});
	
	
	$("#function_type_add").combobox({
		onSelect:function(obj){
			var type = obj.value;
			if(type=='2'){
				$("#funtion_type_one_add").combobox({
					disabled:false
				})
			}else if(type='1'){
				$("#funtion_type_one_add").combobox({
					disabled:true
				})
			}
		}
	});
	
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}

function refresh(){
	$("#function_list").treegrid('reload');
}
