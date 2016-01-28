package com.cn.hnust.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
//		String fileName = "dsdsd.PDF" ;
//		int index = fileName.indexOf(".") ;
//		String hzName = fileName.substring(index+1) ;
//		hzName = hzName.toLowerCase() ;
//		System.out.println(hzName);
		
		
		String bireinfo = "<p>你们有哪些出行必备的物件？这些陪伴你走南闯北的物件中，有木有王自如同款遮瑕膏？</p>" +
				"<p><img alt='【在路上文化月第三弹】晒出行装备，致敬陪你走南闯北的物件&lt;span class=&quot;globalIcons post_greatIcon&quot; title=&quot;精华帖&quot;&gt;&lt;/span&gt;' src='http://img.zealer.com/690/0/aa997008351123f03d3d5a67567f2b96fef.jpg' /></p>" +
				"<p><a href='http://plus.zealer.com/post/423' target='blank'><strong>妹子的出行装备</strong></a>，汉子们看得懂都是些什么吗？</p>" +
				"<p><img alt='' src='/company/upload/5f96400d6bdbf0dad0932a307f88e820e4d_1.jpg' style='height:417px; width:615px' /></p>" +
				"<p><a href='http://plus.zealer.com/post/395' target='blank'><strong>摄影师的出行装备</strong></a>，是否能一眼看出摄影师在哪个城市？</p>" +
				"<p><img alt='' src='/company/upload/5f96400d6bdbf0dad0932a307f88e820e4d.jpg' style='height:400px; width:608px' /></p>" ;
		
		
//		String bireinfo = "<IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103010830.jpg 300px; HEIGHT: 201px></SPAN></FONT><SPAN style=FONT-FAMILY:  New FONT-SIZE: 10.5pt Roman?,?serif?; ?Times><IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103058264.jpg 300px; HEIGHT: 201px></SPAN><SPAN style=FONT-FAMILY:  New FONT-SIZE: 10.5pt Roman?,?serif?; ?Times><IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103323797.jpg 300px; HEIGHT: 201px></SPAN></P>";
		Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
		Matcher m = p.matcher(bireinfo);
		String[] arr = new String[3];
		int i = 0;
		String paths ="" ;
		while(m.find()) {
		arr[i] = m.group(1);
		i++;
		paths +=m.group(1)+"," ;
		}
		
		System.out.println(arr);
		System.out.println(paths);
		
	}
}
