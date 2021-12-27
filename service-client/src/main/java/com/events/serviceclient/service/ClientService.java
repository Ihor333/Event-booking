package com.events.serviceclient.service;

import com.events.serviceclient.api.dto.OrderDto;
import com.events.serviceclient.model.Client;
import com.events.serviceclient.repo.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo){
        this.repo = repo;
    }

    public List<Client> getAllClients(){return repo.findAll();}

    public Client addNewClient(Client newClient){
        return repo.save(newClient);
    }

    public Client getClientById(Long id){
       Optional<Client> client = repo.findById(id);
       if(client.isPresent())
           return client.get();
       else
           throw new NoSuchElementException();
    }

    public void clientUpdating(Client oldClientInfo, Client newClientInfo) {
        oldClientInfo.setClientFirstName(newClientInfo.getClientFirstName());
        oldClientInfo.setClientSecondName(newClientInfo.getClientSecondName());
        oldClientInfo.setClientPhone(newClientInfo.getClientPhone());
        oldClientInfo.setClientEmail(newClientInfo.getClientEmail());
        oldClientInfo.setClientPassword(newClientInfo.getClientPassword());
    }
    public Client updateClientById(Long id, Client newClientInfo){
        Optional<Client> client = repo.findById(id);
        if(client.isPresent()) {
            Client clientInfoForUpdate = client.get();
            clientUpdating(clientInfoForUpdate, newClientInfo);
            return repo.save(clientInfoForUpdate);
        }
        else
            throw new NoSuchElementException();
    }
    public String deleteClient(Long id){
        Optional<Client> client = repo.findById(id);
        System.out.println(client);
        RestTemplate restTemplate = new RestTemplate();
        if(client.isPresent()) {
            String getOrderLink = "http://localhost:8080/order/client/" + client.get().getClient_id();
            Long orderId = restTemplate.getForEntity(getOrderLink, OrderDto.class).getBody().getClientId();
            String deleteOrderLink = "http://localhost:8080/order/" + orderId;
            restTemplate.delete(deleteOrderLink);
            repo.deleteById(id);
            return "Client was deleted";
        }
            return "No client with such id";
    }
}
