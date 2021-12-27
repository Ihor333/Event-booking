package com.events.serviceorder.api;

import com.events.serviceorder.model.Order;
import com.events.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderController {
    private final OrderService order;

    @Autowired
    public OrderController(OrderService order){this.order = order;}

    @GetMapping(value = "/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(order.showAllOrders());
    }

    @GetMapping(value = "/order/event/{id}")
    public ResponseEntity<Order> getByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(order.getByEvent(id));
    }

    @GetMapping(value = "/order/client/{id}")
    public ResponseEntity<Order> getByClients(@PathVariable Long id) {
        return ResponseEntity.ok(order.getByClient(id));
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Order> postClient(@Valid @RequestBody Order newOrder) {
        return ResponseEntity.ok(order.addNewOrder(newOrder));
    }

    @DeleteMapping(value = "/order/{orderId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long orderId) {
        return ResponseEntity.ok(order.deleteOrder(orderId));
    }

}
