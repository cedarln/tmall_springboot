package com.linan.tmall.web;

import com.linan.tmall.pojo.Category;
import com.linan.tmall.service.CategoryService;
import com.linan.tmall.util.ImageUtil;
import com.linan.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@RestController  //对每个方法的返回值都直接转换为json对象（实际上返回的大多数都是Category）
//如果返回集合（如List<Category>）则会返回一个数组
public class CategoryController { //用来提供restful服务(一个前后端分离的标准)的控制层
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

    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id")int id, HttpServletRequest request) throws Exception {
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id+".jpg");
        file.delete();
        return null; //删除成功，返回空串
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id")int id) throws Exception {
        Category bean = categoryService.get(id);
        return bean;
    }

    @PutMapping("/categories/{id}")
    public Object update(
            MultipartFile image,
            Category bean,
            HttpServletRequest request
    ) throws Exception {
//        String name = request.getParameter("name");
//        bean.setName(name);
        System.out.println("update, new name is " + bean.getName());
        categoryService.update(bean);
        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, bean.getId()+".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
}
