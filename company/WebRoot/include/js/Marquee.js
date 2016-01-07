 /* 彦磊基于jQuery图片平滑滚动插件(2009.04)
 * 在IE6/IE7/Mozilla 5.0（Firefox 3.0.5）中测试通过
 * 
 * 
 * 此插件要求运行在jQuery v1.2 或更高版本上
 * 插件可以实现图片平滑向上或向左滚动
 * 如果您需要更多的效果或在使用中本插件存在BUG，可以给在彦磊的博客上留言，彦磊将尽自己最大努力完善此插件
 * 您留言前请先到：http://gmarwaha.com/jquery/jcarousellite/ 上面下载jcarousellite插件　
 * 来和彦磊图片平滑滚动的插件相互补充使用以获取更多的图片效果
 * 彦磊图片平滑滚动插件只是对jcarousellite插件的补充
 * 
 * 
 * 技术支持：HTTP://HI.BAIDU.COM/DPXDQX
 * 此为自由拷贝版本，您可以在此基础上添加任何功能
 * 彦磊建议您将修改过的版本或发布地址发到彦磊博客上以共享，也建议您保留部分彦磊的信息
 * 
 * 
 * 此插件没有象jcarousellite插件样对相关元素的CSS全自动设置，部分CSS样式需要您为设置
 * 原因很简单：您需要考虑部分用户的浏览器不支持JAVASCRIPT或相关功能被禁用
 * 您需要让您的页面元素布局在此时也能显示正确，因此必要的CSS设置还需要您自己来做。
 * 
 * 使用前您一定要引入jQurey及本脚本,如
 *  <script type="text/javascript" src="js/jquery_last.js"></script>
 *  <script type="text/javascript" src="js/YlMarquee.js">
 * 使用方法：
 * 
 * 1、为您要设置滚动的对象设置容器，并命名class，如class="ylMarquee"
 * 2、将您要滚动的对象置入<ul></ul>中，并添加li标签，li标签中可以是图片、文字或表格……
 * 3、此版本需要您设置每个li列表的宽度或高度一至
 *  <div class="ylMarquee">
 *     <ul>
 *          <li><img src="image/1.jpg" alt="1"></li>
 *          <li><img src="image/2.jpg" alt="2"></li>
 *          <li><img src="image/3.jpg" alt="3"></li>
 *      </ul>
 *  </div>
 * 4、设置ylMarquee的height或width样式——向上滚动必须设置height的大小，向左滚动必须设置width的大小，否则页面将显示不正常。
 * 5、在页面中添加jQuery语句，调用此插件，并对相关参数进行设置,如：
 * <script type="text/javascript">
 * $(document).ready(function(){
 *  $(".stroll").jMarquee({
 *     visible:3,
 *     step:1,
 *     direction:"left"
 *   });
 *});
 * </script>
 * 6、插件相关参数：
 * visible:页面可见元素（图片）个数，默认为1，必须参数。
 * step:滚动步长，整数，默认为1，增大此数可加快滚动速度，设为0，则不进行滚动。
 * direction:滚动方向，有"left"（向左滚动）和"up"（向上滚动）两个参数，注意设置时一定要加英文的双引("")号或单引号('')。
 */

(function($) { 
 $.fn.jMarquee = function(o) {
    o = $.extend({
    speed:30,
    step:1,//滚动步长
    direction:"up",//滚动方向
    visible:1//可见元素数量
    }, o || {});
    //获取滚动内容内各元素相关信息
    var i=0;
    var div=$(this);
    var ul=$("ul",div);
    var tli=$("li",ul);
    var liSize=tli.size();
    if(o.direction=="left")
        tli.css("float","left");
    var liWidth=tli.innerWidth();
    var liHeight=tli.height();
    var ulHeight=liHeight*liSize;
    var ulWidth=liWidth*liSize;
  
    //如果对象元素个数大于指定的显示元素则进行滚动，否则不滚动。
    if(liSize>o.visible){
        ul.append(tli.slice(0,o.visible).clone())  //复制前o.visible个li，并添加到ul的最后
        li=$("li",ul);
        liSize=li.size();
        
          //给滚动内容添加相关CSS样式
        div.css({"position":"relative",overflow:"hidden"});
        ul.css({"position":"relative",margin:"0",padding:"0","list-style":"none"});
        li.css({margin:"0",padding:"0","position":"relative"});
        
        switch(o.direction){
            case "left":
                div.css("width",(liWidth*o.visible)+"px");
                ul.css("width",(liWidth*liSize)+"px");
                li.css("float","left");
                break;
            case "up":
                div.css({"height":(liHeight*o.visible)+"px"});
                ul.css("height",(liHeight*liSize)+"px");
                break;
        }
        
       
        var MyMar=setInterval(ylMarquee,o.speed);
        ul.hover(
            function(){clearInterval(MyMar);},
            function(){MyMar=setInterval(ylMarquee,o.speed);}
        );
    };
    function ylMarquee(){
         
        if(o.direction=="left"){
            if(div.scrollLeft()>=ulWidth){
                div.scrollLeft(0);
            }
            else
            {
                var leftNum=div.scrollLeft();
                leftNum+=parseInt(o.step);
                div.scrollLeft(leftNum)
            }
        }
        
        if(o.direction=="up"){
            if(div.scrollTop()>=ulHeight){
               div.scrollTop(0);
                
            }
            else{
               var topNum=div.scrollTop();
               topNum+=parseInt(o.step);
               div.scrollTop(topNum);
            }
        }
        
    };
   
}; 
     
})(jQuery);
     
   
     

