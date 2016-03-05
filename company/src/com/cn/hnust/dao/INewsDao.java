package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.*;;

public interface INewsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    List<News> findByParams(News u,RowBounds rowBound) ;

	int findAllCount(News ns);
	List<News> findAll(News f) ;
	List<News> findHotUser() ;
}
