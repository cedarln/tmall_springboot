package com.linan.tmall.service;

import com.linan.tmall.dao.OrderItemDAO;
import com.linan.tmall.pojo.Order;
import com.linan.tmall.pojo.OrderItem;
import com.linan.tmall.pojo.Product;
import com.linan.tmall.pojo.User;
import com.linan.tmall.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Cacheable(cacheNames = "orderItems")
public class OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    @CacheEvict(allEntries = true)
    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public void fill(Order order) {
        OrderItemService orderItemService = SpringContextUtil.getBean(OrderItemService.class);//aop-redis改造
        List<OrderItem> orderItems = orderItemService.listByOrder(order);
//        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    @CacheEvict(allEntries = true)
    public void add(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    @Cacheable(key = "'orderItems-one-' + #p0")
    public OrderItem get(int id) {
        return orderItemDAO.findOne(id);
    }

    @CacheEvict(allEntries = true)
    public void delete(int id) {
        orderItemDAO.delete(id);
    }

    public int getSaleCount(Product product) {
        OrderItemService orderItemService = SpringContextUtil.getBean(OrderItemService.class);//aop-redis改造
        List<OrderItem> ois = orderItemService.listByProduct(product);
//        List<OrderItem> ois = listByProduct(product);
        int result = 0;
        for (OrderItem oi : ois) {
            if (null != oi.getOrder())
                if (null != oi.getOrder() && null != oi.getOrder().getPayDate())
                    result += oi.getNumber();
        }
        return result;
    }

    @Cacheable(key = "'orderItems-pid-' + #p0.id")
    public List<OrderItem> listByProduct(Product product) {
        return orderItemDAO.findByProduct(product);
    }

    @Cacheable(key = "'orderItems-oid-' + #p0.id")
    public List<OrderItem> listByOrder(Order order) {
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    @Cacheable(key = "'orderItems-uid-' + #p0.id")
    public List<OrderItem> listByUser(User user) {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }
}