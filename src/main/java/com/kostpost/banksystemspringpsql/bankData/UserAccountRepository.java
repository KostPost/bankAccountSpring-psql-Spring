package com.kostpost.banksystemspringpsql.bankData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostpost.banksystemspringpsql.bankData.Account;
import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import com.kostpost.banksystemspringpsql.bankData.AdminAccount;

import java.util.List;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByAccountName(String accountName);

}
