var datagridD;
// 初始化datagrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'role_name',
	title : '角色名',
	width : 200,
	align : 'center'
},{
	field : 'all_function_name',
	title : '所有权限',
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
		queryRole();
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
		removeRole();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyRole();
	}
}, '-', {
	text : '授权',
	iconCls : 'icon-set',
	handler : function() {
		accredit();
	}
} 

];

/**
 * 打开授权窗口
 */
function accredit(){
	
	var target = $('#role_list').datagrid('getSelections');
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
	}else {
		var role_id = target[0].id ;
		$("#accreditNewsWin").window("open") ;
		$("#sq").tree({    
			url:"role/getFunctionByRole?role_id="+role_id ,
			animate : true,
			checkbox : true,
			cascadeCheck:false,
			lines: true,
			 onCheck: function (node, checked) {
                 if (checked) {
                     var parentNode = $("#sq").tree("getParent", node.target);
                     if (parentNode != null) {
                         $("#sq").tree("check", parentNode.target);
                     }
                 } else {
                     var childNode = $("#sq").tree("getChildren", node.target);
                     if (childNode.length > 0) {
                         for (var i = 0; i < childNode.length; i++) {
                             $("#sq").tree("uncheck", childNode[i].target);
                         }
                     }
                 }
             }
		}); 
	}
	
}
/**
 * 确认授权 更新信息
 */
function subSq(){
	var target = $('#role_list').datagrid('getSelections');
	var nodes = $("#sq").tree("getChecked") ;
	var json= {
		"id":target[0].id,
		"function_id" : nodes
	};
	
	var list = new Array();
	list.splice(0);
	for(var i=0;i<nodes.length;i++){
		list.push(nodes[i].id);
	}
	
	$.ajax({
		   type: "POST",
		   url: "role/editSq?id="+target[0].id+"&function_id="+list,
		   success: function(msg){
				$("#role_list").datagrid('reload');	
	   		  	$("#accreditNewsWin").window("close") ;
	   			$.messager.show({
	   				msg : '修改成功',
	   				title : '提示'
	   			});
			   }
		});
    
    
}

function queryRole(){
	$("#queryRoleWin").window("open") ;
}
function add(){
	$("#addRoleWin").window("open") ;
}

function submitQuery(){
	var role_name = $("#role_name_query").val() ;
	if(role_name==""){
		role_name=undefined;
	}
	var json ={
		"role_name":role_name
	};
	$("#role_list").datagrid('options').url = 'role/getRoleList';
	$("#role_list").datagrid('options').queryParams = json;
	$("#role_list").datagrid('load');
	$('#queryRoleWin').window("close");
	
}

function modifyRole(){
	var target = $('#role_list').datagrid('getSelections');
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
		$("#modifyRoleWin").window("open") ;
		$("#role_name_edit").val(target[0].role_name);
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
	var target = $('#role_list').datagrid('getSelections');
	var role_name = $("#role_name_edit").val() ;
	var realname = $("#realname_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(role_name ==""|| remark ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"role_name":role_name,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"role/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#role_list").datagrid('reload');	
        		  	$("#modifyRoleWin").window("close") ;
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
	var role_name = $("#role_name_add").val() ;
	var remark = $("#remark_add").val() ;
	if(role_name =="" || remark =="" ){
		$("#showMsg").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg").html("");
	}
	
	var json = {
		"role_name": role_name,
		"remark":remark
	};

    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"role/create", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#role_list").datagrid('reload');	
        		  	$("#addRoleWin").window("close") ;
        			$.messager.show( {
        				msg : '新增成功',
        				title : '提示'
        			});
        	  }
        }            
  }); 
}

//删除数据
function removeRole() {

	var list = new Array();
	var rows = $('#role_list').datagrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
				}
				$.ajax( {
					type:"get", 
					url : 'role/delete?ids=' + list + '',
					cache : false,
					success : function(r) {
					$("#role_list").datagrid('clearSelections'); // 清空选择状态
					$("#role_list").datagrid('reload');
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
	$("#addRoleWin").window("close") ;
	$("#modifyRoleWin").window("close") ;
	$("#queryRoleWin").window("close") ;
	$("#accreditNewsWin").window("close") ;
	$('#role_list').datagrid({
		url : 'role/getRoleList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
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
		nowrap : false,
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
