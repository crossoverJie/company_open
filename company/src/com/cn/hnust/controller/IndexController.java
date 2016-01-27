package com.cn.hnust.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.Img;
import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.AbstractController;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {
	
	@Resource
	private IImgService imgService ;
	
	@Resource
	private INewsService newService ;
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/turnToIndex/{pageNum}")
	public String trunToIndex(@PathVariable int pageNum,Model model){
		Img img = new Img() ;
//		设置为1表示自查询上首页的图片
		img.setIs_index("1");
		List<Img> imgs = imgService.findByIndex(img) ;
		model.addAttribute("imgs", imgs) ;
		//查询最新的新闻信息。
		News ns = new News() ;
		super.setPageNum(pageNum) ;
		super.setRowCount(newService.findAllCount()) ;
		super.getIndex() ;
		ns.setStartIndex(super.getStartIndex()) ;
		ns.setEndIndex(super.getEndIndex()) ;
		List<News> news = newService.findAll(ns) ;
		model.addAttribute("news", news) ;
		model.addAttribute("currentpage", pageNum) ;
		
		return "../../index" ;
	}
	
	/**
	 * 
	 * @Description: 检查用户名或者是邮箱是否重复
	 * @param @param u
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-24  下午1:08:17
	 */
	@RequestMapping("/checkIsRepeat")
	public void checkIsRepeat(User u,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		response.setContentType("html/text") ;
		int count = userService.findAllCount(u) ;
		if(count ==0){
			response.getWriter().print("true") ;
		}else {
			response.getWriter().print("false") ;
		}
	}
	
	@RequestMapping("/register")
	public String register(User u,HttpServletRequest request) throws IOException{
		userService.createUser(u) ;
//		注册完之后直接登录
		request.getSession().setAttribute("user", u) ;
//		重定向到首页
		return "redirect:../index/turnToIndex/1" ;
	}
	
	@RequestMapping("/login")
	public void login(User u,HttpServletRequest request,HttpServletResponse response) throws IOException{
		User user = userService.findByLogin(u) ;
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
		if(user != null){
			request.getSession().setAttribute("user", user) ;
			response.getWriter().print("true") ;
//			return "redirect:../index/turnToIndex/1" ;
		}else {
		    response.getWriter().print("false") ;
		}
	}
	
	/**
	 * 
	 * @Description: 修改密码
	 * @param @param user
	 * @param @param session
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author chj
	 * @date 2016-1-26  下午7:41:56
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(User user,HttpSession session){
		userService.updateByPrimaryKeySelective(user) ;
		session.removeAttribute("user") ;
		return "redirect:../index/turnToIndex/1" ;
	}
	
	/**
	 * 
	 * @Description: 验证当前密码是否错误
	 * @param @param user
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-26  下午7:47:52
	 */
	@RequestMapping("/checkCurrentPwd")
	public void checkCurrentPwd(User user,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
	    int count = userService.findAllCount(user) ;
	    if(count ==0){
	    	response.getWriter().print("false") ;
	    }else {
	    	response.getWriter().print("true") ;
	    }
	}
	
	
}
