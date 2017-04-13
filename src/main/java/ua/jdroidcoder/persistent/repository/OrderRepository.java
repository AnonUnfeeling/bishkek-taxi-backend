package ua.jdroidcoder.persistent.repository;

import org.springframework.data.repository.CrudRepository;
import ua.jdroidcoder.persistent.entity.OrdersEntity;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public interface OrderRepository extends CrudRepository<OrdersEntity, Long> {
    Iterable<OrdersEntity> findOrderByUserPhone(String userPhone);

    OrdersEntity findByUserPhone(String userPhone);

    Iterable<OrdersEntity> findOrderByDriverPhone(String driverPhone);
}
