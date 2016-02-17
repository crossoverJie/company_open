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
	private String img_path ;//当条记录中图片的地址
	private String index_src ;//用于首页展示时候用的图片 一般是第一张
	private String user_id ;//创建文章的用户ID
	private String user_head_img ;//当前帖子的创建者的头像
	
	private String author ;//创建者的名称，用于datagrid的展示
	
	private String is_index ;//是否上首页滚动 1：上首页
	
	private String comment_count ;//当前帖子的回复数量
	
	
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
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
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
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getIndex_src() {
		return index_src;
	}
	public void setIndex_src(String index_src) {
		this.index_src = index_src;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_head_img() {
		return user_head_img;
	}
	public void setUser_head_img(String user_head_img) {
		this.user_head_img = user_head_img;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIs_index() {
		return is_index;
	}
	public void setIs_index(String is_index) {
		this.is_index = is_index;
	}
	public String getComment_count() {
		return comment_count;
	}
	public void setComment_count(String comment_count) {
		this.comment_count = comment_count;
	}
}
