package com.cn.hnust.controller;

import java.io.IOException;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Role;
import com.cn.hnust.service.IRoleService;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private IRoleService roleService ;
	
	@RequestMapping("/turnToRoleList")
	public String turnToRoleIndex(){
		return "role/roleList" ;
	}
	
	@RequestMapping("/getRoleList")
	public void getRoleList(Role role,HttpServletResponse response,int page,int rows) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Page<Role> roles = roleService.findByParams(role, page, rows) ;
		String json = JSON.toJSONString(roles) ;
		response.getWriter().print(json) ;
	}
	@RequestMapping("/create")
	public void create(Role role,HttpServletResponse response) throws IOException{
		try {
			roleService.createRole(role) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			response.getWriter().print("false") ;
		}
		
	}
	
}
