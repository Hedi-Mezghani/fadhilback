package tn.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.Client;
import tn.spring.exceptions.ProjectNotFoundExeption;
import tn.spring.service.ClientServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor

public class ClientController {
    private ClientServiceImp ClientService;

    @GetMapping("")
    public List<Client> listClient(){
        return ClientService.ListClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable(name="id")Long id) throws ProjectNotFoundExeption {
        return ClientService.getClient(id);
    }
    @PostMapping("")
    public Client saveClient(@RequestBody Client client) {
        return ClientService.saveClient(client);
    }
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client ) {
        client.setId(id);
        return ClientService.updateClient(client);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        ClientService.deleteClient(id);
    }
}
