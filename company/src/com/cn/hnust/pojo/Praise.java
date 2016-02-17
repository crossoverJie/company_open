package com.cn.hnust.pojo;

import java.util.Date;

import com.cn.hnust.util.AbstractPojo;

/**
 * 
 * ClassName: Praise 
 * @Description: 点赞的实体类
 * @author crossoverJie
 * @date 2016年2月17日 上午11:57:23
 */
public class Praise extends AbstractPojo {

	private Integer id ;
	private String news_id ;
	private String user_id ;
	private Date praise_date ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getPraise_date() {
		return praise_date;
	}
	public void setPraise_date(Date praise_date) {
		this.praise_date = praise_date;
	}
}
