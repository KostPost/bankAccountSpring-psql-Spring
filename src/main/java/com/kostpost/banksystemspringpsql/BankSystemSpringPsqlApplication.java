package com.kostpost.banksystemspringpsql;

import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

import com.kostpost.banksystemspringpsql.bankData.Account;
import com.kostpost.banksystemspringpsql.bankData.UserAccount;
import com.kostpost.banksystemspringpsql.bankData.AdminAccount;
import com.kostpost.banksystemspringpsql.bankData.AdminAccountRepository;

@SpringBootApplication
public class BankSystemSpringPsqlApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
		MainController controller = context.getBean(MainController.class);

		Scanner askAction = new Scanner(System.in);
		String doAction;

		do{
			System.out.println("""
					1 - Login to account
					2 - Create account
					3 - Exit""");
			doAction = askAction.nextLine();
			
			switch (doAction){

				case "1":{

					Scanner askData = new Scanner(System.in);
					String LogName;

					UserAccount checkUser = null;
					AdminAccount checkAdmin = null;
					String kindAccount;

					do{

						System.out.println("Enter a name");
						LogName = askData.nextLine();

						checkUser = controller.findUserByName(LogName);
						checkAdmin = controller.findAdminByName(LogName);

						if(checkUser != null){
							kindAccount = "user";
							break;
						}
						else if(checkAdmin != null){
							kindAccount = "admin";
							break;
						}
						else{
							System.out.println("Account with name '" + LogName + "' doesn't exist");
						}

					}while (true);

					String password;

					switch (kindAccount){
						case "user":{

							do{
								System.out.println("Enter a password for account " + checkUser.getAccountName());
								password = askData.nextLine();

								if(Objects.equals(password, checkUser.getAccountPassword()) && checkUser.getAccountStatus() == null) {
									System.out.println("\nLogin successful\n-----Welcome-----");
								}
								else if(Objects.equals(password, checkUser.getAccountPassword()) && checkUser.getAccountStatus() != null) {
									System.out.println("Your account was banned");
								}
								else{
									System.out.println("Wrong password");
								}

							}while (!Objects.equals(password, checkUser.getAccountPassword()));

							controller.PrintUser(checkUser);

							break;

						}
						case "admin":{

							do{
								System.out.println("Enter a password for account " + checkAdmin.getAccountName());
								password = askData.nextLine();

								if(Objects.equals(password, checkAdmin.getAccountPassword())){
									System.out.println("\nLogin successful\n-----Welcome-----");
									controller.PrintAdmin(checkAdmin);
									controller.AdminPanel(checkAdmin);
									break;
								}
								else{
									System.out.println("Wrong password");
								}

							}while (!Objects.equals(password, checkAdmin.getAccountPassword()));

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

					}while (checkName != null);

					String newPassword;
					System.out.print("Enter a password for new account: ");
					newPassword = askDataForNewAccount.nextLine();

					UserAccount newUser = new UserAccount();
					newUser.setAccountName(newName);
					newUser.setAccountPassword(newPassword);

					controller.addUser(newUser);

					System.out.println("Account created successfully");

					break;
				}

				default:{
					if(!doAction.equals("3")){

					}
					break;
				}

			}


		}while (!Objects.equals(doAction, "3"));


		context.close();
		SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
	}

}
