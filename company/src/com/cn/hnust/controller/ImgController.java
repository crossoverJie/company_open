package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Img;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.util.Page;

@Controller
@RequestMapping("/img")
public class ImgController {
	
	@Resource
	private IImgService imgService ;
	
	@RequestMapping("/turnToImgList")
	public String turoToImgList(){
		return "imgManger/imgList" ;
	}
	
	@RequestMapping("/getImgList")
	public void getImgList(Img img,int page,int rows,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		Page<Img> pages = imgService.findByParams(img, page, rows) ;
		String json = JSON.toJSONString(pages) ;
		response.getWriter().print(json) ;
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam(value="file")MultipartFile file, Img img,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		String fileName = file.getOriginalFilename() ;
		File targetFile = new File(path, fileName); 
		if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
		try {
			file.transferTo(targetFile) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		img.setPath("upload/"+fileName) ;
		DecimalFormat df = new DecimalFormat("######0.00");   
		double size = Double.parseDouble(file.getSize()+"") ;
		double dbsize = size /(1024*1024) ;
		String strsize = df.format(dbsize) ;
		img.setImg_size(strsize+"MB") ;
		img.setCreate_date(new Date()) ;
		imgService.insertSelective(img) ;
		return "imgManger/imgList" ; 
	}
	
	@RequestMapping("/delete")
	public void delete(String ids,String names ,HttpServletResponse response,HttpServletRequest request) throws IOException{
		String path = request.getSession().getServletContext().getRealPath("/"); 
		String[] str_ids = ids.split(",") ;
		String[] str_names = names.split(",") ;
		for(String name : str_names){
			File file = new File(path+"/"+name) ;
			if(file.exists()){
				file.delete() ;
			}
		}
		for(String id : str_ids){
			imgService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			response.getWriter().print("ture");
		}
	}
}
