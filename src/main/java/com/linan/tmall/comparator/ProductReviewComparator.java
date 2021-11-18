package com.linan.tmall.comparator;
 
import java.util.Comparator;
 
import com.linan.tmall.pojo.Product;
 
public class ProductReviewComparator implements Comparator<Product> {
    //评论最多
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }
 
}