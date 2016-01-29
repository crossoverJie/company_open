package com.cn.hnust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Function;
import com.cn.hnust.service.IFunctionService;
import com.cn.hnust.util.Page;
import com.cn.hnust.util.TreeGridUtil;
/**
 * 
* <p>Title:FunctionController </p>
* <p>Description: 功能控制器</p>
* <p>Company: free</p> 
* @author crossoverJie
* @date 2016年1月29日 上午10:52:41
 */
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
	public void getFunctionList(Function function,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		List<TreeGridUtil> trees = new ArrayList<TreeGridUtil>() ;
		//只显示一级菜单
		function.setParent_id(-1) ;
		List<Function> functions = functionService.findAll(function);
		for(Function f:functions){
			TreeGridUtil tree = new TreeGridUtil() ;
			tree.setId(f.getId()) ;
			tree.setFunction_url(f.getFunction_url());
			tree.setName(f.getFunction_name());
			tree.setRemark(f.getRemark()) ;
			tree.setParent_id(f.getParent_id()) ;
//			tree.setState("closed") ;
			if(f.getParent_id() == -1){
				int parent_id = f.getId() ;
				Function parent = new Function() ;
				parent.setParent_id(parent_id) ;
				List<Function> lists = functionService.findAll(parent) ;
				List<TreeGridUtil> t2 = new ArrayList<TreeGridUtil>() ;
				for(Function f_son : lists){
					TreeGridUtil t_son = new TreeGridUtil() ;
					t_son.setId(f_son.getId());
					t_son.setFunction_url(f_son.getFunction_url()) ;
					t_son.setName(f_son.getFunction_name()) ;
					t_son.setRemark(f_son.getRemark());
					t_son.setParent_id(f_son.getParent_id()) ;
					t2.add(t_son) ;
				}
				tree.setChildren(t2) ;
			}
			trees.add(tree) ;
		}
		
		String json = JSON.toJSONString(trees) ;
		System.out.println(json);
		response.getWriter().print(json) ;
	}
	
	@RequestMapping("/create")
	public void create(Function function,HttpServletResponse response) throws IOException{
		response.setContentType("html/text") ;
		try {
			functionService.insertSelective(function) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			response.getWriter().print("false") ;
			e.printStackTrace() ;
		}
	}
	
	/**
	 * 
	 * @Description: 获得所有一级菜单
	 * @param    
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author chj
	 * @date 2016-1-12  下午1:21:43
	 */
	@RequestMapping("/getAllone")
	public void getAllone(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Function f = new Function() ;
		f.setParent_id(-1) ;
		List<Function> fs = functionService.findAll(f) ;
		String json = JSON.toJSONString(fs) ;
		response.getWriter().print(json) ;
		
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,String parent_id,HttpServletResponse response) throws IOException{
		String[] str_ids = ids.split(",") ;
		for(String id : str_ids){
			//当parent_id为-1时候 表示该节点为一级菜单 所以要删除下边所有的记录。
			if(parent_id.equals("-1")){
				//首先删除所有父节点为id的
				functionService.deleteByPrentId(Integer.parseInt(id)) ;
				
				//然后删除该父节点
				functionService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			}else{
				functionService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			}
			response.getWriter().print("true") ;
		}
	}
	@RequestMapping("/edit")
	public void edit(Function f,HttpServletResponse response) throws IOException{
		try {
			response.setContentType("html/text") ;
			functionService.updateByPrimaryKeySelective(f) ;
			response.getWriter().print("true") ;
		} catch (Exception e) {
			// TODO: handle exception
			response.getWriter().print("false") ;
			e.printStackTrace() ;
		}
	}
	
}
