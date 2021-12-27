package com.events.serviceclient.repo;

import com.events.serviceclient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
