package com.mavict.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DaoSupport;

import com.mavict.article.category.ArticleCategory;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class CategoryServiceImpl<T extends Category<T>, Dao extends CategoryDao<T>> extends BaseServiceImpl<T, Integer> implements CategoryService<T> {
	
	public Dao dao;
	
	/* 需覆写此方法 */
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<T> getCategoryList() {
		return dao.get("order by orders asc");
	}
	
	private List<T> categories = null;
	
	/** 
	 * 获取分类树 -- 将所有的目录取出，通过简单算法设计使其按预定显示顺序重排
	 * 
	 * 1、一次性通过categoryDao.getAll()按照orders asc顺序将目录全部取出
	 * 2、分析每个实例，将顶级分类实例挑出，并通过 hasChildren(ArticleCategories,ArticleCategory)判断其是否有下级分类
	 * 3、无下级分类直接存入并跳出循环。有下级分类则通过 getChildren(ArticleCategories,ArticleCategory) 将所有下级分类全部加入
	 * */
	@Override
	public List<T> getTreeService() {
		List<T> result = new ArrayList<T>();
		categories = getCategoryList();
		for (T category : categories) {  
			
			if (category.getGrade() == 1) {    /** 如果是顶级分类(顶级分类的grade设置为1) */
				result.add(category);
				if (hasChildren(category)) {  /** 该顶级分类是否有下级分类 */
					result.addAll(getChildren(category));  /** 该分类所有下级分类递归全部加入 */
				}
			}
		}
		return result;
	}
	
	
	/** 
	 * 判断是否有下级分类 
	 * 
	 * 1、第一个参数是欲检索的所有目录实例集合，第二个参数是要判定有无下级分类的目录实例，
	 * 2、第二个参数在第一个参数的集合中依次对比每个实例item，看是否有元素是其子元素。有返回true，否则false
	 * */
	@Override
	public boolean hasChildren(T category) {
		int i = 0;
		for (T item : ((categories == null) ? getCategoryList() : categories)) {
			if (item.getParent() != null && category.getId() == item.getParent().getId()) { i++; }
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
	public List<T> getChildren(T category) {
		List<T> children = new ArrayList<T>();
		
		for (T item : ((categories == null) ? getCategoryList() : categories)) {    // A
			
			if (item.getParent() != null && category.getId() == item.getParent().getId()) {  // B			
				children.add(item);
				T itemAlias = item; // 此步很关键，不然会导致参数在递归中混乱
				children.addAll(getChildren(itemAlias));
			}
		}
		return children;
	}

	@Override
	public void updateSequenceService(Integer myId, Integer rpId, Integer myOrders, Integer rpOrders) {
		T myCategory = dao.get(myId);
		myCategory.setOrders(rpOrders);
		dao.update(myCategory);
		
		T rpCategory = dao.get(rpId);
		rpCategory.setOrders(myOrders);
		dao.update(rpCategory);
	}


}



