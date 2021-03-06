package com.linan.tmall.service;

import com.linan.tmall.dao.CategoryDAO;
import com.linan.tmall.pojo.Category;
import com.linan.tmall.pojo.Product;
import com.linan.tmall.util.Page4Navigator;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //业务层
@CacheConfig(cacheNames = "categories")
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;  //自动装配到底是啥意思
    //Spring运行后，会将此对象自动绑定到某一实例，动态绑定

    @CachePut(key = "'categories-page-'+ #p0 + '-' + #p1")
    public Page4Navigator<Category> list(int start, int size, int navigatePages) { //带分页的业务方法，查询所有category
        System.out.println("start="+start+",size="+size);
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //DESC倒叙，ASC为正序
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    @Cacheable(key = "'categories-all'")
    public List<Category> list() { //业务方法-查询
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    @CacheEvict(allEntries = true)
//    @CachePut(key = "'categories-one-'+ #p0")  //这样写本来没问题，但category-page里的信息不会更新，所以还是重新清除数据重新读取数据库。
    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    @CacheEvict(allEntries = true)
//    @CachePut(key = "'categories-one-'+ #p0")  //这样写本来没问题，但category-page里的信息不会更新，所以还是重新清除数据重新读取数据库。
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Cacheable(key = "'categories-one-'+ #p0")
    public Category get(int id) {
        Category c = categoryDAO.findOne(id);
        return c;
    }

    @CacheEvict(allEntries = true)
//    @CachePut(key = "'category-one-'+ #p0")  //这样写本来没问题，但category-page里的信息不会更新，所以还是重新清除数据重新读取数据库。
    public void update(Category bean) {
        categoryDAO.save(bean);
    }

    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }

    public void removeCategoryFromProduct(Category category) {
        List<Product> products =category.getProducts();
        if (null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }

        List<List<Product>> productsByRow =category.getProductsByRow();
        if (null != productsByRow) {
            for (List<Product> ps : productsByRow) {
                for (Product p: ps) {
                    p.setCategory(null);
                }
            }
        }
    }
}
