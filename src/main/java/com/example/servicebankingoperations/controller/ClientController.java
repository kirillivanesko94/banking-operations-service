package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.ClientDto;
import com.example.servicebankingoperations.service.ClientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("add")
    public void addNewClient(@RequestBody ClientDto clientDto) {
        service.addClient(clientDto);
    }

    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam(required = false) String fullName,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String phone) {
        return service.searchClients(fullName, birthDate, email, phone);
    }
}
