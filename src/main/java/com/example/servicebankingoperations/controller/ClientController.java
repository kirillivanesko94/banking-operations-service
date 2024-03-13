package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.model.dto.ClientDto;
import com.example.servicebankingoperations.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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

    @GetMapping("search")
    public ResponseEntity<Page<Client>> searchClients(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDay,
                                                      @RequestParam(required = false) String phone,
                                                      @RequestParam(required = false) String fullName,
                                                      @RequestParam(required = false) String email,
                                                      @RequestParam(defaultValue = "0") int pageNumber,
                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(defaultValue = "fullName") String sortBy) {

        Page<Client> clients = service.searchClients(birthDay, phone, fullName, email, pageNumber, pageSize, sortBy);

        return ResponseEntity.ok().body(clients);
    }
}
