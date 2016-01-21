package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IImgDao;
import com.cn.hnust.pojo.Img;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.util.Page;

@Service("imgService")
public class ImgServiceImpl implements IImgService {

	@Resource
	private IImgDao dao ;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Img record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public Img selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Img record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Img record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public Page<Img> findByParams(Img i, int pageNo, int limit) {
		Page<Img> page = new Page<Img>() ;
		page.setPageNo(pageNo) ;
		page.setLimit(limit) ;
		int offset = page.getOffsets();
		RowBounds rowBound = new RowBounds(offset, limit);
		List<Img> news = dao.findByParams(i,rowBound);
		page.setRows(news);
		int total = dao.findAllCount(i) ;
		page.setTotal(total) ;
		if(offset >= page.getTotal()){
			page.setPageNo(page.getTotalPages());
		}
		return page ;
	}

	@Override
	public int findAllCount(Img i) {
		return dao.findAllCount(i);
	}

	@Override
	public List<Img> findAll(Img n) {
		return dao.findAll(n);
	}
	
	@Override
	public List<Img> findByIndex(Img i){
		return dao.findByIndex(i) ;
	}
}
