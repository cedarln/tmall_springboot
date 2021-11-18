package com.linan.tmall.dao;
  
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.linan.tmall.pojo.Product;
import com.linan.tmall.pojo.Review;
 
public interface ReviewDAO extends JpaRepository<Review,Integer>{
 
    List<Review> findByProductOrderByIdDesc(Product product);
    int countByProduct(Product product);
 
}