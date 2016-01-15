package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

	@RequestMapping("/turnToNewsList")
	public String turnToNewsList(){
		return "news/newsList" ;
	}
	
	@RequestMapping("/getNewsList")
	public void getNewsList(int page,int rows){
	}
	
}
