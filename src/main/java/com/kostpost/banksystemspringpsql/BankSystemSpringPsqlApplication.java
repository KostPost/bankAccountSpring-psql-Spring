package com.kostpost.banksystemspringpsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Scanner;

import com.kostpost.banksystemspringpsql.bankData.bankAccount;

@SpringBootApplication
public class BankSystemSpringPsqlApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
		MainController controller = context.getBean(MainController.class);

		Scanner askAction = new Scanner(System.in);
		String doAction;
		do{
			System.out.println("1 - Check accounts || Find account\n2 - Create account");
			doAction = askAction.nextLine();

			switch (doAction){

				case "1": {
					Scanner findAccount = new Scanner(System.in);
					System.out.println("qwe");


					break;
				}

				case "2":{
					Scanner askDataForNewAccount = new Scanner(System.in);

					String newName;
					bankAccount checkName = new bankAccount();
					do {
						System.out.print("Enter a name for new account: ");
						newName = askDataForNewAccount.nextLine();

						checkName = controller.findByAccountName(newName);

					}while (checkName != null);

					String newPassword;
					System.out.print("Enter a password for new account: ");
					newPassword = askDataForNewAccount.nextLine();

					bankAccount newAccount = new bankAccount();
					newAccount.setAccountName(newName);
					newAccount.setAccountPassword(newPassword);

					controller.createBankAccount(newAccount);

					break;

				}

				default:{

				}

			}



		}while (!doAction.equals("6"));

		context.close();
		SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
	}

}
