package com.trizmera.teste_api_e.controller;

import com.trizmera.teste_api_e.Utils.RequestData;
import com.trizmera.teste_api_e.Utils.ResponseData;
import com.trizmera.teste_api_e.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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
    public ResponseEntity<?> handleEvent(RequestData request) throws Exception {
        return switch (request.getType()) {
            case "deposit" -> deposit(request);
            case "withdraw" -> withdraw(request);
            case "transfer" -> transfer(request);
            default -> ResponseEntity.status(400).body("Invalid");
        };
    }

    private ResponseEntity<?> deposit(RequestData request) {
        ResponseData response = service.deposit(request);
        if (response == null) {
            return ResponseEntity.status(404).body(0);
        }
        return ResponseEntity.status(201).body(response);
    }

    private ResponseEntity<?> withdraw(RequestData request) {
        ResponseData response = service.withdraw(request);
        if (response.getOrigin() == null || response.getOrigin().getId() == null) {
            return ResponseEntity.status(404).body(0);
        }
        if (response.getOrigin().getBalance() < request.getAmount()) {
            return ResponseEntity.status(400).body(0);
        }
        return ResponseEntity.status(201).body(response);
    }

    private ResponseEntity<?> transfer(RequestData request) {
        ResponseData response = service.transfer(request);
        if (response == null) {
            return ResponseEntity.status(404).body(0);
        }
        return ResponseEntity.status(201).body(response);
    }


}
