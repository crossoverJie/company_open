package com.cn.hnust.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.Img;
import com.cn.hnust.pojo.News;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Resource
	private IImgService imgService ;
	
	@Resource
	private INewsService newService ;
	
	@RequestMapping("/turnToIndex")
	public String trunToIndex(Model model){
		Img img = new Img() ;
//		设置为1表示自查询上首页的图片
		img.setIs_index("1");
		List<Img> imgs = imgService.findByIndex(img) ;
		for (int i = 0; i < imgs.size(); i++) {
			model.addAttribute("img"+i, imgs.get(i).getPath()) ;
			model.addAttribute("msg"+i, imgs.get(i).getRemark()) ;
		}
		
		//查询最新的新闻信息。
		News ns = new News() ;
		List<News> news = newService.findAll(ns) ;
		for (int i = 0; i < news.size(); i++) {
			model.addAttribute("news_title"+i, news.get(i).getTitle()) ;
			model.addAttribute("news_content"+i, news.get(i).getContent()) ;
		}
		
		return "../../index" ;
	}
}
