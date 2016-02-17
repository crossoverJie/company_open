package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IPraiseDao;
import com.cn.hnust.pojo.Praise;
import com.cn.hnust.service.IPraiseService;
import com.cn.hnust.util.Page;
@Service("PraiseService")
public class PraiseServiceImpl implements IPraiseService {

	@Resource
	private IPraiseDao dao ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Praise record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(Praise record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public Praise selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public Page<Praise> findByParams(Praise u, int pageNo, int limit) {
		Page<Praise> page = new Page<Praise>() ;
		page.setPageNo(pageNo) ;
		page.setLimit(limit) ;
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		List<Praise> Praise = dao.findByParams(u,rowBound);
		page.setRows(Praise);
		int total = dao.findAllCount(u) ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int findAllCount(Praise ns) {
		return dao.findAllCount(ns);
	}

	@Override
	public List<Praise> findAll(Praise n) {
		return dao.findAll(n);
	}

	@Override
	public int deleteByCancelPraise(Praise praise) {
		// TODO Auto-generated method stub
		return dao.deleteByCancelPraise(praise);
	}

}
