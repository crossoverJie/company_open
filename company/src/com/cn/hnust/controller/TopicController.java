package com.cn.hnust.controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.AbstractController;

@Controller
@RequestMapping("/")
public class TopicController extends AbstractController {

	@Resource
	private INewsService newsService ;
	
	@Resource
	private IUserService userService ;
	
	@Resource
	private IImgService imgService ;
	
	@Autowired
	private HttpSession session ;
	
	/**
	 * 
	 * @Description: 用于显示当前帖子的详情
	 * @param @param id
	 * @param @param session
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年1月29日  上午11:53:01
	 */
	@RequestMapping("/topic/{id}")
	public String showDetail(@PathVariable int id,HttpSession session,Model model){
		News news = newsService.selectByPrimaryKey(id) ;
		model.addAttribute("topic", news) ;
		String user_id = news.getUser_id() ;
		
		//帖子作者的详情
		User user = userService.getUserById(Integer.parseInt(user_id)) ;
		if(user != null){
			String img_id = user.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("topicuser", path) ;
			}
		}
		User currenUser = (User) session.getAttribute("user") ;
		if(currenUser != null){
			String img_id = currenUser.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("headimg", path) ;
			}
		}
		return "front/topic/topicDetail" ;
	}
	
	/**
	 * 
	 * @Description: 保存帖子
	 * @param @param n
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年1月29日  下午5:57:11
	 */
	@RequestMapping("/createTopic")
	public String createTopic(News n){
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
			User user = (User) session.getAttribute("user") ;
			if (user != null) {
				n.setUser_id(user.getId()+"");
			}
			if(!paths.equals("")){
				n.setImg_path(paths) ;
			}
			
			newsService.insertSelective(n) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return "redirect:index/turnToIndex/1" ;
	}
	
	
	@RequestMapping("/turnToCreateTopic")
	public String turnToCreateTopic(HttpSession session,Model model){
		User user = (User) session.getAttribute("user") ;
		if(user == null){
			return "redirect:index/turnToIndex/1" ;
		}else{
			//每个控制器都需要将当前用户的头像显示出来
			String img_id = user.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("headimg", path) ;
			}
			return "front/topic/createTopic" ;
		}
	}
}
