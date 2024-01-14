package tn.spring.service;


import tn.spring.dto.ProjectDto;
import tn.spring.entity.Client;
import tn.spring.exceptions.ProjectNotFoundExeption;

import java.util.List;


public interface ClientService {

    Client saveClient(Client client);

    List<Client>ListClients();

    Client getClient(Long id ) throws ProjectNotFoundExeption;

    Client updateClient(Client client);

    void deleteClient(Long id);

}