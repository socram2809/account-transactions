package com.project.accounttransactions.controller;

import com.project.accounttransactions.service.AccountService;
import com.project.accounttransactions.vo.AccountCreateRequestVO;
import com.project.accounttransactions.vo.AccountResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account Management", description = "Responsible to create and search accounts")
@RequestMapping("/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(
            description = "Create a new account",
            tags = {"Account Management"})
    @ApiResponse(responseCode = "201", description = "Account created")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponseVO create(
            @Valid @RequestBody AccountCreateRequestVO accountCreateRequestVO) {
        return accountService.create(accountCreateRequestVO);
    }
}
