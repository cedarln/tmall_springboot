package com.linan.tmall.es;
 
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
 
import com.linan.tmall.pojo.Product;

public interface ProductESDAO extends ElasticsearchRepository<Product,Integer>{
 
}