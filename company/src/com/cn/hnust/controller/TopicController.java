package com.cn.hnust.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IUserService;

@Controller
@RequestMapping("/")
public class TopicController {

	@Resource
	private INewsService newsService ;
	
	@Resource
	private IUserService userService ;
	
	@Resource
	private IImgService imgService ;
	
	@RequestMapping("/topic/{id}")
	public String showDetail(@PathVariable int id,HttpSession session,Model model){
		News news = newsService.selectByPrimaryKey(id) ;
		model.addAttribute("topic", news) ;
		
		User user = (User)session.getAttribute("user") ;
		if(user != null){
			int userid = user.getId() ;
			User userById = userService.getUserById(userid) ;
			String img_id = userById.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("headimg", path) ;
			}
			
		}
		
		return "front/topic/topicDetail" ;
	}
}
