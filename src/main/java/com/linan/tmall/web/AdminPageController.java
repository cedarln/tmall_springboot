package com.linan.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController { //用来处理跳转的控制层

    @GetMapping(value = "/admin")
    public String admin(){
        return "redirect:admin_category_list"; //redirect-直接访问地址
    }

    @GetMapping(value = "/admin_category_list")
    public String listCategory() {
        return "admin/listCategory"; //访问listCategory html文件
    }

    @GetMapping(value = "/admin_category_edit")
    public String editCategory() {
        return "admin/editCategory";
    }
}
