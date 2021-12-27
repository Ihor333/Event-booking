package com.events.serviceorder.service;

import com.events.serviceorder.api.dto.ClientDto;
import com.events.serviceorder.api.dto.EventDto;
import com.events.serviceorder.model.Order;
import com.events.serviceorder.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo){
        this.repo = repo;
    }

    public List<Order> showAllOrders(){
        RestTemplate restTemplate = new RestTemplate();
        List<Order> list =  repo.findAll();
        for (Order order:list){
            String connectionToClient = "http://localhost:8082/client/" + order.getClientId();
            String connectionToEvent = "http://localhost:8081/event/" + order.getEventId();
            Long clientId = restTemplate.getForEntity(connectionToClient, ClientDto.class).getBody().getClient_id();
            Long eventId = restTemplate.getForEntity(connectionToEvent, EventDto.class).getBody().getEvent_id();
            order.setClientId(clientId);
            order.setEventId(eventId);
        }
        return repo.findAll();
    }
    public Order getByEvent(Long id){
        Optional<Order> order = repo.findByEventId(id);
        if(order.isPresent())
            return order.get();
        else
            throw new NoSuchElementException();
    }
    public Order getByClient(Long id){
        Optional<Order> order = repo.findByClientId(id);
        if(order.isPresent())
            return order.get();
        else
            throw new NoSuchElementException();
    }

    public Order addNewOrder(Order newOrder){
        return repo.save(newOrder);
    }

    public String deleteOrder(Long id){
        Optional<Order> order = repo.findById(id);
        if(order.isPresent()) {
            repo.deleteById(id);
            return "Order was deleted";
        }
        else
            return "No order with such id";
    }

}
