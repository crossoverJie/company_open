package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.cn.hnust.pojo.*;;

public interface ICommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> findByParams(Comment u,RowBounds rowBound) ;

	int findAllCount(Comment ns);
	List<Comment> findAll(Comment f) ;
	
}
