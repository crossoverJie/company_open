package com.cn.hnust.intercept;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.hnust.pojo.Function;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IFunctionService;
import com.cn.hnust.service.IRoleService;

public class Intercept implements HandlerInterceptor {

	@Resource
	private IFunctionService functionService ;
	
	@Resource
	private IRoleService roleService ;
	/**
	 * 第一次执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("user") ;
		String url = request.getRequestURI() ;
		url = url.substring(9) ;
		System.out.println("当前URL------>  "+url);
		if(user != null){
			String role_id = user.getRole_id() ;
			if(role_id != null){
				//说拥有的所有权限ID
				String function_id = roleService.selectByPrimaryKey(Integer.parseInt(role_id)).getFunction_id() ;
				Function function = new Function() ;
				function.setFunction_url(url) ;
				List<Function> list = functionService.findAll(function) ;
				if(list.size() <=0){
					return true ;
				}else{
					Function f = functionService.findAll(function).get(0) ;
					String currenId = f.getId()+"" ;
					if(function_id.indexOf(currenId) == -1){
						response.sendRedirect("/company/notfound.jsp") ;
						response.getWriter().print("false") ;
						return false ;
					}else {
						return true ;
					}
					
				}
			}
			
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
