package com.trizmera.teste_api_e.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public interface BankApi {

    @Operation(summary = "Reset the state before starting tests")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/reset")
    public ResponseEntity<Void> resetAppState() throws Exception;

    @Operation(summary = "Get balance for non-existing account")
    @ApiResponse(responseCode = "404")
    @GetMapping(value = "/balance{account_id}")
    public ResponseEntity<Void> balanceNoAccount() throws Exception;

    

}
