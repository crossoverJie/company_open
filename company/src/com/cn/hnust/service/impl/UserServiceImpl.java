package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.Page;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int createUser(User user) {
		return this.userDao.insertSelective(user) ;
	}

	@Override
	public User findByLogin(User user) {
		return userDao.findByLogin(user) ;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<User> findByParams(User u,int pageNo,int limit) {
		Page<User> page = new Page<User>();
		page.setPageNo(pageNo);
		page.setLimit(limit);
		
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		
		List<User> users = userDao.findByParams(u,rowBound);
		page.setRows(users);
		int total = userDao.findAllCount(u) ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userDao.deleteByPrimaryKey(id) ;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int findAllCount(User u) {
		// TODO Auto-generated method stub
		return userDao.findAllCount(u) ;
	}
	
}
