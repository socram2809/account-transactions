package com.project.accounttransactions.handler;

import com.project.accounttransactions.service.AccountService;
import com.project.accounttransactions.vo.AccountCreateRequestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class RestErrorHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Test
    void shouldReturnBadRequest_whenValidationFails() throws Exception {
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("must not be null")));
    }

    @Test
    void shouldReturnBadRequest_whenIllegalArgumentExceptionThrown() throws Exception {
        AccountCreateRequestVO accountCreateRequest = new AccountCreateRequestVO();
        accountCreateRequest.setDocumentNumber("12345678900");
        accountService.create(accountCreateRequest);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"account_id\":1,\"operation_type_id\":999,\"amount\":10}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void shouldReturnNotFound_whenAccountNotFoundExceptionThrown() throws Exception {
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"account_id\":9999,\"operation_type_id\":1,\"amount\":10}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}