package com.linan.tmall.comparator;
 
import java.util.Comparator;
 
import com.linan.tmall.pojo.Product;
 
public class ProductPriceComparator implements Comparator<Product> {
    //价格最低
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }
 
}