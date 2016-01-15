package com.cn.hnust.pojo;
/**
 * 
 * ClassName: Role 
 * @Description: 角色实体
 * @author chj
 * @date 2016-1-7 下午4:11:03
 */
public class Role {

	private Integer id ;
	private String role_name ;
	private String remark ;
	private String function_id ;
	
	private String all_function_name;//角色的所有功能名称
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunction_id() {
		return function_id;
	}
	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}
	public String getAll_function_name() {
		return all_function_name;
	}
	public void setAll_function_name(String all_function_name) {
		this.all_function_name = all_function_name;
	}
}
