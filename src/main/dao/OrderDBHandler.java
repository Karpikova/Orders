package main.dao;

import main.entity.Order;
import main.entity.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDBHandler extends CrudRepository<Order, Long> {

    @Modifying
    @Transactional
    @Query("update Order u set u.status = ?1 where u.id = ?2")
    int updateStatusByID(Status status, long id);
}
