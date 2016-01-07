var $$ = function(elementId){
	function getObjs(str)
	{
		var type=str.split("=")[0];
		var value=str.split("=")[1];

		if(type="name")return document.getElementsByName(value);
		if(type="id")return document.getElementById(value);
		
	}
	if(elementId.indexOf("=")>-1)
	{
		if(elementId.indexOf("[")>-1)
		{   
			var resAr=[];
			var beginStr = elementId.substring(0,elementId.indexOf("["));
			var endStr = elementId.substring(elementId.indexOf("[")+1,elementId.indexOf("]"));
			var eArr=endStr.split("=");
			for ( var i = 0,elms=getObjs(beginStr); i < elms.length; i++) {
                if ((elms[i].getAttribute(eArr[0]) == eArr[1])||(elms[i].getAttribute(eArr[0]) == eval(eArr[1])))
                resAr[resAr.length]=elms[i];
			                         	
			}
			return resAr; 
		}
		else
		{
			return	getObjs(elementId);
		}
	}
	else
	{
	  return document.getElementById(elementId);
	}
}
var $v = function(elementId){return document.getElementById(elementId).value.Trim();};
String.prototype.Trim = function(){ return this.replace(/(^\s*)|(\s*$)/g, "");}//去除字符串首尾空格
String.prototype.isEmpty =function(){return (this=="");}//判断字符串的值是否为空
String.prototype.isNull =function(){return (this==null);}//判断字符串对象是否为空

var toHTML = { 
		   on: function(str) { 
		        var a = [], 
		        i = 0; 
				for (; i < str.length;) a[i] = str.charCodeAt(i++); 
				return "&#" + a.join(";&#") + ";" 
				}, 
		   un: function(str) { 
			   return str.replace(/&#(x)?([^&]{1,5});?/g, 
		           function(a, b, c) { 
		             return String.fromCharCode(parseInt(c, b ? 16 : 10)) 
		           }) 
		       } 
		}; 

function showError(err)
{
	if(!err.isEmpty())
	{
	   alert(toHTML.un(err));
	}
}

function openwindow(src){
	var result = window.showModalDialog(src,window,"status:no;scroll:yes;dialogWidth:335px;dialogHeight:300px");
	if(result=="re"){window.location.reload();return;}
	return result;
}
function showModel_dingyi(url,par,sty,tt)//打开Model窗口
{
	var strFeatures = "center=yes;middle=yes;help=no;status=no;scroll=no;resizable:yes;";
	if(sty)
	{
		if(sty.w)strFeatures=("dialogWidth="+sty.w+";")+strFeatures;
		if(sty.h)strFeatures=("dialogHeight="+sty.h+";")+strFeatures;
	}
	var result = showModalDialog(url,par,strFeatures)
	if(tt == null)
	{
	  if(result=="re"){window.location.reload();return;}
	}
	return result;
}
/*
function showModel_I(url,par,sty,tt)//打开Model窗口
{
	var strFeatures = "center=yes;middle=yes;help=no;status=no;scroll=no;resizable:yes;";
	if(sty)
	{
		if(sty.w)strFeatures=("dialogWidth="+sty.w+";")+strFeatures;
		if(sty.h)strFeatures=("dialogHeight="+sty.h+";")+strFeatures;
	}
	var result = showModalDialog(url,par,strFeatures)
	if(tt == null)
	{
	  if(result=="re"){window.location.reload();return;}
	}
	return result;
}
*/
function showModalDialogWindow(url,width,height,params){
    strFeatures="dialogWidth="+width+"px;dialogHeight="+height+"px;center=yes;middle=yes;help=no;status=no;scroll=yes;";               
    strReturn=window.showModalDialog(url,params,strFeatures);
    return strReturn;
}
function selectByCondition(){
	 document.forms[0].submit();
}
function deletedata(url)//删除链接执行方法
{
	if(confirm("确认删除此记录吗？"))
	{
	   window.location=url;
	   return false;
	}
	  return true;
}
function resetdata(url)//重置状态执行方法
{
	if(confirm("确认重置此记录吗？"))
	{
	   window.location=url;
	   return false;
	}
	  return true;
}
function auditdata(url)//审核链接执行方法
{
	if(confirm("确认审核此记录吗？"))
	{
	   window.location=url;
	   return false;
	}
	  return true;
}
//分页
function myPC()
{
	var pageNum=0,pageCount=0,rowCount=0,pageSize=0;
	var top =this;
	var table;
	this.url="";
	this.parameters=[];
	this.formName = "";
	this.load = function(pageNum1,rowCount1,pageSize1,obj)
	{
		initData(pageNum1,rowCount1,pageSize1);
		createTable();
		
		var titleRow = table.insertRow(0);//创建行
		var leftTd = titleRow.insertCell(titleRow.cells.length);
		var rightTd = titleRow.insertCell(titleRow.cells.length);
		leftTd.setAttribute("align","left");
		rightTd.setAttribute("align","right");
				
		leftTd.innerHTML = "<span style=\"font-size:12px;\">共有&nbsp;&nbsp;"+rowCount+"&nbsp;&nbsp;条记录，当前第&nbsp;&nbsp;"+pageNum+"&nbsp;&nbsp;页，共&nbsp;&nbsp;"+pageCount+"&nbsp;&nbsp;页</span>";
		var par=[];
		var pnStr="";
		var pn=5;
		if (pageNum <= 1)
		{
			pnStr = "<span class=\"disabled\">[首页]</span><span class=\"disabled\">[上一页] </span>";
		}
		else
		{   par[0] = 1;
			pnStr = initUrl("[首页]",par);
			par[0] = (pageNum-1);
			pnStr += initUrl("[上一页]",par);
		}
		for (i = pageNum-pn; i < pageNum; i++)
		{
			if (i>1)
			{
				par[0] = i;
				pnStr += initUrl(i,par);
			}
		}
		for (i = pageNum; i <= pageNum+pn; i++)
		{
			if (i == pageNum)
			{
				pnStr += "<span class=\"current\">"+i+"</span>";
			}
			else if (i <= pageCount)
			{
				par[0] = i;
				pnStr += initUrl(i,par);
			}
			else
			{
				
			}
		}
		if(pageNum+pn < pageCount-2)
		{
			pnStr +="...";
			for (i = pageCount-2; i < pageCount; i++)
			{
				par[0] = i;
				pnStr += initUrl(i,par);
			}
		}
		if(pageNum>=pageCount)
		{
			pnStr += "<span class=\"disabled\">[下一页]</span><span class=\"disabled\">[尾页]</span>";
		}
		else
		{
			par[0] = (pageNum+1);
			pnStr += initUrl("[下一页]",par);
			par[0] = pageCount;
			pnStr += initUrl("[尾页]",par);
		}
		//alert(pnStr);
		rightTd.innerHTML = pnStr;
		document.getElementById(obj).appendChild(table);
}
	
	function initData(pageNum1,rowCount1,pageSize1)
	{
		if(parseInt(pageNum1) == pageNum1) pageNum = pageNum1;//当前页
		if(parseInt(rowCount1) == rowCount1) rowCount = rowCount1;//总记录数
		if(parseInt(pageSize1) == pageSize1) pageSize = pageSize1;//每页条数
        if((rowCount!==0)&&(pageSize!==0))
		pageCount = Math.ceil(rowCount/pageSize);//页面总数		
	}
	
	function createTable()
	{
		table = document.createElement("table"); 
	    table.width="100%";
	    table.border="0";
	    table.cellPadding="0";
	    table.cellSpacing="0";
	    //table.className=top.styles.tbclass;
	    
	}
	
	function initParameters(t)
	{
		var re ="";
		for(j=0;j<top.parameters.length;j++)
		{
			if(top.parameters[j]!=null)
			{
				re += top.parameters[j] +"="+ t[j];	
			}
		}
		return re;
	}
	function initUrl(tx,par)
	{   
		if(top.formName)
		{
			var urlStr ="<a href=\"javascript:void(0);\" onclick=\"" +
					"var formObj = document.getElementsByName('"+top.formName+"')[0];" +
					"if(formObj.action.indexOf(\'?\')>-1)formObj.action+=\'&\';" +
					"else formObj.action += \'?\';" +
					"formObj.action +=\'"+initParameters(par)+"\';"+
					"formObj.submit();return false;\"";
			urlStr += ">"+tx+"</a>";
			//alert(urlStr);
			return urlStr;
		}
		var urlStr ="<a href=\""+top.url;
		if(top.url.indexOf("?")>-1)urlStr+="&";
		else urlStr += "?";
		urlStr += initParameters(par)+"\"";
		urlStr += (top.fun == null ? "" : "onclick=\"return "+top.fun+"\"");
		//urlStr+=" onclick=\"alert(this.href)\"";
		urlStr += ">"+tx+"</a>";
		return urlStr;
	}
}
function todo(type,tt)//录入界面按钮相关操作
{
   if(type == "submmit"){document.forms[0].submit();}
   else if(type == "back"){history.back();}
   else if(type == "reset"){document.forms[0].reset();}
   else if(type == "close"){if(tt=="rl"){window.returnValue=1;}window.close();}
   else {}
}
//主要责任部门和次要责任部门效果JS
function cleanselecteddpm(zrbm,zrbm_cy){
	//var selected = document.getElementById("zrbm").value;
	var desc_id="";
	var dex_id = [];
	var cy_id = "";
	var selected = $$(zrbm);
	var element = $$(zrbm_cy);
	for(var i=0;i<selected.options.length;i++){
	    if(selected.options[i].selected){
	        desc_id += selected.options[i].value+",";// 收集选中项
	    }else{
	    	dex_id.push(selected.options[i]);
	    }
	}
	//去除选中项
	for(var i=0; i<element.childNodes.length; i++) 
		{ 
		var node = element.childNodes[i]; 
		if(desc_id.indexOf(node.value)!=-1) 
		{ 
			node.parentNode.removeChild(node); 
		}
		}
	//去除选中项后剩下的选项
	for(var i=0; i<element.childNodes.length; i++) 
		{ 
			var node = element.childNodes[i]; 
			if(node.value != null){
				cy_id += node.value+",";
			}
		}
	//把没有在次要责任部门没有的，但是在主要责任部门里面没有选中的加上
	for(var i=0; i<dex_id.length; i++){
			//alert(dex_id[i].value+"%%"+cy_id);
			if(cy_id.indexOf(dex_id[i].value+"") == -1 && dex_id[i].value != null){
				var jj=dex_id[i].cloneNode(true);
				//alert($(jj));
				$(jj).appendTo($(element));
			}
	} 
}
//主要责任部门和次要责任部门的修改
function changeDisableed() {
	 document.getElementById("zrbm").disabled=false;
	 document.getElementById("zrbm_cy").disabled=false;
}
//主要责任部门和次要责任部门的蒙版效果
var Mask=function(obj)
{
this.background="#000";
this.zindex="100000";
this.filter="alpha(opacity=10)";
this.opacity="0.1";
this.Create=function(){
var maskDiv=document.createElement("div");
maskDiv.setAttribute("id","mask"+obj.getAttribute("id"));
maskDiv.style.position="absolute";
maskDiv.style.display="block";
maskDiv.style.background=this.background;
maskDiv.style.width=(parseInt(obj.clientWidth)+parseInt($(obj).css("border-right-width"))+parseInt($(obj).css("border-left-width")))+"px";
maskDiv.style.height=(parseInt(obj.clientHeight)+parseInt($(obj).css("border-top-width"))+parseInt($(obj).css("border-bottom-width")))+"px";
maskDiv.style.top=($(obj).offset().top)+"px";
maskDiv.style.left=($(obj).offset().left)+"px";
maskDiv.style.filter=this.filter;
maskDiv.style.opacity=this.opacity;
maskDiv.style.zindex=this.zindex;
document.getElementsByTagName("body")[0].appendChild(maskDiv);
}
}
function removeMask(id)
{
var maskDiv=document.getElementById("mask"+id);
maskDiv.parentNode.removeChild(maskDiv);
}