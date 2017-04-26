package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.OrderDto;
import ua.jdroidcoder.persistent.entity.OrdersEntity;
import ua.jdroidcoder.persistent.repository.OrderRepository;
import ua.jdroidcoder.persistent.repository.UserProfileRepository;
import ua.jdroidcoder.service.OrderService;

import java.util.Date;
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
    public List<OrderDto> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .filter(ordersEntity -> ordersEntity.getStatus().equals("new"))
                .map(OrdersEntity::clone)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto acceptOrder(OrderDto orderDto) {
        return orderRepository.save(orderRepository.findOne(orderDto.getId())
                .setAcceptDate(new Date(orderDto.getAcceptDate()))
                .setDriverPhone(orderDto.getDriverPhone()).setStatus("accepted")).clone();
    }

    @Override
    public List<OrderDto> getAcceptOrders(String driverPhone) {
        return StreamSupport.stream(orderRepository.findOrderByDriverPhone(driverPhone).spliterator(), false)
                .map(OrdersEntity::clone)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto removeAcceptedOrder(Long id) {
        return orderRepository.save(orderRepository.findOne(id)
                .setDriverPhone(null).setStatus("new")).clone();
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
