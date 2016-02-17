package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Praise;
import com.cn.hnust.util.Page;

public interface IPraiseService {
    int deleteByPrimaryKey(Integer id);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer id);

    Page<Praise> findByParams(Praise u,int pageNo,int limit) ;

	int findAllCount(Praise ns);
	List<Praise> findAll(Praise f) ;
	
	int deleteByCancelPraise(Praise praise) ;
}
