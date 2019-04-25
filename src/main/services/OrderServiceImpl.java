package main.services;

import main.dao.OrderDBHandler;
import main.entity.Order;
import main.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDBHandler orderDBHandler;

    public Order create(Order order) {
        return orderDBHandler.save(order);
    }

    public int updateStatusByID(Status status, long id) {
        return orderDBHandler.updateStatusByID(status, id);
    }

    public void delete(Order order) {
        orderDBHandler.delete(order);
    }
}
