package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IRoleDao;
import com.cn.hnust.pojo.Role;
import com.cn.hnust.service.IRoleService;
import com.cn.hnust.util.Page;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	IRoleDao dao ;
	
	@Override
	public Role getRoleById(int id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int createRole(Role role) {
		return dao.insertSelective(role);
	}

	@Override
	public Page<Role> findByParams(Role role, int pageNo, int limit) {
		Page<Role> page = new Page<Role>();
		page.setPageNo(pageNo);
		page.setLimit(limit);
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		List<Role> roles = dao.findByParams(role,rowBound);
		page.setRows(roles);
		int total = dao.findAllCount() ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

}
