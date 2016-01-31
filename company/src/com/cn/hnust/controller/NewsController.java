package com.cn.hnust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Img;
import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.AbstractController;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/news")
public class NewsController extends AbstractController {

	@Resource
	private INewsService newsService ;
	
	@Resource
	private IImgService imgService ;
	
	@Autowired
	private IUserService userService ;
	
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
			String user_id = ns.getUser_id() ;
			if(user_id != null){
				User user = userService.getUserById(Integer.parseInt(user_id)) ;
				if(user != null){
					ns.setAuthor(user.getUsername());
				}
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
			n.setIs_index("0");//默认不是首页
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
	
	
	/**
	 * 
	 * @Description: 将文章置顶到首页
	 * @param @param ids
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-28  下午2:38:59
	 */
	@RequestMapping("/upToIndex")
	public void upToIndex(String ids,HttpServletResponse response){
		String str_id[] = ids.split(",");
		for (String str : str_id) {
			News ns = new News() ;
			ns.setId(Integer.parseInt(str));
			ns.setIs_index("1");
			newsService.updateByPrimaryKeySelective(ns) ;
		}
		try {
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	/**
	 * 
	 * @Description: 将文章取消置顶
	 * @param @param ids
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-28  下午2:39:15
	 */
	@RequestMapping("/downToIndex")
	public void downToIndex(String ids,HttpServletResponse response){
		String str_id[] = ids.split(",");
		for (String str : str_id) {
			News ns = new News() ;
			ns.setId(Integer.parseInt(str));
			ns.setIs_index("0") ;
			newsService.updateByPrimaryKeySelective(ns) ;
		}
		try {
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}	
	
	
}
