package com.kostpost.banksystemspringpsql;

import com.kostpost.banksystemspringpsql.bankData.*;
import com.kostpost.banksystemspringpsql.repositories.AdminAccountRepository;
import com.kostpost.banksystemspringpsql.repositories.UserAccountRepository;
import com.kostpost.banksystemspringpsql.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import java.util.List;

@Controller
public class MainController {


    private final UserAccountRepository userAccountRepository;
    private final AdminAccountRepository adminAccountRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public MainController(UserAccountRepository userAccountRepository, AdminAccountRepository adminAccountRepository
                          , TransactionRepository transactionRepository) {
        this.userAccountRepository = userAccountRepository;
        this.adminAccountRepository = adminAccountRepository;
        this.transactionRepository = transactionRepository;

    }


    //--------------------------BANK ACCOUNT-------------------------------------------------

    public void PrintUser(UserAccount printAccount) {
        System.out.println("----------------------------");
        System.out.println("ID: " + printAccount.getId());
        System.out.println("Name: " + printAccount.getAccountName());
        System.out.println("Password: " + printAccount.getAccountPassword());
        System.out.println("Balance: " + printAccount.getAccountBalance());
        System.out.println("Creating Date: " + printAccount.getAccount_creation_date());
        System.out.println("----------------------------");
    }

    public void PrintUser(List<UserAccount> accountsPrint) {

        System.out.println("----------------------------");

        for (UserAccount accPrint : accountsPrint) {
            System.out.println("ID: " + accPrint.getId());
            System.out.println("Name: " + accPrint.getAccountName());
            System.out.println("Password: " + accPrint.getAccountPassword());
            System.out.println("Balance: " + accPrint.getAccountBalance());
            System.out.println("Creating Date: " + accPrint.getAccount_creation_date());
            System.out.println("----------------------------");
        }
    }

    public UserAccount findByCardNumber(String number){
        return userAccountRepository.findByCardNumber(number);
    }
    public UserAccount findUserByName(String name) {
        return userAccountRepository.findByAccountName(name);
    }

    public UserAccount findByID(Long ID) {
        return userAccountRepository.findById(ID).orElse(null);
    }

    public List<UserAccount> findAllUsers() {
        return userAccountRepository.findAll();
    }

    public void addUser(UserAccount newUser) {
        userAccountRepository.save(newUser);
    }


    //--------------------------BANK ACCOUNT-------------------------------------------------

    //--------------------------TRANSACTION-------------------------------------------------
    public void createTransfer(UserAccount sender, UserAccount recipient, double transactionSum){

        if(transactionSum < sender.getAccountBalance()) {

            Long senderID = sender.getId(), recipientID = recipient.getId();

            Transaction newTransaction = new Transaction();

            newTransaction.setTransactionSum(transactionSum);

            newTransaction.setSender(sender.getAccountName());
            newTransaction.setSenderID(sender.getId());
            newTransaction.setSenderBalanceBeforeTransaction(sender.getAccountBalance());

            newTransaction.setRecipient(recipient.getAccountName());
            newTransaction.setRecipientID(recipient.getId());
            newTransaction.setRecipientBalanceBeforeTransaction(recipient.getAccountBalance());

            moneyTransfer(sender,recipient,transactionSum);


            sender = findByID(senderID);
            recipient = findByID(recipientID);

            newTransaction.setSenderBalanceAfterTransaction(sender.getAccountBalance());
            newTransaction.setRecipientBalanceAfterTransaction(recipient.getAccountBalance());

            addTransaction(newTransaction);


        }
        else{
            System.out.println("Insufficient funds for the money transfer\n" +
                    "Transaction sum: " + transactionSum + ", Your balance: " + sender.getAccountBalance());
        }
    }
    private void moneyTransfer(UserAccount sender, UserAccount recipient, double transactionSum) {

        sender.setAccountBalance(sender.getAccountBalance() - transactionSum);
        userAccountRepository.save(sender);

        recipient.setAccountBalance(recipient.getAccountBalance() + transactionSum);
        userAccountRepository.save(recipient);
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    //--------------------------TRANSACTION-------------------------------------------------





    //--------------------------BANK ADMIN ACCOUNT-------------------------------------------------
    public void PrintAdmin(AdminAccount printAccount) {
        System.out.println("----------------------------");
        System.out.println("ID: " + printAccount.getId());
        System.out.println("Name: " + printAccount.getAccountName());
        System.out.println("Password: " + printAccount.getAccountPassword());
        System.out.println("Level: " + printAccount.getLevel());
        System.out.println("----------------------------");
    }

    public void PrintAdmin(List<AdminAccount> accountsPrint) {

        System.out.println("----------------------------");

        for (AdminAccount accPrint : accountsPrint) {
            System.out.println("ID: " + accPrint.getId());
            System.out.println("ID: " + accPrint.getAccountName());
            System.out.println("ID: " + accPrint.getAccountPassword());
            System.out.println("Level: " + accPrint.getLevel());
            System.out.println("----------------------------");
        }
    }

    public AdminAccount findAdminByName(String name) {
        return adminAccountRepository.findByAccountName(name);
    }

    public AdminAccount addAdmin(AdminAccount newAdminUser) {
        return adminAccountRepository.save(newAdminUser);
    }

    //--------------------------BANK ADMIN ACCOUNT-------------------------------------------------

    //--------------------------BANK ADMIN ACCOUNT ACTIONS-----------------------------------------

    public void AdminPanel(AdminAccount adminAccount) {

        Long UserByID;
        Scanner askID = new Scanner(System.in);
        UserAccount userAccount = null;

        String[] leve1 = new String[]{"See users", "See tran"};
        String[] leve2 = new String[]{"See users", "See tran", "Ban accounts"};
        String[] leve3 = new String[]{"See users", "See tran", "Change account status", "Change Admin Level"};

        String[] availableActions;
        if (adminAccount.getLevel() == 1) availableActions = leve1;
        else if (adminAccount.getLevel() == 2) availableActions = leve2;
        else availableActions = leve3;


        Scanner askAction = new Scanner(System.in);
        int doAction, i;

        do {
            System.out.println("----------------------------");
            System.out.println("Choice action");

            i = 0;
            for (; i < availableActions.length; i++) {
                System.out.println(i + 1 + " - " + availableActions[i]);
            }
            System.out.println(i + 1 + " - Exit");
            System.out.println("----------------------------");

            doAction = askAction.nextInt();
            i += 1;


            switch (doAction) {

                case 1: {
                    if (adminAccount.getLevel() >= 1) {
                        PrintUser(findAllUsers());
                    }
                    break;
                }

                case 2: {
                    // see tran
                    if (adminAccount.getLevel() >= 1) {
                    }
                    break;
                }

                case 3: {
                    //ban
                    if (adminAccount.getLevel() >= 2) {
                        do {
                            System.out.print("Enter a ID account: ");
                            UserByID = askID.nextLong();

                            userAccount = findByID(UserByID);

                            //PrintUser(userAccount);

                        } while (userAccount == null);

                        Scanner askUserAction = new Scanner(System.in);
                        String actionStatus = null;

                        do {
                            System.out.println("""
                                    1 - Clear status
                                    2 - Add status
                                    3 - Exit""");

                            actionStatus = askUserAction.nextLine();

                            switch (actionStatus) {

                                case "1": {
                                    if (userAccount.getAccountStatus() != null) {
                                        userAccount.setAccountStatus(null);
                                    } else {
                                        System.out.println("this account have no limits");
                                    }
                                    break;
                                }

                                case "2": {
                                    System.out.println();
                                }

                            }

                        } while (!Objects.equals(actionStatus, "3"));

                    }
                    break;
                }

                case 4: {
                    //change admin level
                    if (adminAccount.getLevel() >= 3) {

                    }
                    break;
                }

            }

        } while (doAction != i);


    }

}


//--------------------------BANK ADMIN ACCOUNT ACTIONS-----------------------------------------






