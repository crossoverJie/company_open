package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Function;
import com.cn.hnust.util.Page;

public interface IFunctionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
    
    Page<Function> findByParams(Function u,int pageNo,int limit) ;

	int findAllCount();
	List<Function> findAll(Function f) ;
}
