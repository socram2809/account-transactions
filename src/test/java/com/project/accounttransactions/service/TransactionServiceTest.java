package com.project.accounttransactions.service;

import com.project.accounttransactions.domain.Account;
import com.project.accounttransactions.domain.OperationType;
import com.project.accounttransactions.domain.Transaction;
import com.project.accounttransactions.exception.AccountNotFoundException;
import com.project.accounttransactions.repository.TransactionRepository;
import com.project.accounttransactions.vo.TransactionCreateRequestVO;
import com.project.accounttransactions.vo.TransactionResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void createTransaction_shouldSaveTransaction() {
        TransactionCreateRequestVO transactionCreateRequestVO = new TransactionCreateRequestVO();
        transactionCreateRequestVO.setAccountId(1L);
        transactionCreateRequestVO.setOperationTypeId(1);
        transactionCreateRequestVO.setAmount(BigDecimal.TEN);
        Account account = new Account("12345678900");
        ReflectionTestUtils.setField(account, "id", 1L);
        Transaction transaction = new Transaction(account, OperationType.NORMAL_PURCHASE, BigDecimal.TEN);
        Transaction transactionOnDB = new Transaction(account, OperationType.NORMAL_PURCHASE, BigDecimal.TEN);
        ReflectionTestUtils.setField(transactionOnDB, "id", 1L);
        when(accountService.findEntityById(1L)).thenReturn(account);
        when(transactionRepository.save(eq(transaction))).thenReturn(transactionOnDB);

        TransactionResponseVO result = transactionService.create(transactionCreateRequestVO);

        assertNotNull(result);
        assertEquals(BigDecimal.TEN.negate(), result.getAmount());
        assertEquals(1L, result.getAccountId());
        assertEquals(1, result.getOperationTypeId());
        assertEquals(1L, result.getId());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void createTransaction_shouldThrowIllegalArgumentException_whenOperationTypeIsInvalid() {
        TransactionCreateRequestVO transactionCreateRequestVO = new TransactionCreateRequestVO();
        transactionCreateRequestVO.setAccountId(1L);
        transactionCreateRequestVO.setOperationTypeId(999);
        transactionCreateRequestVO.setAmount(BigDecimal.TEN);

        when(accountService.findEntityById(1L)).thenReturn(new Account("12345678900"));

        assertEquals(
                "Invalid operation type id: 999",
                assertThrows(IllegalArgumentException.class, () -> transactionService.create(transactionCreateRequestVO)).getMessage()
        );
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void createTransaction_shouldThrowAccountNotFoundException_whenAccountDoesNotExist() {
        TransactionCreateRequestVO transactionCreateRequestVO = new TransactionCreateRequestVO();
        transactionCreateRequestVO.setAccountId(999L);
        transactionCreateRequestVO.setOperationTypeId(1);
        transactionCreateRequestVO.setAmount(BigDecimal.TEN);

        when(accountService.findEntityById(999L)).thenThrow(new AccountNotFoundException("Account not found with id: 999"));

        assertEquals(
                "Account not found with id: 999",
                assertThrows(AccountNotFoundException.class, () -> transactionService.create(transactionCreateRequestVO)).getMessage()
        );
        verify(transactionRepository, never()).save(any());
    }
}