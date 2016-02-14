package com.mavict.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mavict.PageInfo;
import com.mavict.article.Article;
import com.mavict.article.ArticleService;

/**
 * Controller - 文章客户端
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller("articleClientController")
public class ArticleController {
	
	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	
	@Resource(name = "solrServerCore0")
	private SolrServer solrServer;
	
	/* 首页 */
	@RequestMapping("/")
	public String index(ModelMap model){
		Integer[][] groups = new Integer[][]{{17, 3},{5,8},{7,7},{6,6},{4,6},{1,8},{2,8},{3,8}};
		addAttributes("article", "more", groups, model);
		
		return "/client/index";
	}
	
	/* 单篇类文章 - 单篇展示 */
	@RequestMapping("/article/{id}")
	public String article(@PathVariable Integer id,ModelMap model){
		model.addAttribute("article", articleService.getService(id));
		return "/client/article/article";
	}
	
	/* 多篇类文章 - 单篇展示 */
	@RequestMapping("/articles/{id}")
	public String articles(@PathVariable Integer id,ModelMap model){
		model.addAttribute("article", articleService.getService(id));
		return "/client/article/articles";
	}
	
	/* 无图片类文章列表 */
	@RequestMapping("/list/{categoryId}")
	public String list(@PathVariable Integer categoryId,ModelMap model,PageInfo pageInfo){
		pageInfo.setPageSize(30);
		model.addAttribute("pagedContent", articleService.getPagedContentByCategoryIdService(categoryId, pageInfo));
		model.addAttribute("pageUrl", "testurl");
		return "/client/article/list";
	}
	
	/* 有图片类文章列表 */
	@RequestMapping("/lists/{categoryId}")
	public String lists(@PathVariable Integer categoryId,ModelMap model,PageInfo pageInfo){
		pageInfo.setPageSize(30);
		model.addAttribute("pagedContent", articleService.getPagedContentByCategoryIdService(categoryId, pageInfo));
		model.addAttribute("pageUrl", "testurl");
		return "/client/article/lists";
	}
	
	
	
	@RequestMapping("/search/{categoryId}")
	public String list(@PathVariable String categoryId, ModelMap model) throws SolrServerException, ParseException{
		SolrQuery query = new SolrQuery();

		query.setQuery("categoryId:"+categoryId);
		query.setRows(20);
		SolrDocumentList docs = solrServer.query(query).getResults();
		List<Article> articles = new ArrayList<Article>();
		for (SolrDocument solrDocument : docs) {
			Article article = new Article();
			article.setTitle(String.valueOf(solrDocument.getFieldValue("title")));
			article.setContent(String.valueOf(solrDocument.getFieldValue("content")));
			
			/** solr时间格式转换的坑,具体可参考 http://chengqianl.iteye.com/blog/1340385 */
			article.setCreateDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(String.valueOf(solrDocument.getFieldValue("createDate"))));
			articles.add(article);
		}
		
		model.addAttribute("articles", articles);
		
		return "/client/article/list";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap model) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		
		/** 
		 * 设置多条filterQuery语句有助于提高内存效率；组合查询条件设置单条filterQuery语句损耗内存多，但可以优化性能
		 * 可以采用  AND 或  OR 关键字 来设置查询与或
		 * */
		query.setQuery("title:北京  OR content:北京");	

		query.setRows(20);
		
		//设置高亮
        query.setHighlight(true);// 开启高亮组件
        query.addHighlightField("title");// 高亮字段
        query.addHighlightField("content");// 高亮字段
        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        query.setHighlightSimplePost("</font>");//后缀
        query.setHighlight(true).setHighlightSnippets(1); //获取高亮分片数，一般搜索词可能分布在文章中的不同位置，其所在一定长度的语句即为一个片段，默认为1，但根据业务需要有时候需要多取出几个分片。 - 此处设置决定下文中titleList, contentList中元素的个数
        query.setHighlightFragsize(150);//每个分片的最大长度，默认为100。适当设置此值，如果太小，高亮的标题可能会显不全；设置太大，摘要可能会太长。
           
        QueryResponse response = solrServer.query(query);
        SolrDocumentList docs = response.getResults();
        
        //获取所有高亮的字段
        Map<String,Map<String,List<String>>> highlightMap = response.getHighlighting();
       
        
		List<Article> articles = new ArrayList<Article>();
		for (SolrDocument solrDocument : docs) {
			Article article = new Article();
			
		    List<String> titleList=highlightMap.get(solrDocument.getFieldValue("id")).get("title");
            List<String> contentList=highlightMap.get(solrDocument.getFieldValue("id")).get("content");
           
            //获取并设置高亮的字段title
            if(titleList!=null && titleList.size()>0){ article.setTitle(titleList.get(0)); }      
            
            //获取并设置高亮的字段content
            if(contentList!=null && contentList.size()>0){ article.setContent(contentList.get(0));  }
			
			articles.add(article);
		}
		
		model.addAttribute("articles", articles);
		
		
		return "/client/article/list";
	}
	
	
	
	@RequestMapping("/del")
	public String del() throws SolrServerException, IOException{
		List<String> ids = new ArrayList<String>();
	      for (int i = 1; i < 300; i++) {
	        ids.add(String.valueOf(i));
	      }
	      solrServer.deleteById(ids);
	      return "/client/article/list";
	}
	
	
	/**
	 * 添加多栏目文章ftl模板属性 - 多个栏目文章在前台显示前Num条时,用此方法可以一次性添加ftl模板使用的属性
	 * 
	 * @param entity 文章实体前缀   ftl模板中生成形如 entity_categoryId 实体List
	 * @param more   更多文章前缀   ftl模板中生成形如 more_categoryId int值
	 * @param categoryIdAndNums 文章栏目Id和其前Num条 组成的二维数组
	 * @param model 
	 * 
	 * @return 多栏目文章ftl模板属性
	 * */
	private void addAttributes(String entity,String more,Integer[][] categoryIdAndNums,ModelMap model){
		for (Integer[] group : categoryIdAndNums) {
			model.addAttribute(entity+"_"+String.valueOf(group[0]), articleService.getNumListService(group[0], Integer.valueOf(group[1].toString())));
			model.addAttribute(more+"_"+String.valueOf(group[0]), group[0]);
		}
	}
	

}
