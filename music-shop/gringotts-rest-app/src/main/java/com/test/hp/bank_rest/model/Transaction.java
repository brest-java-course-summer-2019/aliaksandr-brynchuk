package com.test.hp.bank_rest.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    private Integer transactionId;

    private LocalDateTime transactionDateAndTime;

    private BigDecimal amount;

    private String status;

    @ManyToOne
    private Client client;
}
