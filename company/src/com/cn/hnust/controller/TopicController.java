package com.cn.hnust.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.Img;
import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ICommentService;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.AbstractController;

@Controller
@RequestMapping("/")
public class TopicController extends AbstractController {

	@Resource
	private INewsService newsService ;
	
	@Resource
	private IUserService userService ;
	
	@Resource
	private IImgService imgService ;
	
	@Autowired
	private ICommentService commentService ;
	
	@Autowired
	private HttpSession session ;
	
	/**
	 * 
	 * @Description: 用于显示当前帖子的详情
	 * @param @param id
	 * @param @param session
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年1月29日  上午11:53:01
	 */
	@RequestMapping("/topic/{id}")
	public String showDetail(@PathVariable int id,HttpSession session,Model model){
		News news = newsService.selectByPrimaryKey(id) ;
		model.addAttribute("topic", news) ;
		String user_id = news.getUser_id() ;
		
		//帖子作者的详情
		User user = userService.getUserById(Integer.parseInt(user_id)) ;
		if(user != null){
			model.addAttribute("author", user) ;
			String img_id = user.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("topicuser", path) ;
			}
			News news_author = new News() ;
			news_author.setUser_id(user.getId()+"");
			super.setPageNum(1);//只显示第一页的数据，也就是只显示六条数据。
			super.setRowCount(newsService.findAllCount(news_author)) ;
			super.getIndex();
			news_author.setStartIndex(super.getStartIndex());
			news_author.setEndIndex(super.getEndIndex()); 
			List<News> all_author = newsService.findAll(news_author) ;//为了只显示最新的六条数据
			model.addAttribute("allauthor", all_author) ;//将当前这条帖子的作者的所有帖子都列展示出来。
			
		}
		//每个控制器都需要将当前用户的头像显示出来
		User currenUser = (User) session.getAttribute("user") ;
		if(currenUser != null){
			String img_id = currenUser.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("headimg", path) ;
			}
		}
		
		//以下是为了显示评论的
		Comment comment = new Comment() ;
		comment.setNews_id(id+"");
		super.setPageNum(2);//只显示两页的数据，也就是只显示12条数据。
		super.setRowCount(commentService.findAllCount(comment)) ;
		super.getIndex();
		comment.setStartIndex(super.getStartIndex());
		comment.setEndIndex(super.getEndIndex()); 
		List<Comment> comments = commentService.findAll(comment) ;
		for (Comment com : comments) {
			String com_user_id = com.getUser_id() ;
			User userById = userService.getUserById(Integer.parseInt(com_user_id));
			com.setUsername(userById.getUsername());
			Img img_user = imgService.selectByPrimaryKey(Integer.parseInt(userById.getImg_id())) ;
			String topic_user_path = img_user.getPath() ;//获得当前帖子的创建者的头像
			if(topic_user_path != null){
				com.setUser_head_img(topic_user_path);//设置当前帖子的创建者的头像
			}
			
		}
		model.addAttribute("comments", comments) ;
		
		return "front/topic/topicDetail" ;
	}
	
	/**
	 * 
	 * @Description: 保存帖子
	 * @param @param n
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年1月29日  下午5:57:11
	 */
	@RequestMapping("/createTopic")
	public String createTopic(News n){
		n.setCreate_date(new Date()) ;
		try {
			String content = n.getContent() ;
			//筛选出其中的图片地址。
			Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
			Matcher m = p.matcher(content);
			String paths = "" ;
			while(m.find()) {
				String path = m.group(1);
				paths +=path+",";
			}
			User user = (User) session.getAttribute("user") ;
			if (user != null) {
				n.setUser_id(user.getId()+"");
			}
			if(!paths.equals("")){
				n.setImg_path(paths) ;
			}
			n.setIs_index("0");//默认不是首页
			newsService.insertSelective(n) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return null ;
		}
		
		return "redirect:index/turnToIndex/1" ;
	}
	
	
	@RequestMapping("/turnToCreateTopic")
	public String turnToCreateTopic(HttpSession session,Model model){
		User user = (User) session.getAttribute("user") ;
		if(user == null){
			return "redirect:index/turnToIndex/1" ;
		}else{
			//每个控制器都需要将当前用户的头像显示出来
			String img_id = user.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("headimg", path) ;
			}
			return "front/topic/createTopic" ;
		}
	}
}