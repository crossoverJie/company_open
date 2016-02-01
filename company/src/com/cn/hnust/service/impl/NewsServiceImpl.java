package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.INewsDao;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.pojo.News;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.util.Page;
@Service("newsService")
public class NewsServiceImpl implements INewsService {

	@Resource
	private INewsDao dao ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(News record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(News record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public News selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(News record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(News record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public Page<News> findByParams(News u, int pageNo, int limit) {
		Page<News> page = new Page<News>() ;
		page.setPageNo(pageNo) ;
		page.setLimit(limit) ;
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		List<News> news = dao.findByParams(u,rowBound);
		page.setRows(news);
		int total = dao.findAllCount(u) ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int findAllCount(News ns) {
		return dao.findAllCount(ns);
	}

	@Override
	public List<News> findAll(News n) {
		return dao.findAll(n);
	}

}
