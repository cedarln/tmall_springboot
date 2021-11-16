package com.linan.tmall.dao;
  
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.linan.tmall.pojo.User;
 
public interface UserDAO extends JpaRepository<User,Integer>{
 
}