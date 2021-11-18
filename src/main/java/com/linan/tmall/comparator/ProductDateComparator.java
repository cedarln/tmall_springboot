package com.linan.tmall.comparator;
 
import java.util.Comparator;
 
import com.linan.tmall.pojo.Product;
 
public class ProductDateComparator implements Comparator<Product>{
    //创建日期最新
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
 
}