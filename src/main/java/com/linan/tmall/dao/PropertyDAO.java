package com.linan.tmall.dao;

import com.linan.tmall.pojo.Category;
import com.linan.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDAO extends JpaRepository<Property, Integer> {
    //根据分类category进行查询
    //这样命名后可以直接调用
    Page<Property> findByCategory(Category category, Pageable pageable);
}
