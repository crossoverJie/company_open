package com.cn.hnust.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.Img;
import com.cn.hnust.pojo.News;
import com.cn.hnust.pojo.Praise;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ICommentService;
import com.cn.hnust.service.IImgService;
import com.cn.hnust.service.INewsService;
import com.cn.hnust.service.IPraiseService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.impl.PraiseServiceImpl;
import com.cn.hnust.util.AbstractController;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

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
	private IPraiseService praiseService ;
	
	@Autowired
	private HttpSession session ;
	
	/**
	 * 
	 * @Description: 显示查看用户的详细信息
	 * @param @param id
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月10日  下午10:16:09
	 */
	@RequestMapping("/frontuser/{id}")
	public String showUserDetail(@PathVariable int id,Model model){
		User user = userService.getUserById(id) ;
		if(user != null){
			
			model.addAttribute("frontuser", user) ;
			
			String img_id = user.getImg_id() ;
			if(img_id != null){
				String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
				model.addAttribute("frontuserheading", path) ;
			}
			
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
		//用于显示发帖详情和发帖数量
		News ns = new News() ;
		ns.setUser_id(id+"");
		List<News> list_currentUserTopicCount = newsService.findAll(ns) ;
		if(list_currentUserTopicCount != null){
			model.addAttribute("list_currentUserTopicCount", list_currentUserTopicCount) ;
			int currentUserTopicCount = list_currentUserTopicCount.size() ;
			model.addAttribute("currentUserTopicCount", currentUserTopicCount) ;
		}
		
		Comment com = new Comment() ;
		com.setUser_id(id+"");
		List<Comment> list_currentUserComment = commentService.findAll(com) ;
		for(Comment ct : list_currentUserComment){
			//获取当前评论的帖子标题
			String news_id = ct.getNews_id() ;
			News comment_news = newsService.selectByPrimaryKey(Integer.parseInt(news_id)) ;
			if (comment_news != null) {
				ct.setNews_title(comment_news.getTitle());
			}else{
				ct.setNews_title("帖子已被删除！") ;
			}
		}
		//显示评论数量
		if(list_currentUserComment != null){
			model.addAttribute("list_currentUserComment", list_currentUserComment) ;
			int currentUserCommentCount = list_currentUserComment.size() ;
			model.addAttribute("currentUserCommentCount", currentUserCommentCount) ;
			
		}
		
		List<News> star_news = new ArrayList<News>();
		String star_id = user.getStar_news_id() ;
		if(star_id != null){
			String star_ids[] = star_id.split(",") ;
			for(String news_id : star_ids){
				News news = newsService.selectByPrimaryKey(Integer.parseInt(news_id)) ;
				star_news.add(news);
			}
		}
		model.addAttribute("star_news", star_news) ;
		model.addAttribute("currentUserStarNewsCount", star_news.size()) ;
		
		return "front/user/userDetail" ;
	}
	
	
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
	public String showDetail(@PathVariable int id,String pageNum,HttpSession session,Model model){
		News news = newsService.selectByPrimaryKey(id) ;
		model.addAttribute("topic", news) ;
		if(news != null){
			
			String user_id = news.getUser_id() ;
			User currenUser = (User) session.getAttribute("user") ;
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
				super.setPageNum(1);//只显示当前用户的其他帖子      第一页的数据，也就是只显示六条数据。
				super.setRowCount(newsService.findAllCount(news_author)) ;
				super.getIndex();
				news_author.setStartIndex(super.getStartIndex());
				news_author.setEndIndex(super.getEndIndex()); 
				List<News> all_author = newsService.findAll(news_author) ;//为了只显示最新的六条数据
				model.addAttribute("allauthor", all_author) ;//将当前这条帖子的作者的所有帖子都列展示出来。
				
			}
			int is_star = 0 ;//0默认为没有收藏
			//每个控制器都需要将当前用户的头像显示出来
			if(currenUser != null){
				User userById = userService.getUserById(currenUser.getId()) ;
				String img_id = userById.getImg_id() ;
				if(img_id != null){
					String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath() ;
					model.addAttribute("headimg", path) ;
				}
				
				//查询当前用户是否点赞
				String curentuser_user_id = userById.getId()+"" ;
				Praise praise = new Praise() ;
				praise.setNews_id(id+"");
				praise.setUser_id(curentuser_user_id);
				int praise_count = praiseService.findAllCount(praise) ;
				model.addAttribute("praise_count", praise_count);
				
				//判断当前用户对当前帖子是否有收藏
				
				String star_id = userById.getStar_news_id() ;
				if(star_id == null){
					is_star= 0 ;
				}else{
					String news_id[] = star_id.split(",") ;
					for(String star_news_id : news_id ){
						if(String.valueOf(id).equals(star_news_id.trim())){
							is_star = 1;
						}
					}
				}
			}
			model.addAttribute("is_star", is_star);
			
			//以下是为了显示评论的
			Comment comment = new Comment() ;
			comment.setNews_id(id+"");
			super.setRowCount(commentService.findAllCount(comment)) ;
			if(pageNum == null){
				super.setPageNum(1);//只显示两页的数据，也就是只显示12条数据。
				model.addAttribute("currentpage", 1) ;
			}else{
				super.setPageNum(Integer.parseInt(pageNum));
				model.addAttribute("currentpage", pageNum) ;
			}
			
			super.getIndex();
			comment.setStartIndex(super.getStartIndex());
			comment.setEndIndex(super.getEndIndex()); 
			List<Comment> comments = commentService.findAll(comment) ;
			for (Comment com : comments) {
				String com_user_id = com.getUser_id() ;
				User userById = userService.getUserById(Integer.parseInt(com_user_id));
				if(userById != null){
					
					com.setUsername(userById.getUsername());
					String img_id = userById.getImg_id() ;
					if(img_id != null){
						Img img_user = imgService.selectByPrimaryKey(Integer.parseInt(userById.getImg_id())) ;
						String topic_user_path = img_user.getPath() ;//获得当前帖子的创建者的头像
						if(topic_user_path != null){
							com.setUser_head_img(topic_user_path);//设置当前帖子的创建者的头像
						}
					}else{
						//如果注册用户没有上传图片
						
					}
				}
				String parent_id = com.getParent_id() ;
				if(!parent_id.equals("-1")){
					Comment comment_parent = commentService.selectByPrimaryKey(Integer.parseInt(parent_id)) ;
					com.setParent_comment(comment_parent);
					
					
					String comment_parent_user_id = comment_parent.getUser_id() ;
					User parent_user = userService.getUserById(Integer.parseInt(comment_parent_user_id));
					comment_parent.setUsername(parent_user.getUsername());
					String parent_img_id = parent_user.getImg_id() ;
					if(parent_img_id != null){
						Img img_user = imgService.selectByPrimaryKey(Integer.parseInt(parent_user.getImg_id())) ;
						String topic_user_path = img_user.getPath() ;//获得当前帖子的创建者的头像
						if(topic_user_path != null){
							comment_parent.setUser_head_img(topic_user_path);//设置当前帖子的创建者的头像
						}
					}
				}
				
				
			}
			int count = super.getRowCount() ;
			//当评论超过六条的时候才显示分页
			if(count >6){
				model.addAttribute("commentcount", count) ;
			}
			model.addAttribute("comments", comments) ;
			model.addAttribute("totalpage", super.getTotalPage()) ;
			
			//显示有多少当前帖子点过赞
			Praise onPraise = new Praise() ;
			onPraise.setNews_id(id+"");
			int onPraiseCount = praiseService.findAllCount(onPraise) ;
			model.addAttribute("onPraiseCount", onPraiseCount) ;
		}
		
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
	
	/**
	 * 
	 * @Description: 跳转到新建帖子界面
	 * @param @param session
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月17日  下午12:09:49
	 */
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
	
	/**
	 * 
	 * @Description: 收藏帖子
	 * @param @param news_id
	 * @param @param response   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月28日  下午8:00:20
	 */
	@RequestMapping("/onStar")
	public void onStar(String news_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		User user = (User) session.getAttribute("user") ;
		if(user!= null){
			user = userService.getUserById(user.getId()) ;
			String star_news_id = user.getStar_news_id() ;
			if(star_news_id == null){
				user.setSex(null);
				String star_id = "" ;
				star_id = news_id +"," ;
				user.setStar_news_id(star_id); 
				userService.updateByPrimaryKeySelective(user) ;
				response.getWriter().print("true");
			}else{
				user.setSex(null);
				String newsId = star_news_id + news_id +",";
				user.setStar_news_id(newsId); 
				userService.updateByPrimaryKeySelective(user) ;
				response.getWriter().print("true");
			}
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 
	 * @Description: 取消收藏
	 * @param @param news_id
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月28日  下午8:14:08
	 */
	@RequestMapping("/cancleStar")
	public void cancleStar(String news_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		User user = (User) session.getAttribute("user") ;
		if(user!= null){
			user = userService.getUserById(user.getId()) ;
			String star_news_id = user.getStar_news_id() ;
			if(star_news_id  != null){
				user.setSex(null);
				star_news_id = star_news_id.replace(news_id+",", "");
				user.setStar_news_id(star_news_id);
				userService.updateByPrimaryKeySelective(user) ;
				response.getWriter().print("true");
			}
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 
	 * @Description: 点赞
	 * @param @param news_id
	 * @param @param response   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月17日  下午12:15:16
	 */
	@RequestMapping("/onPraise")
	public void onPraise(String news_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		User user = (User) session.getAttribute("user") ;
		if(user!= null){
			Praise praise = new Praise() ;
			praise.setNews_id(news_id) ;
			praise.setUser_id(user.getId()+"");
			praise.setPraise_date(new Date()) ;
			praiseService.insertSelective(praise) ;
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
		
	}
	
	/**
	 * 
	 * @Description: 取消点赞
	 * @param @param news_id
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月17日  下午2:27:27
	 */
	@RequestMapping("/cancelPraise")
	public void cancelPraise(String news_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		User user = (User) session.getAttribute("user") ;
		if(user!= null){
			Praise praise = new Praise() ;
			praise.setNews_id(news_id) ;
			praise.setUser_id(user.getId()+"");
			praise.setPraise_date(new Date()) ;
			praiseService.deleteByCancelPraise(praise) ;
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 
	 * @Description: 获取当前帖子的点赞数量
	 * @param @param news_id
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年2月17日  下午2:43:15
	 */
	@RequestMapping("/getPraiseCount")
	public void getPraiseCount(String news_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		Praise onPraise = new Praise() ;
		onPraise.setNews_id(news_id);
		int onPraiseCount = praiseService.findAllCount(onPraise) ;
		response.getWriter().print(onPraiseCount);
	}
	
}
