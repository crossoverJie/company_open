package com.cn.hnust.pojo;

import java.util.Date;

public class News {
	private Integer id ;
	private String title ;
	private String content ;
	private Date create_date ;
	
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
	}
	public String getParseDate() {
		return parseDate;
	}
	public void setParseDate(String parseDate) {
		this.parseDate = parseDate;
	}
}
