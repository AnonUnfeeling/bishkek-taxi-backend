package ua.jdroidcoder.service;

import ua.jdroidcoder.persistent.dto.OrderDto;

import java.util.List;

/**
 * Created by jdroidcoder on 10.04.17.
 */
public interface OrderService {
    OrderDto makeOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders(String userPhone);

    void removeOrderById(Long id);

    boolean getOrderById(Long id);
}
