package ua.jdroidcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.jdroidcoder.persistent.dto.OrderDto;
import ua.jdroidcoder.service.OrderService;

import javax.validation.Valid;

/**
 * Created by jdroidcoder on 10.04.17.
 */
@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("makeOrder")
    public ResponseEntity makeOrder(@Valid OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(orderService.makeOrder(orderDto));
    }

    @GetMapping("getOrders")
    public ResponseEntity getAllOrders(String userPhone) {
        return ResponseEntity.ok(orderService.getAllOrders(userPhone));
    }

    @GetMapping("getAllOrders")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("acceptOrder")
    public ResponseEntity acceptOrder(@Valid OrderDto orderDto){
        return ResponseEntity.ok(orderService.acceptOrder(orderDto));
    }

    @PostMapping("removeAcceptedOrder")
    public ResponseEntity removeAcceptedOrder(Long id){
        return ResponseEntity.ok(orderService.removeAcceptedOrder(id));
    }

    @GetMapping("getAcceptOrders")
    private ResponseEntity getAllAcceptOrders(String driverPhone){
        return ResponseEntity.ok(orderService.getAcceptOrders(driverPhone));
    }

    @GetMapping("deleteOrder")
    public ResponseEntity deleteOrder(Long id) {
        orderService.removeOrderById(id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
