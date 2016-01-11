package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.Role;

public interface IRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> findByParams(Role u,RowBounds rowBound) ;

	int findAllCount();
	List<Role> findAll() ;
}
