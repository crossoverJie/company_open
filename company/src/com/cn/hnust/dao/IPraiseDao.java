package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.*;;

public interface IPraiseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer id);
    
    List<Praise> findByParams(Praise u,RowBounds rowBound) ;

	int findAllCount(Praise ns);
	List<Praise> findAll(Praise f) ;
	
	int deleteByCancelPraise(Praise praise) ;
}
