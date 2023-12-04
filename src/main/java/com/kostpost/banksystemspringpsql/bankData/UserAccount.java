package com.kostpost.banksystemspringpsql.bankData;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.kostpost.banksystemspringpsql.bankData.Account;
import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import com.kostpost.banksystemspringpsql.bankData.AdminAccount;


@Getter
@Setter
@Entity
@DiscriminatorValue("user")
public class UserAccount extends Account {

    @Column(name = "account_balance")
    private double accountBalance;

    private String accountStatus;

    @Column(name = "account_creation_date")
    private LocalDate account_creation_date;

    public UserAccount(){
        accountStatus = null;
        accountBalance = 1000.0;
        account_creation_date = LocalDate.now();
    }

}
