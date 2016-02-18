package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Comment;
import com.cn.hnust.util.Page;

public interface ICommentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    Page<Comment> findByParams(Comment u,int pageNo,int limit) ;

	int findAllCount(Comment ns);
	List<Comment> findAll(Comment f) ;
	List<Comment> findHotTopic() ;
}
