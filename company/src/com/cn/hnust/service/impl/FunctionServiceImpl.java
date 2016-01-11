package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IFunctionDao;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.service.IFunctionService;
import com.cn.hnust.util.Page;
@Service("functionService")
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	IFunctionDao dao ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Function record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}

	@Override
	public int insertSelective(Function record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public Function selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Function record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Function record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public Page<Function> findByParams(Function u,int pageNo,int limit) {
		Page<Function> page = new Page<Function>();
		page.setPageNo(pageNo);
		page.setLimit(limit);
		
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		
		List<Function> users = dao.findByParams(u,rowBound);
		page.setRows(users);
		int total = dao.findAllCount() ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int findAllCount() {
		// TODO Auto-generated method stub
		return dao.findAllCount();
	}

	@Override
	public List<Function> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
