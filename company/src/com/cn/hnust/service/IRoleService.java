package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Role;
import com.cn.hnust.util.Page;

public interface IRoleService {
	public Role getRoleById(int userId);
	public int createRole(Role role) ;
	public Page<Role> findByParams(Role role,int pageNo,int limit) ;
	int deleteByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Role record);
	List<Role> findAll() ;
	
	Role selectByPrimaryKey(Integer id);
}
