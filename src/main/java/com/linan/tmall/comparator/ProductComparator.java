package com.linan.tmall.comparator;

import com.linan.tmall.pojo.Product;

import java.util.Collections;
import java.util.Comparator;

public class ProductComparator implements Comparator<Product> { //替代其余五个Comparator
    String sortType;

    public ProductComparator(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(Product p1, Product p2) {
        if (null != sortType) {
            switch (sortType) {
                case "review":
                    return p2.getReviewCount() * p2.getSaleCount() - p1.getReviewCount() * p1.getSaleCount();
                case "date":
                    return p2.getCreateDate().compareTo(p1.getCreateDate());
                case "saleCount":
                    return (int) (p2.getPromotePrice()-p1.getPromotePrice());
                case "price":
                    return p2.getReviewCount()-p1.getReviewCount();
                case "all":
                    return p2.getSaleCount() - p1.getSaleCount();
            }
        }
        return 0;
    }
}
