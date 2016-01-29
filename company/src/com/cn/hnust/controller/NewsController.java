package com.cn.hnust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.util.AbstractController;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/news")
public class NewsController extends AbstractController {

	@Resource
	private INewsService newsService ;
	
	@Resource
	private IImgService imgService ;
	
	
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
	public void create(News n,HttpServletResponse response,HttpSession session){
		n.setCreate_date(new Date()) ;
		try {
			
			String content = n.getContent() ;
			Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
			Matcher m = p.matcher(content);
			String paths = "" ;
			while(m.find()) {
				String path = m.group(1);
				paths +=path+",";
			}
			n.setImg_path(paths) ;
			User  user = (User) session.getAttribute("user") ;
			n.setUser_id(user.getId()+"");
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
			String content = n.getContent() ;
			Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
			Matcher m = p.matcher(content);
			String paths = "" ;
			while(m.find()) {
				String path = m.group(1);
				paths +=path+",";
			}
			n.setImg_path(paths) ;
			newsService.updateByPrimaryKeySelective(n) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
	}
	
}
