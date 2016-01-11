package com.cn.hnust.pojo;

public class Function {

	private Integer id ;
	private Integer parent_id ;
	private String function_name ;
	private String funtion_url ;
	private String remark ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getFuntion_url() {
		return funtion_url;
	}
	public void setFuntion_url(String funtion_url) {
		this.funtion_url = funtion_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
}
