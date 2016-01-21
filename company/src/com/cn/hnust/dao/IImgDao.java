package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.Img;

public interface IImgDao {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
    
    List<Img> findByParams(Img u,RowBounds rowBound) ;

	int findAllCount(Img i);
	List<Img> findAll(Img f) ;
	
	List<Img> findByIndex(Img i) ;
}
