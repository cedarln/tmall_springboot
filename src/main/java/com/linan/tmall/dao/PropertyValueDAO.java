package com.linan.tmall.dao;

import com.linan.tmall.pojo.Category;
import com.linan.tmall.pojo.Product;
import com.linan.tmall.pojo.Property;
import com.linan.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {
    List<PropertyValue> findByProductOrderByIdDesc(Product product);//为什么这里叫find

    PropertyValue getByPropertyAndProduct(Property property, Product product);//这里叫get？
}
