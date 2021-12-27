package com.events.serviceevent.api;

import com.events.serviceevent.model.Event;
import com.events.serviceevent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {
    private final EventService event;

    @Autowired
    public EventController(EventService event){this.event = event;}

    @GetMapping(value = "/event")
    public ResponseEntity<List<Event>> getClients() {
        return ResponseEntity.ok(event.getAllEvents());
    }

    @PostMapping(value = "/event")
    public ResponseEntity<Event> postClient(@Valid @RequestBody Event newEvent) {
        return ResponseEntity.ok(event.addNewEvent(newEvent));
    }

    @GetMapping(value = "/event/{eventId}")
    public ResponseEntity<Event> getClientById(@PathVariable Long eventId) {
        return ResponseEntity.ok(event.getEventById(eventId));
    }

    @PutMapping(value = "/event/{eventId}")
    public ResponseEntity<Event> updateClientById(@PathVariable Long eventId, @Valid @RequestBody Event updatedEventInfo) {
        return ResponseEntity.ok(event.updateClientById(eventId, updatedEventInfo));
    }
    @DeleteMapping(value = "/event/{eventId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long eventId) {
        return ResponseEntity.ok(event.deleteClient(eventId));
    }
}
