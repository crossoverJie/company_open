var datagridD;
// 初始化treegrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'name',
	title : '名称',
	width : 200,
	align : 'center'
},{
	field : 'path',
	title : '图片地址',
	width : 100,
	align : 'center'
},{
	field : 'url',
	title : '图片',
	width : 180,
	align : 'center'
},{
	field : 'img_size',
	title : '图片大小',
	width : 180,
	align : 'center'
},{
	field : 'remark',
	title : '备注',
	width : 150,
	align : 'center'
},{
	field : 'parseDate',
	title : '创建日期',
	width : 150,
	align : 'center'
}

];

var tabrs;
tabrs = [ {

	text : '查询',
	iconCls : 'icon-search',
	handler : function() {
		queryImg();
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
		removeImg();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyImg();
	}
} 
];

function queryImg(){
	$("#queryImgWin").window("open") ;
}
function add(){
	$("#addImgWin").window("open") ;
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
	$("#img_list").treegrid('options').url = 'function/getImgList';
	$("#img_list").treegrid('options').queryParams = json;
	$("#img_list").treegrid('load');
	$('#queryImgWin').window("close");
	
}

function modifyImg(){
	var target = $('#img_list').treegrid('getSelections');
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
		$("#modifyImgWin").window("open") ;
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
	var target = $('#img_list').treegrid('getSelections');
	var function_name = $("#function_name_edit").val() ;
	var function_url = $("#function_url_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(function_name =="" ){
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
        		  	$("#img_list").treegrid('reload');	
        		  	$("#modifyImgWin").window("close") ;
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
	var name =$("#name_add").val() ;
	var remark = $("remark_add").val() ;
	if(name =="" || remark == ""){
		return ;
	}
	$('#addImgForm').form('submit', {    
	    success:function(data){    
	    	$("#img_list").treegrid('reload');	
		  	$("#addImgWin").window("close") ;
			$.messager.show( {
				msg : '新增成功',
				title : '提示'
			});
	    }    
	});  
	
}

//删除数据
function removeImg() {

	var list = new Array();
	var parent_id= "";
	var rows = $('#img_list').treegrid('getSelections');
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
					$("#img_list").treegrid('clearSelections'); // 清空选择状态
					$("#img_list").treegrid('reload');
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
	$("#addImgWin").window("close") ;
	$("#modifyImgWin").window("close") ;
	$("#queryImgWin").window("close") ;
	
	$('#img_list').treegrid({
		url : 'img/getImgList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
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
		nowrap : false,//可以换行显示
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

