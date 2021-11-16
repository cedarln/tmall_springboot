package com.linan.tmall.dao;
  
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.linan.tmall.pojo.Order;
import com.linan.tmall.pojo.User;
 
public interface OrderDAO extends JpaRepository<Order,Integer>{
}