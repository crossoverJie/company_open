package com.cn.hnust.pojo;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String realname ;
    private String role_id ;
    private String remark ;
    private Date last_date ;
    /**用于显示格式化的日期**/
    private String parsedate ;
    
    /**角色名称**/
    private String rolename ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getParsedate() {
		return parsedate;
	}

	public void setParsedate(String parsedate) {
		this.parsedate = parsedate;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}