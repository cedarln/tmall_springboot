package com.linan.tmall.comparator;
 
import java.util.Comparator;
 
import com.linan.tmall.pojo.Product;
 
public class ProductSaleCountComparator implements Comparator<Product> {
    //销量最高
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount() - p1.getSaleCount();
    }
}