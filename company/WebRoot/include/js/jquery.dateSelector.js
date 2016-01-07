/*******下拉框式的日期选择器*******
dateSelector v1.0 -- 下拉框式的日期选择器
********下拉框式的日期选择器******/
(function($){
$.fn.extend({
        dateSelector:function(opt,callback){
			/*函数区域*/
			var getNowYear = function(){
				//得到现在的年
				var date = new Date();
				return date.getFullYear();
			}
			var getNowMonth = function(){
				//得到现在的月
				var date = new Date();
				return date.getMonth()+1;
			}
			var getNowDay = function(){
				//得到现在的日
				var date = new Date();
				return date.getDate();
			}
			/*判断是否闰年*/
			var isLeapYear = function(year) 
		　　{ 
		　　return (0==year%4&&((year%100!=0)||(year%400==0))); 
		　　} 
			/*END*/
            
			/*参数定义*/
			defaults = {
				yearBegin: 2012			//开始年(默认为:1960年)
				,yearEnd: 2022	//结束年(默认为:当前年)getNowYear()
				,yearEnabled:true		//年选择框是否启用(默认为:启用)
				,monthEnabled:true		//月选择框是否启用(默认为:启用)
				,dayEnabled:true		//日选择框是否启用(默认为:启用)
			}
			$.extend(defaults, opt);
			//可遍历添加多个
			$(this).each(function(){
				var _this=this;
				var el=$(this);
				el.hide();//隐藏原有的输入框
				var elval=el.val();
				var elDate = new Date(elval.split("-").join("/"));
				var oldYear = elDate.getFullYear();//取得输入框里的年值
				var oldMonth=elDate.getMonth()+1;//取得输入框里的月值(月初始是从0开始)
				var oldDay=elDate.getDate();//取得输入框里的日值
				var daysInMonth =new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);//每一个月份相应的天数
				if (isNaN(oldYear))oldYear=getNowYear();
				if (isNaN(oldMonth))oldMonth=getNowMonth();
				if (isNaN(oldDay))oldDay=getNowDay();
				/*创建年下拉框*/
				var selectYear=document.createElement("select");
				selectYear.id=_this.id+"_selectYear";
				selectYear.disabled=defaults.yearEnabled?false:true;
				for (var i=defaults.yearEnd;i>=defaults.yearBegin;i--)
				{
					var option = document.createElement("option");
					option.value = i;
					option.innerHTML = i;
					//如果等于当前年就选中
					if (!isNaN(oldYear)) {
						if (i == oldYear) {
							option.selected = true;
						}
					}
					selectYear.appendChild(option);
					option = null;
				}
				//加入控件到文本框的位置
				el.after(selectYear);

				/*END*/

				/*创建月下拉框*/
				var selectMonth=document.createElement("select");
				selectMonth.id=_this.id+"_selectMonth";
				selectMonth.disabled=defaults.monthEnabled?false:true;
				for (var i=1;i<=12;i++)
				{
					var option = document.createElement("option");
					option.value = i;
					option.innerHTML = i;
					//如果等于当前年就选中
					if (!isNaN(oldMonth)) {
						if (i == oldMonth) {
							option.selected = true;
						}
					}
					selectMonth.appendChild(option);
					option = null;
				}
				//加入控件到文本框的位置
				$(selectYear).after(selectMonth);
				$(selectMonth).before(" ");
				/*END*/

				/*创建日下拉框*/
				var selectDay=document.createElement("select");
				selectDay.id=_this.id+"_selectDay";
				selectDay.disabled=defaults.dayEnabled?false:true;
				var dayCount=daysInMonth[$(selectMonth).val()-1];//天数
				if (oldMonth==2 && isLeapYear($(selectYear).val()))dayCount++;
				$(selectDay).CreateDay(dayCount,oldDay);
				//加入控件到文本框的位置
				$(selectMonth).after(selectDay);
				$(selectDay).before(" ");
				/*END*/

				/*绑定下拉框事件*/
				//年下拉框值改变时
				$(selectYear).change(function(){
					var thisYear=this.value;//选中的年
					var dayCount=daysInMonth[$(selectMonth).val()-1];//天数
					if (oldMonth==2 && isLeapYear($(selectYear).val()))dayCount++;
					$(selectDay).CreateDay(dayCount,oldDay);
					return updateValue();
				});
				//月下拉框值改变时
				$(selectMonth).change(function(){
					var selectyear=$(selectYear).val();//选中的年
					var dayCount=daysInMonth[$(selectMonth).val()-1];//天数
					if (this.value==2 && isLeapYear(selectyear))dayCount++;
					$(selectDay).CreateDay(dayCount,oldDay);
					return updateValue();
				});
				//日下拉框值改变时
				$(selectDay).change(function(){
					return updateValue();
				});
				/*END*/
				/*实时更新输入框的值*/
				var updateValue=function(){
					el.val($(selectYear).val()+"-"+$(selectMonth).val()+"-"+$(selectDay).val());
				};
				/*END*/
			});
			return this;
        },
		//生成日期下拉框
		CreateDay:function(dayCount,oldDay){
				var me=this.get(0);
				$(this).empty();
				for (var i=1;i<=dayCount;i++)
				{
					var option = document.createElement("option");
					option.value = i;
					option.innerHTML = i;
					//如果等于当前年就选中
					if (!isNaN(oldDay)) {
						if (i == oldDay) {
							option.selected = true;
						}
					}
					me.appendChild(option);
					option = null;
				}
		}
})
})(jQuery);
