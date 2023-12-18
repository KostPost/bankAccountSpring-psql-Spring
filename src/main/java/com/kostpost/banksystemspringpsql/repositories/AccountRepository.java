package com.kostpost.banksystemspringpsql.repositories;

import com.kostpost.banksystemspringpsql.bankData.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}