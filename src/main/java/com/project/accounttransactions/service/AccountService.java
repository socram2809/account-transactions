package com.project.accounttransactions.service;

import com.project.accounttransactions.domain.Account;
import com.project.accounttransactions.exception.AccountNotFoundException;
import com.project.accounttransactions.repository.AccountRepository;
import com.project.accounttransactions.vo.AccountCreateRequestVO;
import com.project.accounttransactions.vo.AccountResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponseVO create(AccountCreateRequestVO accountCreateRequestVO) {
        log.info("Creating account with documentNumber: {}", accountCreateRequestVO.getDocumentNumber());

        Account account = new Account(accountCreateRequestVO.getDocumentNumber());
        Account savedAccount = accountRepository.save(account);

        log.info("Account created: {}", savedAccount);

        return AccountResponseVO.from(savedAccount);
    }

    public AccountResponseVO findById(Long id) {
        log.info("Finding account by id: {}", id);

        Account account = findEntityById(id);

        log.info("Account found: {}", account);

        return AccountResponseVO.from(account);
    }

    public Account findEntityById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }
}
