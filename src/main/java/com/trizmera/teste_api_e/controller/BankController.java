package com.trizmera.teste_api_e.controller;

import com.trizmera.teste_api_e.Utils.RequestData;
import com.trizmera.teste_api_e.Utils.ResponseData;
import com.trizmera.teste_api_e.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BankController implements BankApi {

    @Autowired
    private BankService service;

    @Override
    public ResponseEntity<Void> resetAppState() throws Exception {
        service.reset();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getBalance(String accountId) throws Exception {
        Integer balance = service.getBalance(accountId);
        if (balance == null) {
            return ResponseEntity.status(404).body(0);
        }
        return ResponseEntity.ok(balance);
    }

    @Override
    public ResponseEntity<?> createAccount(RequestData request) throws Exception {
        if ("deposit".equals(request.getType())) {
            ResponseData response = service.createAccount(request);
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.status(404).body(0);
    }

    @Override
    public ResponseEntity<?> deposit(RequestData request) throws Exception {
        ResponseData response = service.deposit(request);

        if (response.getDestination().getId() == null) {
            return ResponseEntity.status(404).body(0);
        }

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<?> withdraw(RequestData request) throws Exception {
        if ("withdraw".equals(request.getType())) {
            ResponseData response = service.withdraw(request);
            if (response.getDestination().getId() == null) {
                return ResponseEntity.status(404).body(0);
            }

            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.status(404).body(0);
    }

    @Override
    public ResponseEntity<?> transfer(RequestData request) throws Exception {
        ResponseData response = service.transfer(request);
        if (response.getDestination().getId() == null) {
            return ResponseEntity.status(404).body(0);
        }
        return ResponseEntity.status(201).body(response);
    }
}
