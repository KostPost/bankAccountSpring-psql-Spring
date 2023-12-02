package com.kostpost.banksystemspringpsql.bankData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bankAccountRepository extends JpaRepository<bankAccount, Integer> {
    bankAccount findByAccountName(String AccName);
}
