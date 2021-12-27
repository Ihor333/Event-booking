package com.events.serviceevent.service;

import com.events.serviceevent.api.dto.OrderDto;
import com.events.serviceevent.model.Event;
import com.events.serviceevent.repo.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repo;
    public EventService(EventRepository repo){this.repo = repo;}
    public List<Event> getAllEvents(){return repo.findAll();}

    public Event addNewEvent(Event newEvent){
        return repo.save(newEvent);
    }

    public Event getEventById(Long id){
        Optional<Event> client = repo.findById(id);
        if(client.isPresent())
            return client.get();
        else
            throw new NoSuchElementException();
    }

    public void clientUpdating(Event oldEventInfo, Event newEventInfo) {
        oldEventInfo.setEventName(newEventInfo.getEventName());
        oldEventInfo.setDestination(newEventInfo.getDestination());
        oldEventInfo.setDuration(newEventInfo.getDuration());
        oldEventInfo.setDescription(newEventInfo.getDescription());
        oldEventInfo.setCost(newEventInfo.getCost());
    }
    public Event updateClientById(Long id, Event newEventInfo){
        Optional<Event> event = repo.findById(id);
        if(event.isPresent()) {
            Event eventInfoForUpdate = event.get();
            clientUpdating(eventInfoForUpdate, newEventInfo);
            return repo.save(eventInfoForUpdate);
        }
        else
            throw new NoSuchElementException();
    }

    public String deleteClient(Long id){
        Optional<Event> event = repo.findById(id);
        RestTemplate restTemplate = new RestTemplate();
        if(event.isPresent()) {
            String getOrderLink = "http://localhost:8080/order/event/" + event.get().getEvent_id();
            Long orderId = restTemplate.getForEntity(getOrderLink, OrderDto.class).getBody().getClientId();
            String deleteOrderLink = "http://localhost:8080/order/" + orderId;
            restTemplate.delete(deleteOrderLink);
            repo.deleteById(id);
            return "Client was deleted";
        }
        else
            return "No client with such id";
    }
}

