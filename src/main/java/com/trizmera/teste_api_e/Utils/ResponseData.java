package com.trizmera.teste_api_e.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private String id;
    private Integer balance;
    private String type;
    private String origin;
    @JsonProperty("destination")
    private Destination destination;
    private Integer amount;

    public ResponseData() {
    }

    public ResponseData(String destinationId, Integer balance) {
        this.destination = new Destination(destinationId, balance);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public static class Destination {
        private String id;
        private Integer balance;

        public Destination(String id, Integer balance) {
            this.id = id;
            this.balance = balance;
        }

        public Destination() {

        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("balance")
        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }
    }
}
