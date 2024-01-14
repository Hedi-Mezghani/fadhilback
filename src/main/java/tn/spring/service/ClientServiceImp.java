package tn.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.spring.dao.ClientDao;
import tn.spring.entity.Client;
import tn.spring.exceptions.ProjectNotFoundExeption;

import javax.transaction.Transactional;
import java.util.List;


@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class ClientServiceImp implements ClientService {

    private ClientDao clientDao;

    @Override
    public Client saveClient(Client client) {

        log.info("saving new Client");

        Client saveClient= clientDao.save(client);
        return saveClient;
    }

    @Override
    public List<Client> ListClients() {
        List<Client> client= clientDao.findAll();
        return client;
    }

    @Override
    public Client getClient(Long id) throws ProjectNotFoundExeption {
        Client client=clientDao.findById(id).orElseThrow(() -> new ProjectNotFoundExeption("client not found"));
        return client;

    }

    @Override
    public Client updateClient(Client client) {

        log.info("Update Client");
        Client saveClient = clientDao.save(client);
        return saveClient;
    }

    @Override
    public void deleteClient(Long id) {
        clientDao.deleteById(id);
    }
    }
