package com.cn.hnust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.News;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private INewsService newsService ;
	
	@RequestMapping("/turnToNewsList")
	public String turnToNewsList(){
		return "news/newsList" ;
	}
	
	@RequestMapping("/getNewsList")
	public void getNewsList(News n,int page,int rows,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Page<News> pages = newsService.findByParams(n, page, rows) ;
		for(News ns : pages.getRows()){
			Date date = ns.getCreate_date() ;
			if(date != null){
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
				String dateStr = sm.format(date) ;
				ns.setParseDate(dateStr) ;
			}
		}
		String json = JSON.toJSONString(pages);
		response.getWriter().print(json) ;
	}
	
	@RequestMapping("/create")
	public void create(News n,HttpServletResponse response){
		n.setCreate_date(new Date()) ;
		try {
			newsService.insertSelective(n) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			newsService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			response.getWriter().print("ture");
		}
	}
	
	@RequestMapping("/edit")
	public void edit(News n ,HttpServletResponse response){
		try {
			newsService.updateByPrimaryKeySelective(n) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
	}
	
}
