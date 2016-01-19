package com.cn.hnust.pojo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Img {
	private Integer id ;
	private String name ; 
	private String path ;
	private String url ;//用于在页面中显示图片
	private String img_size ;
	private Date create_date ;
	private String remark ;
	
	private String parseDate ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getImg_size() {
		return img_size;
	}
	public void setImg_size(String img_size) {
//		DecimalFormat df = new DecimalFormat("######0.00");   
//		double size = Double.parseDouble(img_size) ;
//		double dbsize = size /(1024*1024) ;
//		String strsize = df.format(dbsize) ;
//		this.img_size = strsize+"MB";
		this.img_size = img_size ;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getParseDate() {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
		String date = sm.format(this.create_date) ;
		return date;
	}
	public void setParseDate(String parseDate) {
		this.parseDate = parseDate;
	}
	public String getUrl() {
		String url = "<img scr='"+this.path+"' width='30px' height='30px' alt=''/>" ;
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
