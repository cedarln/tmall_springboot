package com.linan.tmall.web;

import com.linan.tmall.pojo.Category;
import com.linan.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //对每个方法返回值都直接转换为json格式的控制器
public class CategoryController {//专门用来提供restful服务的控制器？
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() throws Exception {
        return categoryService.list(); //最后会返回json数据
    }
}
