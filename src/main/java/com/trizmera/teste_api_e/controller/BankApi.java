package com.trizmera.teste_api_e.controller;

import com.trizmera.teste_api_e.Utils.RequestData;
import com.trizmera.teste_api_e.Utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Create account with initial balance")
    @ApiResponse(responseCode = "201")
    @PostMapping(value = "/event", consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<ResponseData> createAccount(@Valid @RequestBody RequestData request) throws Exception;
}
