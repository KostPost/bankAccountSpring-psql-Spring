package com.kostpost.banksystemspringpsql.repositories;

import com.kostpost.banksystemspringpsql.bankData.Transaction;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    ArrayList<Transaction> findBySender(String FirstName);
    ArrayList<Transaction> findByRecipient(String FirstName);

    @Query("SELECT t FROM Transaction t WHERE t.sender = :user or t.recipient = :user")
    ArrayList<Transaction> findTransactionsByUserAsBothSenderAndRecipient(String user);


}