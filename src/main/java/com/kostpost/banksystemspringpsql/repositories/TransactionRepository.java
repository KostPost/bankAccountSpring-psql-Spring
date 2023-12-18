package com.kostpost.banksystemspringpsql.repositories;

import com.kostpost.banksystemspringpsql.bankData.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    ArrayList<Transaction> findBySender(String FirstName);
    ArrayList<Transaction> findByRecipient(String FirstName);

}