package com.kostpost.banksystemspringpsql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostpost.banksystemspringpsql.bankData.Account;
import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import com.kostpost.banksystemspringpsql.bankData.AdminAccount;

import java.util.List;


@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
    List<AdminAccount> findAll();

    AdminAccount findByAccountName(String accountName);
}