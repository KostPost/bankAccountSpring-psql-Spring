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
public class transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operation_id")
    private int transaction_id;

    @Column(name = "transaction_sum")
    private double transaction_sum;

    @Column(name = "sender")
    private String sender;
    @Column(name = "sender_id")
    private int sender_id;
    @Column(name = "sender_balance_before_operation")
    private double sender_balance_before_transaction;
    @Column(name = "sender_balance_after_operation")
    private double sender_balance_after_transaction;

    @Column(name = "recipient")
    private String recipient;
    @Column(name = "recipient_id")
    private int recipient_id;
    @Column(name = "recipient_balance_before_operation")
    private double recipient_balance_before_transaction;
    @Column(name = "recipient_balance_after_operation")
    private double recipient_balance_after_transaction;

    @Column(name = "account_creation_date")
    private LocalDate transaction_data = null;

    public transactions(){

        if(transaction_data == null){
            transaction_data = LocalDate.now();
        }

    }


}