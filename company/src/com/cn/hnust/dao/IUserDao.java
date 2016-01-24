package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByLogin(User user) ;
    
    List<User> findByParams(User u,RowBounds rowBound) ;

	int findAllCount(User u);
}