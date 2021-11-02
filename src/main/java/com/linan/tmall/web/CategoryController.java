package com.linan.tmall.web;

import com.linan.tmall.pojo.Category;
import com.linan.tmall.service.CategoryService;
import com.linan.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //对每个方法返回值都直接转换为json格式的控制器
public class CategoryController {//专门用来提供restful服务的控制器？
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public Page4Navigator<Category> list(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) throws Exception {
        start = start<0 ? 0 : start;
        Page4Navigator<Category> page = categoryService.list(start,size,5);
        return page; //最后会返回json数据
    }
}
