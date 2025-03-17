package com.trizmera.teste_api_e.service;

import com.trizmera.teste_api_e.Utils.RequestData;
import com.trizmera.teste_api_e.Utils.ResponseData;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BankService {

    private final Map<String, Integer> accounts = new ConcurrentHashMap<>();

    public void reset() {
        accounts.clear();
    }

    public Integer getBalance(String accountId) {
        return accounts.get(accountId);
    }

    public ResponseData createAccount(RequestData request) {
        String destination = request.getDestination();
        Integer amount = request.getAmount();


        accounts.put(destination, amount);

        ResponseData response = new ResponseData();
        ResponseData.Destination destinationAccount = new ResponseData.Destination();
        destinationAccount.setId(destination);
        destinationAccount.setBalance(amount);

        response.setDestination(destinationAccount);
        return response;
    }


    public ResponseData deposit(RequestData request) {
        String destination = request.getDestination();
        int amount = request.getAmount();

        int newBalance = accounts.getOrDefault(destination, 0) + amount;
        accounts.put(destination, newBalance);

        return new ResponseData(destination, newBalance);

    }

    public ResponseData withdraw(RequestData request) {
        String origin = request.getOrigin();
        int amount = request.getAmount();

        if (!accounts.containsKey(origin)) {
            ResponseData response = new ResponseData();
            ResponseData.Destination destinationAccount = new ResponseData.Destination();
            destinationAccount.setId(origin);
            destinationAccount.setBalance(0); // Return balance as 0
            response.setDestination(destinationAccount);
            return response;
        }

        // If the account exists, proceed with the withdrawal logic
        Integer currentBalance = accounts.get(origin);

        if (currentBalance < amount) {
            ResponseData response = new ResponseData();
            ResponseData.Destination destinationAccount = new ResponseData.Destination();
            destinationAccount.setId(origin);
            destinationAccount.setBalance(currentBalance); // No withdrawal, return current balance
            response.setDestination(destinationAccount);
            return response;
        }

        int newBalance = currentBalance - amount;
        accounts.put(origin, newBalance);

        ResponseData response = new ResponseData();
        response.setDestination(new ResponseData.Destination());
        response.getDestination().setId(origin);
        response.getDestination().setBalance(newBalance);

        return response;
    }


    public ResponseData transfer(RequestData request) {
        String origin = request.getOrigin();
        String destination = request.getDestination();
        int amount = request.getAmount();

        Integer originBalance = accounts.get(origin);
        if (originBalance == null || originBalance < amount) {
            return null;
        }

        int newOriginBalance = originBalance - amount;
        accounts.put(origin, newOriginBalance);

        int newDestinationBalance = accounts.getOrDefault(destination, 0) + amount;
        accounts.put(destination, newDestinationBalance);

        return new ResponseData(origin, newOriginBalance);
    }
}
