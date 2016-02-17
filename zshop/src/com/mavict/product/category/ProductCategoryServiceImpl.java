package com.mavict.product.category;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mavict.base.BaseDao;
import com.mavict.category.CategoryServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class ProductCategoryServiceImpl extends CategoryServiceImpl<ProductCategory, ProductCategoryDao> implements ProductCategoryService {
	
	@Override
	@Resource(name = "productCategoryDaoImpl")
	public void setBaseDao(BaseDao<ProductCategory, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	@Resource(name = "productCategoryDaoImpl")
	public void setDao(ProductCategoryDao dao) {
		super.setDao(dao);
	}
	
}
