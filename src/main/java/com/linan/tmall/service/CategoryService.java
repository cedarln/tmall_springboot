package com.linan.tmall.service;

import com.linan.tmall.dao.CategoryDAO;
import com.linan.tmall.pojo.Category;
import com.linan.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;  //自动装配到底是啥意思

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        System.out.println("start="+start+",size="+size);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

    public Category get(int id) {
        Category c = categoryDAO.findOne(id);
        return c;
    }
}
