package com.cn.hnust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Role;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IRoleService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService ;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	  
	@RequestMapping("/showUser/{id}")
	public String showUser(@PathVariable int id, HttpServletRequest request) {
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return "showUser";
	}
	@RequestMapping("/turnToIndex")
	public String turnToIndex(){
		return "index" ;
	}
	@RequestMapping("/turnToUserList")
	public String turnToUserList(){
		return "user/userList" ;
	}
	@RequestMapping("/getUserList")
	public void getUserList(@ModelAttribute User user,HttpServletResponse response ,int page,int rows){
		response.setCharacterEncoding("utf-8");
		Page<User> users = userService.findByParams(user,page,rows) ;
		for(User u :users.getRows()){
			//将角色ID转换为角色名称
			String role_id = u.getRole_id() ;
			if(role_id != null){
				Role role = roleService.selectByPrimaryKey(Integer.parseInt(role_id)) ;
				u.setRolename(role.getRole_name()) ;
			}
			Date date = u.getLast_date() ;
			if(date != null){
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd") ;
				String strDate = sm.format(date) ;
				u.setParsedate(strDate) ;
			}
		}
		
		String json = JSON.toJSONString(users) ;
		try {
			response.getWriter().print(json) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/getAllRoles")
	public void getAllRoles(HttpServletResponse response){
		response.setCharacterEncoding("utf-8") ;
		List<Role> roles = roleService.findAll() ;
		String json = JSON.toJSONString(roles) ;
		try {
			response.getWriter().print(json) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @Description: 新建用户
	 * @param @param user
	 * @param @param request   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author chj
	 * @date 2016-1-2  下午11:06:27
	 */
	@RequestMapping("/create")
	public void createUser(User user ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			userService.createUser(user) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			response.getWriter().print("false") ;
		}
	}
	@RequestMapping("/delete")
	public void delete(String ids,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			userService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			response.getWriter().print("true") ;
		}
	}
	@RequestMapping("/edit") 
	public void edit(User user,HttpServletResponse response){
		try {
			userService.updateByPrimaryKeySelective(user) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession() ;
		session.removeAttribute("user") ;
		return "redirect:../login.jsp" ;
	}
}
