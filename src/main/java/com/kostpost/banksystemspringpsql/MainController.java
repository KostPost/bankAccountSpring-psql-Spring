package com.kostpost.banksystemspringpsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostpost.banksystemspringpsql.bankData.bankAccount;
import com.kostpost.banksystemspringpsql.bankData.bankAccountRepository;
import com.kostpost.banksystemspringpsql.bankData.transactions;
import com.kostpost.banksystemspringpsql.bankData.bankAccountRepository;
@Controller
public class MainController {

    private final bankAccountRepository bankAccountRepository;
    //private final transactionRepository transactionRepository;

    @Autowired
    public MainController(bankAccountRepository bankAccountRepository){ // , transactionRepository transactionRepository){
        this.bankAccountRepository = bankAccountRepository;
        //this.transactionRepository = transactionRepository;
    }
    //--------------------------BANK ACCOUNT-------------------------------------------------
    public bankAccount findByAccountName(String nameToFind){
        return bankAccountRepository.findByAccountName(nameToFind);
    }
    public bankAccount createBankAccount(bankAccount newAccount){
        return bankAccountRepository.save(newAccount);
    }

    public void


    //--------------------------BANK ACCOUNT-------------------------------------------------




}
