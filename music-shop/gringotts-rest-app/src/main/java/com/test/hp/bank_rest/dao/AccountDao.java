package com.test.hp.bank_rest.dao;

import com.test.hp.bank_rest.model.BankAccount;
import com.test.hp.bank_rest.model.Client;
import com.test.hp.bank_rest.model.Transaction;

import java.math.BigDecimal;

public interface AccountDao {

    void createAccount(BankAccount account);

    BigDecimal checkBalance(Integer accountId);

    void operation(Client client, BigDecimal sum);
}
