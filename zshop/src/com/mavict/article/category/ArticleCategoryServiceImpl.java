package com.mavict.article.category;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory, Integer> implements ArticleCategoryService {
	@Resource(name="articleCategoryDaoImpl")
	private ArticleCategoryDao articleCategoryDao;
	
	@Override
	@Resource(name="articleCategoryDaoImpl")
	public void setBaseDao(BaseDao<ArticleCategory, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	private List<ArticleCategory> articleCategories = null;
	/** 
	 * 获取分类树 -- 将所有的目录取出，通过简单算法设计使其按预定显示顺序重排
	 * 
	 * 1、一次性通过articleCategoryDao.getAll()按照orders asc顺序将目录全部取出
	 * 2、分析每个实例，将顶级分类实例挑出，并通过 hasChildren(ArticleCategories,ArticleCategory)判断其是否有下级分类
	 * 3、无下级分类直接存入并跳出循环。有下级分类则通过 getChildren(ArticleCategories,ArticleCategory) 将所有下级分类全部加入
	 * */
	@Override
	@Cacheable(value = "CUSTOM_CACHE", key = "'articleCategory'")
	public List<ArticleCategory> getTreeService() {
		List<ArticleCategory> result = new ArrayList<ArticleCategory>();
		articleCategories = getCategoryList();
		for (ArticleCategory articleCategory : articleCategories) {  
			
			if (articleCategory.getGrade() == 0) {    /** 如果是顶级分类(顶级分类的grade为0) */
				result.add(articleCategory);
				if (hasChildren(articleCategory)) {  /** 该顶级分类是否有下级分类 */
					result.addAll(getChildren(articleCategory));  /** 该分类所有下级分类递归全部加入 */
				}
			}
		}
		return result;
	}
	
	@Override
	@CacheEvict(value = "CUSTOM_CACHE", key = "'articleCategory'")
	public void saveService(ArticleCategory entity) {
		super.saveService(entity);
	}
	
	/** 
	 * 判断是否有下级分类 
	 * 
	 * 1、第一个参数是欲检索的所有目录实例集合，第二个参数是要判定有无下级分类的目录实例，
	 * 2、第二个参数在第一个参数的集合中依次对比每个实例item，看是否有元素是其子元素。有返回true，否则false
	 * */
	@Override
	public boolean hasChildren(ArticleCategory articleCategory) {
		int i = 0;
		for (ArticleCategory item : ((articleCategories == null) ? getCategoryList() : articleCategories)) {
			if (item.getParent() != null && articleCategory.getId() == item.getParent().getId()) { i++; }
		}
		return (i > 0) ? true : false;
	}

	
	/** 
	 * 获取下级分类 -- 递归方式
	 * 
	 * 1、第二个参数为给定元素实例，在 第一个参数集合 中找其下级分类
	 * 2、进入循环，分析集合中的每一个元素实例。即步骤A
	 * 3、如果有元素其父类id 与 给定元素id 相符，证明 该元素 是 给定元素 的子类，将该元素加入Children，这是第一层。即步骤B
	 * 4、继续对该子元素递归调用本方法，获取所有子元素
	 * */
	@Override
	public List<ArticleCategory> getChildren(ArticleCategory articleCategory) {
		List<ArticleCategory> children = new ArrayList<ArticleCategory>();
		
		for (ArticleCategory item : ((articleCategories == null) ? getCategoryList() : articleCategories)) {    // A
			
			if (item.getParent() != null && articleCategory.getId() == item.getParent().getId()) {  // B			
				children.add(item);
				ArticleCategory itemAlias = item; // 此步很关键，不然会导致参数在递归中混乱
				children.addAll(getChildren(itemAlias));
			}
		}
		return children;
	}

	@Override
	public List<ArticleCategory> getCategoryList() {
		return articleCategoryDao.getAll();
	}
	
	
	@Override
	public void updateSequenceService(Integer myId, Integer rpId,Integer myOrders, Integer rpOrders) {
		ArticleCategory myArticleCategory = articleCategoryDao.get(myId);
		myArticleCategory.setOrders(rpOrders);
		articleCategoryDao.update(myArticleCategory);
		
		ArticleCategory rpArticleCategory = articleCategoryDao.get(rpId);
		rpArticleCategory.setOrders(myOrders);
		articleCategoryDao.update(rpArticleCategory);
	}

}
