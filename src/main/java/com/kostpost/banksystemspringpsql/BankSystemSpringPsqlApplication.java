package com.kostpost.banksystemspringpsql;

import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

import com.kostpost.banksystemspringpsql.bankData.AdminAccount;

@SpringBootApplication
public class BankSystemSpringPsqlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
        MainController controller = context.getBean(MainController.class);

        Scanner askAction = new Scanner(System.in);
        String doAction;

        do {
            System.out.println("""
                    1 - Login to account
                    2 - Create account
                    3 - Exit""");
            doAction = askAction.nextLine();

            switch (doAction) {

                case "1": {

                    Scanner askData = new Scanner(System.in);
                    String LogName;

                    UserAccount checkUser = null;
                    AdminAccount checkAdmin = null;
                    String kindAccount;

                    do {

                        System.out.println("Enter a name");
                        LogName = askData.nextLine();

                        checkUser = controller.findUserByName(LogName);
                        checkAdmin = controller.findAdminByName(LogName);

                        if (checkUser != null) {
                            kindAccount = "user";
                            break;
                        } else if (checkAdmin != null) {
                            kindAccount = "admin";
                            break;
                        } else {
                            System.out.println("Account with name '" + LogName + "' doesn't exist");
                        }

                    } while (true);

                    String password;


                    switch (kindAccount) {
                        case "user": {
                            do {
                                System.out.println("Enter a password for account " + checkUser.getAccountName());
                                password = askData.nextLine();

                                if (Objects.equals(password, checkUser.getAccountPassword()) && checkUser.getAccountStatus() == null) {
                                    System.out.println("\nLogin successful\n-----Welcome-----");
                                } else if (Objects.equals(password, checkUser.getAccountPassword()) && checkUser.getAccountStatus() != null) {
                                    System.out.println("Your account was banned");
                                } else {
                                    System.out.println("Wrong password");
                                }

                            } while (!Objects.equals(password, checkUser.getAccountPassword()));

							UserAccount currentUser = checkUser;

                            //controller.PrintUser(currentUser);

                            Scanner askUserActions = new Scanner(System.in);
                            String doUserActions;

                            do {
                                System.out.println("""
                                        1 - See account details
                                        2 - See account transactions
                                        3 - Transfer to card
                                        4 - Exit""");
                                doUserActions = askUserActions.nextLine();

                                switch (doUserActions) {

                                    case "1": {
										controller.PrintUser(currentUser);
                                        break;
                                    }
                                    case "2": {
                                        controller.print(controller.findAllUserTransaction(currentUser.getAccountName()), currentUser.getAccountName());
                                        break;
                                    }
                                    case "3": {
                                        Scanner askDataTransfer = new Scanner(System.in);
                                        String dataForTransfer;

                                        UserAccount recipient;

                                        do {
                                            System.out.print("Enter a card number for transaction");
                                            dataForTransfer = askDataTransfer.nextLine();

                                            recipient = controller.findByCardNumber(dataForTransfer);

                                            if(recipient == null){
                                                System.out.println("\nWrong card number");
                                            }

                                        } while (recipient == null);

                                        double transactionSum = 0;

                                        do{
                                            System.out.print("\nEnter a transaction sum: ");
                                            transactionSum = askDataTransfer.nextDouble();


                                        }while (transactionSum == 0);

                                        controller.createTransfer(currentUser, recipient,transactionSum);

                                        break;
                                    }
                                    case "4": {
										break;
                                    }

                                }

                            } while (!Objects.equals(doUserActions, "4"));

                            break;

                        }
                        case "admin": {

                            do {
                                System.out.println("Enter a password for account " + checkAdmin.getAccountName());
                                password = askData.nextLine();

                                if (Objects.equals(password, checkAdmin.getAccountPassword())) {
                                    System.out.println("\nLogin successful\n-----Welcome-----");
                                    controller.PrintAdmin(checkAdmin);
                                    controller.AdminPanel(checkAdmin);
                                    break;
                                } else {
                                    System.out.println("Wrong password");
                                }

                            } while (!Objects.equals(password, checkAdmin.getAccountPassword()));

                            break;
                        }
                    }

                    break;
                }

                case "2": {
                    Scanner askDataForNewAccount = new Scanner(System.in);

                    String newName;
                    UserAccount checkName = new UserAccount();
                    do {
                        System.out.print("Enter a name for new account: ");
                        newName = askDataForNewAccount.nextLine();

                        checkName = controller.findUserByName(newName);

                    } while (checkName != null);

                    String newPassword;
                    System.out.print("Enter a password for new account: ");
                    newPassword = askDataForNewAccount.nextLine();

                    UserAccount newUser = new UserAccount();
                    newUser.setAccountName(newName);
                    newUser.setAccountPassword(newPassword);

                    String generatedNumber;

                    do {

                        generatedNumber = newUser.generateCardNumber();
                        controller.findByCardNumber(generatedNumber);

                    } while (newUser.getCardNumber() != null);

                    newUser.setCardNumber(generatedNumber);

                    controller.addUser(newUser);

                    System.out.println("Account created successfully");

                    break;
                }

                default: {
                    if (!doAction.equals("3")) {

                    }
                    break;
                }

            }


        } while (!Objects.equals(doAction, "3"));


        context.close();
        SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
    }

}
