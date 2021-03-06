package com.linan.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity //表示这是一个实体类
@Table(name = "category")  //对应的表名是 category
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
//做前后端分离，而前后端数据交互用的是 json 格式。 那么 Category 对象就会被转换为 json 数据。
// 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate,
// 在 jpa 工作过程中，就会创造代理类来继承 Category ，并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，
// 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉
public class Category {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    @Column(name = "id") //对应的数据库字段名为id
    int id;

    String name; //没写说明对应的就是name

    @Transient
    List<Product> products;

    @Transient
    List<List<Product>> productsByRow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
