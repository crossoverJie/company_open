package com.cn.hnust.pojo;
/**
 * 
 * ClassName: Role 
 * @Description: 角色实体
 * @author chj
 * @date 2016-1-7 下午4:11:03
 */
public class Role {

	private int id ;
	private String role_name ;
	private String remark ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
}
