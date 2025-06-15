package com.project.accounttransactions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AccountTransactionsApplicationTests {

	@Test
	void main_runsWithoutExceptions() {
		assertDoesNotThrow(() -> AccountTransactionsApplication.main(new String[]{}));
	}
}
