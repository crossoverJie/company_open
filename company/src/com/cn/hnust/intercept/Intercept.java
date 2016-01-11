package com.cn.hnust.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.hnust.pojo.User;

public class Intercept implements HandlerInterceptor {

	/**
	 * 第一次执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("user") ;
		String url = request.getRequestURI() ;
		System.out.println("当前URL------>  "+url);
		if(user != null){
			System.out.println("当前状态------>  通过 ");
			return true;
		}else {
			System.out.println("当前状态------>  拦截 ");
			response.sendRedirect("/company/login.jsp") ;
//			request.getRequestDispatcher("/login.jsp").forward(request, response);  
			return false ;
		}
	}

	
	
	/**
	 * 最后执行 用于关闭资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 渲染视图时执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

}
