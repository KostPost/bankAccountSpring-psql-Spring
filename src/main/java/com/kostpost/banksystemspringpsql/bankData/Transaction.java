package com.kostpost.banksystemspringpsql.bankData;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operation_id")
    private Long transactionID;

    @Column(name = "transaction_sum")
    private double transactionSum;

    @Column(name = "sender")
    private String sender;
    @Column(name = "sender_id")
    private Long senderID;
    @Column(name = "sender_balance_before_operation")
    private double senderBalanceBeforeTransaction;
    @Column(name = "sender_balance_after_operation")
    private double senderBalanceAfterTransaction;

    @Column(name = "recipient")
    private String recipient;
    @Column(name = "recipient_id")
    private Long recipientID;
    @Column(name = "recipient_balance_before_operation")
    private double recipientBalanceBeforeTransaction;
    @Column(name = "recipient_balance_after_operation")
    private double recipientBalanceAfterTransaction;

    @Column(name = "account_creation_date")
    private LocalDate transactionDate = null;

    public Transaction() {


        transactionDate = LocalDate.now();


    }


}