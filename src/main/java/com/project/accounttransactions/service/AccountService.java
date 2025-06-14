package com.project.accounttransactions.service;

import com.project.accounttransactions.domain.Account;
import com.project.accounttransactions.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public void create(String documentNumber) {
        log.info("Creating account with document number: {}", documentNumber);

        Account account = new Account(documentNumber);
        accountRepository.save(account);
    }
}
