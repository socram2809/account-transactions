package com.project.accounttransactions.controller;

import com.project.accounttransactions.service.TransactionService;
import com.project.accounttransactions.vo.TransactionCreateRequestVO;
import com.project.accounttransactions.vo.TransactionResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transaction Management", description = "Responsible to create transactions for accounts")
@RequestMapping("/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {

     private final TransactionService transactionService;

    @Operation(
            description = "Create a new transaction",
            tags = {"Transaction Management"})
    @ApiResponse(responseCode = "201", description = "Transaction created")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
     public TransactionResponseVO create(@Valid @RequestBody TransactionCreateRequestVO request) {
         return transactionService.create(request);
     }
}
