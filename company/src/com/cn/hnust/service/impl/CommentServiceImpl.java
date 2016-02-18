package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ICommentDao;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.service.ICommentService;
import com.cn.hnust.util.Page;
@Service("CommentService")
public class CommentServiceImpl implements ICommentService {

	@Resource
	private ICommentDao dao ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Comment record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public Comment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public Page<Comment> findByParams(Comment u, int pageNo, int limit) {
		Page<Comment> page = new Page<Comment>() ;
		page.setPageNo(pageNo) ;
		page.setLimit(limit) ;
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		List<Comment> Comment = dao.findByParams(u,rowBound);
		page.setRows(Comment);
		int total = dao.findAllCount(u) ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int findAllCount(Comment ns) {
		return dao.findAllCount(ns);
	}

	@Override
	public List<Comment> findAll(Comment n) {
		return dao.findAll(n);
	}

	@Override
	public List<Comment> findHotTopic() {
		return dao.findHotTopic();
	}

}
