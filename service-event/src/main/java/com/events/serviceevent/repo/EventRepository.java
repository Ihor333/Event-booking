package com.events.serviceevent.repo;

import com.events.serviceevent.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
