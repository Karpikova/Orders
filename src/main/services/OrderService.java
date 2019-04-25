package main.services;

import main.entity.Order;
import main.entity.Status;

import java.sql.SQLException;

public interface OrderService {

    /**
     * Create a new Order
     *
     * @param order
     * @return PK from DB
     */
    Order create(Order order) throws SQLException;

    /**
     * Update status order by Id
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatusByID(Status status, long id);

    /**
     * Delete order
     *
     * @param order
     */
    void delete(Order order);
}
