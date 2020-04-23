package com.test.hp.bank_rest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankAccount account;

//    @JoinTable(
//            name="client_transactions",
//            joinColumns = @JoinColumn(name="client_id"),
//            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private List<Transaction> transactions;

    public Client() {
    }

    public Client(Integer clientId, String firstName, String lastName, BankAccount account, List<Transaction> transactions) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.transactions = transactions;
    }

    public Client(String firstName, String lastName, BankAccount account, List<Transaction> transactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.transactions = transactions;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
