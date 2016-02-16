package com.cn.hnust.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cn.hnust.util.AbstractPojo;

/**
 * 
 * ClassName: Comment 
 * @Description: 评论的实体类
 * @author crossoverJie
 * @date 2016年2月3日 下午4:58:10
 */
public class Comment extends AbstractPojo {
	private Integer id ;
	private String parent_id ;//如果是顶节点就为-1
	private Comment parent_comment ;//父节点的评论
	private String news_id ;
	private String news_title ;//用于显示当前评论的帖子标题
	private String user_id ;
	private Date create_date ;
	private String content ;
	
	private String user_head_img ;//当前评论人的头像
	
	private String dateStr ;//用于格式化日期
	private String username ;//用于显示用户名 没有在数据库中存储
	
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		
		this.create_date = create_date;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		String date = sm.format(create_date) ;
		this.setDateStr(date) ;
		
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_head_img() {
		return user_head_img;
	}
	public void setUser_head_img(String user_head_img) {
		this.user_head_img = user_head_img;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Comment getParent_comment() {
		return parent_comment;
	}
	public void setParent_comment(Comment parent_comment) {
		this.parent_comment = parent_comment;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
}
