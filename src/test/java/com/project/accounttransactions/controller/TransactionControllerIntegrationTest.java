package com.project.accounttransactions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.accounttransactions.service.AccountService;
import com.project.accounttransactions.vo.AccountCreateRequestVO;
import com.project.accounttransactions.vo.TransactionCreateRequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @Test
    void shouldCreateTransaction() throws Exception {
        AccountCreateRequestVO accountCreateRequest = new AccountCreateRequestVO();
        accountCreateRequest.setDocumentNumber("12345678900");
        accountService.create(accountCreateRequest);

        TransactionCreateRequestVO request = new TransactionCreateRequestVO();
        request.setAccountId(1L);
        request.setAmount(BigDecimal.valueOf(100));
        request.setOperationTypeId(1);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}