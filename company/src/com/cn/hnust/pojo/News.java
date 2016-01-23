package com.cn.hnust.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cn.hnust.util.AbstractPojo;

public class News extends AbstractPojo{
	private Integer id ;
	private String title ;
	private String content ;
	private Date create_date ;
	private String dateStr ;
	
	private String parseDate ;//用于格式化日期
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
		String date = sm.format(create_date) ;
		this.setDateStr(date) ;
	}
	public String getParseDate() {
		return parseDate;
	}
	public void setParseDate(String parseDate) {
		this.parseDate = parseDate;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
