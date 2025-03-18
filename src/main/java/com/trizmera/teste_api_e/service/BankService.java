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

//    public ResponseData createAccount(RequestData request) {
//        String destination = request.getDestination();
//        Integer amount = request.getAmount();
//
//        accounts.put(destination, amount);
//
//        ResponseData response = new ResponseData();
//        response.setDestination(new ResponseData.Destination(destination, amount));
//        return response;
//    }


    public ResponseData deposit(RequestData request) {
        String destination = request.getDestination();
        int amount = request.getAmount();

        if (!accounts.containsKey(destination)) {
            accounts.put(destination, amount);
        } else {
            int currentBalance = accounts.get(destination);
            accounts.put(destination, currentBalance + amount);
        }


        ResponseData response = new ResponseData();
        response.setDestination(new ResponseData.Destination(destination, accounts.get(destination)));

        return response;
    }

    public ResponseData withdraw(RequestData request) {
        String origin = request.getOrigin();
        int amount = request.getAmount();

        if (!accounts.containsKey(origin)) {
            ResponseData response = new ResponseData();
            response.setOrigin(new ResponseData.Origin(origin, 0));
            return response;
        }

        int currentBalance = accounts.get(origin);

        if (currentBalance < amount) {
            ResponseData response = new ResponseData();
            response.setOrigin(new ResponseData.Origin(origin, currentBalance));
            return response;
        }

        int newBalance = currentBalance - amount;
        accounts.put(origin, newBalance);

        ResponseData response = new ResponseData();
        response.setOrigin(new ResponseData.Origin(origin, newBalance));
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

        Integer destBalance = accounts.getOrDefault(destination, 0);
        int newDestBalance = destBalance + amount;
        accounts.put(destination, newDestBalance);

        ResponseData response = new ResponseData();

        ResponseData.Origin originAccount = new ResponseData.Origin();
        originAccount.setId(origin);
        originAccount.setBalance(newOriginBalance);
        response.setOrigin(originAccount);

        ResponseData.Destination destAccount = new ResponseData.Destination();
        destAccount.setId(destination);
        destAccount.setBalance(newDestBalance);
        response.setDestination(destAccount);

        return response;
    }
}
