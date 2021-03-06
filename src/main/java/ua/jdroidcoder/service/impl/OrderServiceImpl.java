package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.OrderDto;
import ua.jdroidcoder.persistent.entity.OrdersEntity;
import ua.jdroidcoder.persistent.repository.OrderRepository;
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
        if (getAllOrders(orderDto.getUserPhone()).isEmpty()) {
            return orderRepository.save(orderDto.clone().setCreatedDate(new Date())).clone();
        } else {
            return null;
        }
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
    public List<OrdersEntity> getOrders() {
        return (List<OrdersEntity>) orderRepository.findAll();
    }

    @Override
    public OrderDto acceptOrder(OrderDto orderDto) {
        try {
            return orderRepository.save(orderRepository.findOne(orderDto.getId())
                    .setAcceptDate(new Date(orderDto.getAcceptDate()))
                    .setDriverPhone(orderDto.getDriverPhone()).setStatus("accepted")).clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderDto> getAcceptOrders(String driverPhone) {
        return StreamSupport.stream(orderRepository.findOrderByDriverPhone(driverPhone).spliterator(), false)
                .map(OrdersEntity::clone)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto removeAcceptedOrder(Long id) {
        try {
            return orderRepository.save(orderRepository.findOne(id)
                    .setDriverPhone(null).setStatus("new").setAcceptDate(null)).clone();
        } catch (Exception e) {
            return null;
        }
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
