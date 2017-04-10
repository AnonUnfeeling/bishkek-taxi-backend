package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.OrderDto;
import ua.jdroidcoder.persistent.entity.OrdersEntity;
import ua.jdroidcoder.persistent.repository.OrderRepository;
import ua.jdroidcoder.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by jdroidcoder on 10.04.17.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto makeOrder(OrderDto orderDto) {
        return orderRepository.save(orderDto.clone()).clone();
    }

    @Override
    public List<OrderDto> getAllOrders(String userPhone) {
        return StreamSupport.stream(orderRepository.findOrderByUserPhone(userPhone).spliterator(), false)
                .map(OrdersEntity::clone)
                .collect(Collectors.toList());
    }

    @Override
    public void removeOrderById(Long id) {
        orderRepository.delete(id);
    }

    @Override
    public boolean getOrderById(Long id) {
        return orderRepository.findOne(id) == null;
    }
}
