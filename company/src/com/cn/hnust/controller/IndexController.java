package com.cn.hnust.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.Img;
import com.cn.hnust.service.IImgService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Resource
	private IImgService imgService ;
	
	@RequestMapping("/turnToIndex")
	public String trunToIndex(Model model){
		Img img = new Img() ;
//		设置为1表示自查询上首页的图片
		img.setIs_index("1");
		List<Img> imgs = imgService.findByIndex(img) ;
		for (int i = 0; i < imgs.size(); i++) {
			model.addAttribute("img"+i, imgs.get(i).getPath()) ;
		}
		
		return "../../index" ;
	}
}
