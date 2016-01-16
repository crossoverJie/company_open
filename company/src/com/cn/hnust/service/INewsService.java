package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.News;
import com.cn.hnust.util.Page;

public interface INewsService {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    Page<News> findByParams(News u,int pageNo,int limit) ;

	int findAllCount();
	List<News> findAll(News f) ;
	
}
