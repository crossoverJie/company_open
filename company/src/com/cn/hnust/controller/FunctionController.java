package com.cn.hnust.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.service.IFunctionService;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/function")
public class FunctionController {

	@Resource
	private IFunctionService functionService ;
	
	@RequestMapping("/turnTofunctionList")
	public String turnToFunctionList(){
		return "function/functionList" ;
	}
	
	@RequestMapping("/getFunctionList")
	public void getFunctionList(Function role,HttpServletResponse response,int page,int rows) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Page<Function> roles = functionService.findByParams(role, page, rows) ;
		String json = JSON.toJSONString(roles) ;
		response.getWriter().print(json) ;
	}
	
	
}
