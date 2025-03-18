package com.trizmera.teste_api_e.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private String id;
    private Integer balance;
    private String type;
    @JsonProperty("origin")
    private Destination origin;
    @JsonProperty("destination")
    private Destination destination;
    private Integer amount;

    public ResponseData() {
    }

    public ResponseData(String destinationId, Integer balance) {
        this.destination = new Destination(destinationId, balance);
    }

    public ResponseData(Destination destination, int balance) {
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

    public Destination getOrigin() {
        return origin;
    }

    public void setOrigin(Destination origin) {
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

    public static class Origin {
        private String id;
        private Integer balance;

        public Origin(String id, Integer balance) {
            this.id = id;
            this.balance = balance;
        }

        public Origin() {
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
