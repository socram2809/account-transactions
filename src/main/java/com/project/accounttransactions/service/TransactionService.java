package com.project.accounttransactions.service;

import com.project.accounttransactions.domain.Account;
import com.project.accounttransactions.domain.OperationType;
import com.project.accounttransactions.domain.Transaction;
import com.project.accounttransactions.repository.TransactionRepository;
import com.project.accounttransactions.vo.TransactionCreateRequestVO;
import com.project.accounttransactions.vo.TransactionResponseVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    @Transactional
    public TransactionResponseVO create(TransactionCreateRequestVO transactionCreateRequestVO) {
        log.info("Creating transaction for accountId: {}, operationTypeId: {}, amount: {}",
                transactionCreateRequestVO.getAccountId(),
                transactionCreateRequestVO.getOperationTypeId(),
                transactionCreateRequestVO.getAmount());

        Account account = accountService.findEntityById(transactionCreateRequestVO.getAccountId());
        OperationType operationType = OperationType.fromId(transactionCreateRequestVO.getOperationTypeId());

        Transaction transaction = new Transaction(account, operationType, transactionCreateRequestVO.getAmount());
        Transaction savedTransaction = transactionRepository.save(transaction);

        account.updateAvailableCreditLimit(transaction.getAmount());

        log.info("Transaction created: {}", savedTransaction);

        return TransactionResponseVO.from(savedTransaction);
    }
}
