package com.test.hp.bank_rest.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public BankAccount() {
    }

    public BankAccount(Integer accountId, BigDecimal balance, Client client) {
        this.accountId = accountId;
        this.balance = balance;
        this.client = client;
    }

    public BankAccount(BigDecimal balance, Client client) {
        this.balance = balance;
        this.client = client;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountId.equals(that.accountId) &&
                balance.equals(that.balance) &&
                client.equals(that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, client);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}
