/**
 * 给String添加trim方法
 * @return
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 验证是否为空
 */
var Verification = function(elements) {
	this.result = true;
	this.flag = 0;
    this.f=null;
	this.begin = function() {
		for ( var i = 0; i < elements.length; i += 1) {
			if ($(elements[i]).attr('name') == 'ssbm') {
				$(elements[i]).parent().css("border-top", "none");
				$(elements[i]).parent().css("border-left", "none");
				$(elements[i]).parent().css("border-right", "none");
				$(elements[i]).parent().css("border-bottom",
						"1px dashed #99CCFF");
			} else if (this.flag == 1) {
				if ($(elements[i]).attr('id') == 'choosedmp'||$(elements[i]).attr('id') == 'questionidString')
					$(elements[i]).parent().css("border", "1px solid #CCCCCC");
				else
					$(elements[i]).css("border", "2px inset #FFFFFF");
			} else
				$(elements[i]).css("border", "1px solid #999999");
			if ($(elements[i]).val() == "" || $(elements[i]).val() == null) {
				if ($(elements[i]).attr('name') == 'ssbm'
						|| $(elements[i]).attr('id') == 'choosedmp'
							||$(elements[i]).attr('id') == 'questionidString')
					$(elements[i]).parent().css("border", "1px solid #FF6F6F");
				else
					$(elements[i]).css("border", "1px solid #FF6F6F");
				this.result = false;
			}
		}
		return this.result;
	}
}

var Verification1 = function(elements) {
	this.result = true;
	this.flag = 0;
    this.f=null;
	this.begin = function() {
		for ( var i = 0; i < elements.length; i += 1) {
			if($(elements[i]).attr("name")=="xuanxiangkaname")
			this.f=$(elements[i]);
			if ($(elements[i]).attr('name') == 'ssbm') {
				$(elements[i]).parent().css("border-top", "none");
				$(elements[i]).parent().css("border-left", "none");
				$(elements[i]).parent().css("border-right", "none");
				$(elements[i]).parent().css("border-bottom",
						"1px dashed #99CCFF");
			} else if (this.flag == 1) {
				if ($(elements[i]).attr('id') == 'choosedmp'||$(elements[i]).attr('id') == 'questionidString')
					$(elements[i]).parent().css("border", "1px solid #CCCCCC");
				else
					$(elements[i]).css("border", "2px inset #FFFFFF");
			} else
				$(elements[i]).css("border", "1px solid #999999");
			if ($(elements[i]).val() == "" || $(elements[i]).val() == null) {
				if ($(elements[i]).attr('name') == 'ssbm'
						|| $(elements[i]).attr('id') == 'choosedmp'
							||$(elements[i]).attr('id') == 'questionidString')
				{
					$(elements[i]).parent().css("border", "1px solid #FF6F6F");
					$(this.f).css("border", "1px solid #FF6F6F");
				}
				else
				{
					$(elements[i]).css("border", "1px solid #FF6F6F");
					$(this.f).css("border", "1px solid #FF6F6F");
				}
				this.result = false;
			}
		}
		return this.result;
	}
}
/**
 * 验证是否为空
 * 
 * @param elements
 *            装载dom对象的数组
 * @param newcss
 *            为空时，在对象上显示的样式
 * @return
 */
var isEmpty = function(elements, newcss) {
	this.begin = function() {
		var result=false;
		for ( var i = 0; i < elements.length; i += 1) {
			if($(elements[i]).attr("CantStyleSelf")=="yes")
			    $(elements[i]).parent().removeClass(newcss);
			else
				$(elements[i]).removeClass(newcss);
			if ($(elements[i]).val() == "" || $(elements[i]).val() == null) {
				if($(elements[i]).attr("CantStyleSelf")=="yes")
					$(elements[i]).parent().addClass(newcss);
				else
					$(elements[i]).addClass(newcss);
				result=true;
			}
		}
		return result;
	}
}

/**
 * 验证填写的数据是否符合规范,找到不匹配的就返回false
 * @param elements
 * dom对象数组
 * @param newcss
 * 验证不符合规范时的样式
 * @param regx
 * 数据要符合的规范
 * @param data
 * 实际数据 
 * @return
 */
var rightStyle = function(elements, newcss, regx) {
	this.begin = function() {
		var result = true;
		for ( var i = 0; i < elements.length; i += 1) {
			$(elements[i]).removeClass(newcss);
			if ($(elements[i]).val() != "") {
				if (!regx.test($(elements[i]).val())) {
					$(elements[i]).addClass(newcss);
					result = false;
				}
			}
		}
		return result;
	}
}

/**
 * 验证每一个CDSID皆存在于用户表中
 * @param targ
 * 要验证的cdsid,以“，”分隔开的
 * @param flag
 * 0：所有用户，1：总监
 * @return
 * 如果有一个CDSID不在用户表中，则返回： false
 */
var isContain = function(targ, flag) {
	this.begin = function() {
		var targs = targ.split(',');
		var sources = getCdsid.call(this, flag);
		if (sources != "") {
			sources = getCdsid.call(this, flag).split(',');// 得到满足条件的数据库中的值
			//alert(sources);
			// alert(getCdsid.call(this,flag));
			for ( var i = 0, len = targs.length; i < len; i += 1)
				for ( var j = 0, len1 = sources.length; j < len1; j += 1) {
					if (targs[i].replace(/(^\s*)|(\s*$)/g, "") == sources[j])
						break;
					if (j == len1 - 1)
						return false;
				}
		} else {
			alert("数据库中无相应条件的用户存在，请联系管理员维护后台数据！");
		}
		return true;
	}
}
/**
 * 得到给定条件的CDSID
 * @param flag
 * 0:所有CDSID  1:总监级别CDSID
 * @return
 */
function getCdsid(flag) {
	xmlhttp = null;
	var tt = this;//保存当前对象，以便在onreadystatechange的对象中使用
	tt.result = "";//用户返回结果
	//建立合同谈判XMLHTTPREQUEST对象
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
		}
	}
	if (xmlhttp != null) {
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				tt.result = xmlhttp.responseText;//将返回结果保存如外层对象中
			}
		}
		xmlhttp.open("post", "getCdsid.action", false);//设置为同步
		// setRequestProperty("Content-type","text/xml");
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");//表单提交
		xmlhttp.send("flagString=" + flag);//要发送的数据

	} else {
		alert("你的浏览器不支持异步刷新数据,建议升级浏览器版本！");
	}
	return tt.result;
}