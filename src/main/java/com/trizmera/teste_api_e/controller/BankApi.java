package com.trizmera.teste_api_e.controller;

import com.trizmera.teste_api_e.Utils.RequestData;
import com.trizmera.teste_api_e.Utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public interface BankApi {

    @Operation(summary = "Reset the state before starting tests")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/reset")
    public ResponseEntity<Void> resetAppState() throws Exception;


    @Operation(summary = "Get balance for account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance returned"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam("account_id") String accountId) throws Exception;

    @Operation(summary = "Create account with initial balance")
    @ApiResponse(responseCode = "201")
    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAccount(@Valid @RequestBody RequestData request) throws Exception;


    @Operation(summary = "Create account with initial balance (deposit)")
    @ApiResponse(responseCode = "201", description = "Account created or updated with deposit")
    @PostMapping(value = "/event/deposit", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> deposit(@Valid @RequestBody RequestData request) throws Exception;


    @Operation(summary = "Withdraw from account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Withdraw successful"),
            @ApiResponse(responseCode = "404", description = "Origin account not found")
    })
    @PostMapping(value = "/event/withdraw", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> withdraw(@Valid @RequestBody RequestData request) throws Exception;


    @Operation(summary = "Transfer from one account to another")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transfer successful"),
            @ApiResponse(responseCode = "404", description = "Origin account not found")
    })
    @PostMapping(value = "/event/transfer", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> transfer(@Valid @RequestBody RequestData request) throws Exception;
}
