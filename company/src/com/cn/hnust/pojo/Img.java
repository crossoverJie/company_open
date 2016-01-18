package com.cn.hnust.pojo;

import java.util.Date;

public class Img {
	private Integer id ;
	private String name ; 
	private String path ;
	private String img_size ;
	private Date date ;
	
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
		this.img_size = img_size;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
