package com.cn.hnust.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private IUserService userService;

	@RequestMapping("/check")
	public void login(User user, Model model,HttpServletResponse response,HttpServletRequest request) throws IOException{
		User u = userService.findByLogin(user) ;
		if(u== null){
			response.setContentType("html/text");
			response.getWriter().print("false") ;
		}else{
			Date date = new Date() ;
			u.setLast_date(date) ;
			userService.updateByPrimaryKeySelective(u) ;
			if(u != null){
				request.getSession().setAttribute("user", u) ;
				response.getWriter().print("true") ;
			}else{
				response.getWriter().print("false") ;
			}
		}
	}
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request,String type,Model model){
		request.getSession().removeAttribute("user") ;
//		0代表后台管理注销 1代表普通用户注销
		if(type.equals("0")){
			return "../../login" ;
		}else{
			return "redirect:../index/turnToIndex/1" ;
		}
	}
	

}
