package com.mavict.setting.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mavict.article.category.ArticleCategory;
import com.mavict.article.category.ArticleCategoryService;
import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, Integer> implements NavigationService {
	
	@Resource(name = "navigationDaoImpl")
	private NavigationDao navigationDao;
	
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	
	@Override
	@Resource(name = "navigationDaoImpl")
	public void setBaseDao(BaseDao<Navigation, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public List<Navigation> getTreeService() {
		List<Navigation> result = new ArrayList<Navigation>();
		List<Navigation> navigations = navigationDao.getAll();
		for (Navigation navigation : navigations) {  
			
			if (navigation.getGrade() == 0) {    /** 如果是顶级分类(顶级分类的grade为0) */
				result.add(navigation);
				if (hasChildren(navigations,navigation)) {  /** 该顶级分类是否有下级分类 */
					result.addAll(getChildren(navigations,navigation));  /** 该分类所有下级分类递归全部加入 */
				}
			}
		}
		return result;
	}

	@Override
	public List<Navigation> getChildren(List<Navigation> navigations,Navigation navigation) {
	List<Navigation> children = new ArrayList<Navigation>();
		
		for (Navigation item : navigations) {    // A
			
			if (item.getParent() != null && navigation.getId() == item.getParent().getId()) {  // B			
				children.add(item);
				Navigation itemAlias = item; // 此步很关键，不然会导致参数在递归中混乱
				children.addAll(getChildren(navigations,itemAlias));
			}
		}
		return children;
	}

	@Override
	public boolean hasChildren(List<Navigation> navigations,Navigation navigation) {
		int i = 0;
		for (Navigation item : navigations) {
			if (item.getParent() != null && navigation.getId() == item.getParent().getId()) { i++; }
		}
		return (i > 0) ? true : false;
	}
    
	
	/**
     * 递归方式保存一级栏目:即在保存一级栏目时,若一级栏目下有各级子栏目,则按照原有的层级关系一并保存
     * 
     * @param navigation 
     * 导航实例
     * 
     * @param articleCategory
     * 文章类别的项目
     * 
     * */
	@Override
	public void saveCategoryService(Navigation navigation,ArticleCategory articleCategory) {
		/** 
		 * 确定保存栏目的层级:对于一级栏目,层级为0
		 * 添加一级栏目时,navigation参数实例 未设置parent,其值为null,故grade = 0
		 * 若非一级栏目,而是子项目递归调用至此,navigation参数实例 已设置parent,则grade为其父navigation的层级+1
		 * */
		int grade = (navigation.getParent() == null) ? 0 : (navigation.getParent().getGrade() + 1) ;
		
		/** 获取上级导航实例.一级栏目无上级实例,设置其上级实例id为0 */
		Navigation parent = (navigation.getParent() == null) ? new Navigation(0) : navigation.getParent();
		
		/** 表单是否填写了自定义的名称和URL */
		String navName = (null == navigation.getName() || "".equals(navigation.getName())) ? articleCategory.getName() : navigation.getName();
		String navUrl  = (null == navigation.getUrl()  || "".equals(navigation.getUrl())) ? articleCategory.getUrl() : navigation.getUrl();
		
		navigation.setName(navName);
		navigation.setUrl(navUrl);
		navigation.setGrade(grade);
		navigation.setParent(parent);
		navigationDao.save(navigation);
		
		/** 排序orders与id相同*/
		navigation.setOrders(navigation.getId().intValue());
		navigationDao.update(navigation);
		
		/** 递归循环遍历每个下级子栏目 */
		List<ArticleCategory> articleCategories = articleCategoryService.getChildren(articleCategory);
		if (articleCategories.size() != 0) {
			for (ArticleCategory item : articleCategories) {
				Navigation ng = new Navigation();
				/** 设置上级栏目 */
				ng.setParent(navigation);
				saveCategoryService(ng,item);
			}
		}
	}

	/** 
	 * 保存子栏目 
	 * 
	 * @param parent
	 * 上级栏目
	 * 
	 * @param articleCategory
	 * 文章栏目实体,其属性将转化为navigation实体保存
	 * */
	@Override
	public void saveChildService(Navigation navigation,Navigation parent,ArticleCategory articleCategory) {
		/** 表单是否填写了自定义的名称和URL */
		String navName = ("".equals(navigation.getName()) || navigation.getName().equals(null)) ? articleCategory.getName() : navigation.getName();
		String navUrl  = ("".equals(navigation.getUrl()) || navigation.getUrl().equals(null)) ? articleCategory.getUrl() : navigation.getUrl();
		
		navigation.setName(navName);
		navigation.setUrl(navUrl);
		navigation.setParent(parent);
		navigation.setGrade(parent.getGrade() + 1);
		navigationDao.save(navigation);
		
		/** 排序orders与id相同*/
		navigation.setOrders(navigation.getId().intValue());
		navigationDao.update(navigation);
	}

	
	@Override
	public void updateSequenceService(Integer myId, Integer rpId,Integer myOrders, Integer rpOrders) {
		Navigation myNavigation = navigationDao.get(myId);
		myNavigation.setOrders(rpOrders);
		navigationDao.update(myNavigation);
		
		Navigation rpNavigation = navigationDao.get(rpId);
		rpNavigation.setOrders(myOrders);
		navigationDao.update(rpNavigation);
	}

	@Override
	public boolean doIsChildService(Integer parentId) {
		return navigationDao.hasChild(parentId);
	}

	
	/** ----------------------------------------------------------------  */
	
	
	
	public void setChildren(List<Navigation> navigations,Navigation navigation) {
	List<Navigation> children = new ArrayList<Navigation>();
		
		for (Navigation item : navigations) {    // A		
			if (item.getParent() != null && navigation.getId() == item.getParent().getId()) {  // B			
				children.add(item);
			}	
		}
		
		navigation.setChild(children);

		for (Navigation child : children) {
			setChildren(navigations, child);
		}
	}
	
	

	@Override
	public List<Navigation> getChildedAllService() {
		List<Navigation> result = new ArrayList<Navigation>();
		List<Navigation> navigations = navigationDao.getAll();
		for (Navigation navigation : navigations) {  
			
			if (navigation.getGrade() == 0) {    /** 如果是顶级分类(顶级分类的grade为0) */
				result.add(navigation);
				if (hasChildren(navigations,navigation)) {  /** 该顶级分类是否有下级分类 */
					setChildren(navigations, navigation);   /** 该分类所有下级分类递归全部设置进 */
				}
			}
		}
		return result;
	}
	

}
