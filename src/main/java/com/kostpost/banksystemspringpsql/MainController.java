package com.kostpost.banksystemspringpsql;

import com.kostpost.banksystemspringpsql.bankData.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {


    private final UserAccountRepository userAccountRepository;
    private final AdminAccountRepository adminAccountRepository;
    @Autowired
    public MainController(UserAccountRepository userAccountRepository, AdminAccountRepository adminAccountRepository){
        this.userAccountRepository = userAccountRepository;
        this.adminAccountRepository = adminAccountRepository;
    }


    //--------------------------BANK ACCOUNT-------------------------------------------------


    public void PrintUser(UserAccount printAccount){
        System.out.println("----------------------------");
        System.out.println("ID: " + printAccount.getId());
        System.out.println("Name: " + printAccount.getAccountName());
        System.out.println("Password: " + printAccount.getAccountPassword());
        System.out.println("ID: " + printAccount.getAccountBalance());
        System.out.println("ID: " + printAccount.getAccount_creation_date());
        System.out.println("----------------------------");
    }
    public void PrintUser(List<UserAccount> accountsPrint){

        System.out.println("----------------------------");

        for(UserAccount accPrint : accountsPrint){
            System.out.println("ID: " + accPrint.getId());
            System.out.println("ID: " + accPrint.getAccountName());
            System.out.println("ID: " + accPrint.getAccountPassword());
            System.out.println("ID: " + accPrint.getAccountBalance());
            System.out.println("ID: " + accPrint.getAccount_creation_date());
            System.out.println("----------------------------");
        }
    }

    public UserAccount findUserByName(String name){
        return userAccountRepository.findByAccountName(name);
    }

    public UserAccount addUser(UserAccount newUser){
        return userAccountRepository.save(newUser);
    }

    //--------------------------BANK ACCOUNT-------------------------------------------------



    //--------------------------BANK ADMIN ACCOUNT-------------------------------------------------
    public void PrintAdmin(AdminAccount printAccount){
        System.out.println("----------------------------");
        System.out.println("ID: " + printAccount.getId());
        System.out.println("Name: " + printAccount.getAccountName());
        System.out.println("Password: " + printAccount.getAccountPassword());
        System.out.println("Level: " + printAccount.getLevel());
        System.out.println("----------------------------");
    }
    public void PrintAdmin(List<AdminAccount> accountsPrint){

        System.out.println("----------------------------");

        for(AdminAccount accPrint : accountsPrint){
            System.out.println("ID: " + accPrint.getId());
            System.out.println("ID: " + accPrint.getAccountName());
            System.out.println("ID: " + accPrint.getAccountPassword());
            System.out.println("Level: " + accPrint.getLevel());
            System.out.println("----------------------------");
        }
    }

    public AdminAccount findAdminByName(String name){
        return adminAccountRepository.findByAccountName(name);
    }

    public AdminAccount addAdmin(AdminAccount newAdminUser){
        return adminAccountRepository.save(newAdminUser);
    }


    //--------------------------BANK ADMIN ACCOUNT-------------------------------------------------




}
