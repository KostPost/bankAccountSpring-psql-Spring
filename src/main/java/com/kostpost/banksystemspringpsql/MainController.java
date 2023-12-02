package com.kostpost.banksystemspringpsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostpost.banksystemspringpsql.bankData.bankAccount;
import com.kostpost.banksystemspringpsql.bankData.bankAccountRepository;

import java.util.List;

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
    public List<bankAccount> FindAllBankAccounts(){
        return bankAccountRepository.findAll();
    }
    public bankAccount BankFindByID(int ID){
        return bankAccountRepository.findById(ID).orElse(null);
    }

    public void PrintBank(bankAccount bankAccount){
        System.out.println("----------------------------");
        System.out.println("ID: " + bankAccount.getAccount_id());
        System.out.println("ID: " + bankAccount.getAccountName());
        System.out.println("ID: " + bankAccount.getAccountPassword());
        System.out.println("ID: " + bankAccount.getAccountBalance());
        System.out.println("ID: " + bankAccount.getAccount_creation_date());
        System.out.println("----------------------------");
    }
    public void PrintBank(List<bankAccount> bankAccounts){
        System.out.println("----------------------------");
        for(bankAccount accPrint : bankAccounts){
            System.out.println("ID: " + accPrint.getAccount_id());
            System.out.println("ID: " + accPrint.getAccountName());
            System.out.println("ID: " + accPrint.getAccountPassword());
            System.out.println("ID: " + accPrint.getAccountBalance());
            System.out.println("ID: " + accPrint.getAccount_creation_date());
            System.out.println("----------------------------");
        }
    }




    //--------------------------BANK ACCOUNT-------------------------------------------------




}
