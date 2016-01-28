package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
		img.setIs_index("0") ;//默认为 不是首页置顶
		imgService.insertSelective(img) ;
		return "imgManger/imgList" ; 
	}
	
	/**
	 * 
	 * @Description: 用于ckeditor的上传图片
	 * @param @param upload
	 * @param @param img
	 * @param @param request   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author chj
	 * @date 2016-1-23  下午2:53:33
	 */
	@RequestMapping("/uploadImgByCkeditor")
	public void uploadImgByCkeditor(@RequestParam(value="upload")MultipartFile upload ,Img img,HttpServletRequest request,
			HttpServletResponse response,
			String CKEditorFuncNum
			) throws IOException{
		response.setCharacterEncoding("utf-8") ;
		PrintWriter out = response.getWriter() ;
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		String fileName = upload.getOriginalFilename() ;
		File targetFile = new File(path, fileName); 
		if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
		try {
			upload.transferTo(targetFile) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		if(fileName != null){
			int index = fileName.indexOf(".") ;
			String hzName = fileName.substring(index+1) ;
			hzName = hzName.toLowerCase() ;
			if(hzName.equals("jpg") || hzName.equals("png") || hzName.equals("gif")){
				img.setPath("upload/"+fileName) ;
				DecimalFormat df = new DecimalFormat("######0.00");   
				double size = Double.parseDouble(upload.getSize()+"") ;
				double dbsize = size /(1024*1024) ;
				String strsize = df.format(dbsize) ;
				img.setName("uploadByCkeditor") ;
				img.setImg_size(strsize+"MB") ;
				img.setCreate_date(new Date()) ;
				img.setIs_index("0") ;//默认为 不是首页置顶
				imgService.insertSelective(img) ;
				out.println("<script type=\"text/javascript\">");  
				out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
						+ ",'" + request.getContextPath() + "/upload/" + fileName + "','')");  
				out.println("</script>"); 
				
			}else{
				out.println("<script type=\"text/javascript\">");  
				out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
				        + ",''," + "'只能上传jpg、png、gif类型的图片');");  
				out.println("</script>"); 
			}
			
			
		}else{
			out.println("<script type=\"text/javascript\">");  
			out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
			        + ",''," + "'请上传图片');");  
			out.println("</script>");  

		}

	}
	
	@RequestMapping("/delete")
	public void delete(String ids,String names ,HttpServletResponse response,HttpServletRequest request) throws IOException{
		String path = request.getSession().getServletContext().getRealPath("/"); 
		String[] str_ids = ids.split(",") ;
		String[] str_names = names.split(",") ;
		for(String name : str_names){
			File file = new File(path+"/"+name) ;
			if(file.exists()){
				try {
					file.delete() ;
					
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}
		for(String id : str_ids){
			imgService.deleteByPrimaryKey(Integer.parseInt(id)) ;
			response.getWriter().print("ture");
		}
	}
	
	/**
	 * 
	 * @Description: 将图片置顶到首页
	 * @param @param ids
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-28  下午2:38:59
	 */
	@RequestMapping("/upToIndex")
	public void upToIndex(String ids,HttpServletResponse response){
		String str_id[] = ids.split(",");
		for (String str : str_id) {
			Img img = new Img() ;
			img.setId(Integer.parseInt(str)) ;
			img.setIs_index("1") ;
			imgService.updateByPrimaryKeySelective(img) ;
		}
		try {
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	/**
	 * 
	 * @Description: 将图片取消置顶
	 * @param @param ids
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author chj
	 * @date 2016-1-28  下午2:39:15
	 */
	@RequestMapping("/downToIndex")
	public void downToIndex(String ids,HttpServletResponse response){
		String str_id[] = ids.split(",");
		for (String str : str_id) {
			Img img = new Img() ;
			img.setId(Integer.parseInt(str)) ;
			img.setIs_index("0") ;
			imgService.updateByPrimaryKeySelective(img) ;
		}
		try {
			response.getWriter().print("true") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	
	
	
}
