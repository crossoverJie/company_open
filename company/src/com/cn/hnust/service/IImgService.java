package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Img;
import com.cn.hnust.util.Page;

public interface IImgService {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
    
    Page<Img> findByParams(Img u,int pageNo,int limit) ;

	int findAllCount(Img i);
	List<Img> findAll(Img f) ;
	List<Img> findByIndex(Img i) ;
}
