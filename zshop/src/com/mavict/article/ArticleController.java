package com.mavict.article;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mavict.PageInfo;
import com.mavict.article.category.ArticleCategory;
import com.mavict.article.category.ArticleCategoryService;
import com.mavict.article.image.ArticleImage;
import com.mavict.article.image.ArticleImageService;
import com.mavict.utils.FileOperateUtils;
import com.mavict.utils.ImageUtils;
import com.mavict.utils.TimeUtils;

/**
 * Controller - Article
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	
	@Resource(name = "articleImageServiceImpl")
	private ArticleImageService articleImageService;
	
	@Resource(name = "solrServerCore0")
	private SolrServer solrServer;
	
	private static String SP = File.separator;
	
	/** 添加文章  */
	@RequestMapping("/add")
	public String add(ModelMap model,HttpSession session){
		model.addAttribute("articleCategoryTree", articleCategoryService.getTreeService());
		return "/admin/article/add";
	}
	
	/** 管理文章 - 所有分类  */
	@RequestMapping("/manage")
	public String manage(ModelMap model){
		model.addAttribute("articleCategoryTree", articleCategoryService.getTreeService());
		return "/admin/article/manage";
	}
	
	/** 管理文章 - 具体分类下文章列表  */
	@RequestMapping("/manage/{categoryId}")
	public String manageContent(@PathVariable Long categoryId,@RequestParam(value = "pageNumber",required = false) Integer pageNumber,ModelMap model,PageInfo pageInfo){
		if (pageNumber == null) {
			pageNumber = 1;
		}
		pageInfo.setPageNumber(pageNumber);
		model.addAttribute("pagedContent", articleService.getPagedContentService(pageInfo, "articleCategoryId", categoryId,"createDate","desc"));
		model.addAttribute("pageUrl", "/admin/article/manage/"+categoryId);
		return "/admin/article/list";
	}	
	
	
	/** 保存文章 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpSession session,RedirectAttributes redirectAttributes,Article article,String articleCategoryIdAndType,String isTop){
		Integer articleCategoryId = Integer.valueOf(articleCategoryIdAndType.split("and")[0]);	
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setId(articleCategoryId);
		
		article.setArticleCategory(articleCategory);
		
		/* 设置置顶，给发布时间加1000年 */
		Date date = new Date();
		if ("true".equals(isTop)) { date = TimeUtils.add1000(date); }
		article.setCreateDate(date);
		
		articleService.saveService(article); 	// 保存实体
	
		/* 存在保存上传图片的session - 图片操作*/
		if (null != session.getAttribute("webuploader_article")) {
			List<String> imageUrl = (List<String>) session.getAttribute("webuploader_article");
			for (String url : imageUrl) {
				ArticleImage articleImage = new ArticleImage();
				articleImage.setArticle(article);
				articleImage.setUrl(url);
				articleImageService.saveService(articleImage);
				articleImage.setOrders(Integer.valueOf(String.valueOf(articleImage.getId())));
				articleImageService.updateService(articleImage);
			}
			session.setAttribute("webuploader_article", null);
		}
		
		/* 提交到solr索引库*/
		try {
			solrServer.addBean(article);
			solrServer.commit();
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
		
		redirectAttributes.addFlashAttribute("info", "添加文章成功！");
		return "redirect:/admin/article/add";
	}	
	
	/** 编辑文章  */
	@RequestMapping("/edit/{articleId}")
	public String edit(@PathVariable Integer articleId,ModelMap model){
		Article article = articleService.getService(articleId);
		model.addAttribute("article", article);
		model.addAttribute("articleCategoryTree", articleCategoryService.getTreeService());
		/* 包含上传图片 */
		List<ArticleImage> articleImages = articleImageService.getArticleImageService(article);
		if (articleImages.size() != 0) {
			model.addAttribute("articleImages", articleImages);
		}
		return "/admin/article/edit";
	}
	
	/** 更新文章  */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes,Long id,Article article,String articleCategoryIdAndType,String newDate,String isTop,HttpSession session) throws ParseException{	
		Integer articleCategoryId = Integer.valueOf(articleCategoryIdAndType.split("and")[0]);	
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setId(articleCategoryId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse(newDate);
		
		/* 置顶 则时间增加1000年*/
		if ("true".equals(isTop)) {
			date = TimeUtils.add1000(date);
		}
		article.setCreateDate(date);
		article.setArticleCategory(articleCategory);
		
		articleService.updateService(article);
		
		/* 存在保存上传图片的session - 图片操作*/
		if (null != session.getAttribute("webuploader_article")) {
			List<String> imageUrl = (List<String>) session.getAttribute("webuploader_article");
			for (String url : imageUrl) {
				ArticleImage articleImage = new ArticleImage();
				articleImage.setArticle(article);
				articleImage.setUrl(url);
				articleImageService.saveService(articleImage);
				articleImage.setOrders(Integer.valueOf(String.valueOf(articleImage.getId())));
				articleImageService.updateService(articleImage);
			}
			session.setAttribute("webuploader_article", null);
		}
		
		/* 提交到solr索引库 */
		try {
			solrServer.addBean(article);
			solrServer.commit();
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
		
		redirectAttributes.addFlashAttribute("info", "更新文章成功！");	
		return "redirect:/admin/article/edit/"+id;
	}
	
	/** 删除文章 */
	@RequestMapping("/del/{articleId}")
	public String del(@PathVariable Integer articleId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		Article article = articleService.getService(articleId);
		Integer articleCategoryId = article.getArticleCategory().getId();
		
		/** solr 库删除 
		 *  似有缓存:删除第二条数据时,先前删除的第一条数据才消失,第二条数据仍有。重启服务器第二条数据消失。
		 * */
		try {                                     
			solrServer.deleteById(String.valueOf(articleId));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}finally{
			articleService.deleteService(article);    /** 数据库中删除 */
		}
		
		/**
		 * 注意：返回 return "forward:/admin/article/manage/"; 会报错。具体原因待研
		 * 报错信息：Cannot expose session attribute 'articleId' because of an existing model object of the same name
		 * */ 
		redirectAttributes.addFlashAttribute("info", "文章删除成功！");
		return "redirect:/admin/article/manage/"+articleCategoryId;
	}
	
	/** 批量删除 */
	@RequestMapping("/batchDel")
	@ResponseBody
	public String batchDel(HttpServletRequest request){
		String ids[] = request.getParameter("ids").split("#");
				
		for (int id = 1; id < ids.length; id++) {
			Article article = articleService.getService(Integer.parseInt(ids[id]));
			try {                                     
				solrServer.deleteById(String.valueOf(ids[id]));             /** solr索引删除 */
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
			}finally{
				articleService.deleteService(article);                      /** 数据库中删除 */
			}	
		}
		return "删除成功！";
	}
	
	
	/** 设置/取消 置顶 */
	@RequestMapping("/stick")
	@ResponseBody
	public String stick(HttpServletRequest request){
		Article article = articleService.getService(Integer.parseInt(request.getParameter("id")));
		String checked = request.getParameter("checked");
		if ("true".equals(checked)) {
			article.setCreateDate(TimeUtils.add1000(article.getCreateDate()));
			articleService.updateService(article);
			return "置顶成功！";
		}else {
			article.setCreateDate(TimeUtils.cut1000(article.getCreateDate()));
			articleService.updateService(article);
			return "取消置顶！";
		}
	}
	
	@RequestMapping("/uploadimages")
	@ResponseBody
	public boolean uploadImages(HttpServletRequest request,HttpSession session){
		String suffix = FileOperateUtils.getSuffix(request.getParameter("name")); 		// 文件后缀	
		if (FileOperateUtils.isImage(suffix)) {
			String randFileName = FileOperateUtils.getRandomString();             		// 随机文件名 - 无后缀
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String destDir = "/content/images/"+sdf.format(new Date());           		// 存放入以日期命名的文件夹
			File file = FileOperateUtils.uploadImage(request, randFileName, destDir);  	// 上传					

			/* 上传成功  */
			if (null != file) {
				ImageUtils.thumbnailImage(file, 100, 100, "thumbnail_", true);          // 生成缩略图
				
				/* 图片地址存入名为 webuploader_article的 session*/		
				@SuppressWarnings("unchecked")
				List<String> imageUrl = (null == session.getAttribute("webuploader_article")) ? new ArrayList<String>() : (List<String>) session.getAttribute("webuploader_article");	
				imageUrl.add(file.getAbsolutePath());
				session.setAttribute("webuploader_article", imageUrl);
				
				return true;
			}else{
				return false;
			}
			
			
		}
		return false;
	}
	
	@RequestMapping("/image/del")
	@ResponseBody
	public String imageDel(HttpServletRequest request){
		Integer imageId = Integer.valueOf(request.getParameter("imageId"));
		articleImageService.deleteService(imageId);
		return "success";
	}
	
	@RequestMapping("/image/move")
	@ResponseBody
	public String move(HttpServletRequest request){
		Integer originId = Integer.valueOf(request.getParameter("originId"));
		Integer changeId = Integer.valueOf(request.getParameter("changeId"));
		ArticleImage origin = articleImageService.getService(originId);
		ArticleImage change = articleImageService.getService(changeId);
		int originOrders = origin.getOrders();
		int changeOrders = change.getOrders();
		origin.setOrders(changeOrders);
		change.setOrders(originOrders);
		articleImageService.updateService(origin);
		articleImageService.updateService(change);
		return "success";
	}
	
}
