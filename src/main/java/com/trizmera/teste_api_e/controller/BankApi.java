package com.trizmera.teste_api_e.controller;

import com.trizmera.teste_api_e.Utils.RequestData;
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
    public ResponseEntity<?> resetAppState() throws Exception;


    @Operation(summary = "Get balance for account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance returned"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam("account_id") String accountId) throws Exception;


    @Operation(summary = "Events (deposit, withdraw, transfer)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid event type or insufficient balance"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> handleEvent(@Valid @RequestBody RequestData request) throws Exception;
}
