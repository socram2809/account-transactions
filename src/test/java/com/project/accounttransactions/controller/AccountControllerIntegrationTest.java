package com.project.accounttransactions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.accounttransactions.vo.AccountCreateRequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAccount() throws Exception {
        AccountCreateRequestVO request = new AccountCreateRequestVO();
        request.setDocumentNumber("12345678900");

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAccountById() throws Exception {
        AccountCreateRequestVO request = new AccountCreateRequestVO();
        request.setDocumentNumber("12345678901");

        String response = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long accountId = objectMapper.readTree(response).get("account_id").asLong();

        mockMvc.perform(get("/accounts/" + accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value(accountId));
    }
}