package com.kostpost.banksystemspringpsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;
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
			System.out.println("""
                    1 - Check accounts || Find account
                    2 - Create account
                    3 - Log in
                    4 - Exit""");
			doAction = askAction.nextLine();

			switch (doAction){

				case "1": {
					Scanner findAccount = new Scanner(System.in);
					String actionToAccount = null;
					do {

						Scanner askDataToFind = new Scanner(System.in);

						System.out.println("1 - See all account\n2 - Find by ID\n3 - Find by Account Name\n4 - Exit");
						actionToAccount = findAccount.nextLine();
						switch (actionToAccount) {
							case "1": {
								System.out.println();
								controller.PrintBank(controller.FindAllBankAccounts());
								System.out.println();
								break;
							}

							case "2": {
								System.out.print("Enter a ID to find: ");
								int idToFind = askDataToFind.nextInt();

								bankAccount findID = controller.BankFindByID(idToFind);

								if(findID != null){
									System.out.println();
									System.out.println("\nYour account: ");
									controller.PrintBank(findID);
									System.out.println();
								}else{
									System.out.println("Account with ID: '" + idToFind + "' doesn't exist");
								}

								break;
							}

							case "3": {

								System.out.println("Enter a name to find");
								String nameToFind = askDataToFind.nextLine();

								bankAccount accToFind = controller.findByAccountName(nameToFind);

								if(accToFind != null){
									System.out.println();
									System.out.println("\nYour account: ");
									controller.PrintBank(accToFind);
									System.out.println();
								}else{
									System.out.println("Account with Name: '" + nameToFind + "' doesn't exist");
								}

								break;
							}

							default:{
								if(!actionToAccount.equals("4")) {
									System.out.println("Error");
								}
							}
						}

					}while (!Objects.equals(actionToAccount, "4"));


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

				case "3":{



				}

				default:{

				}

			}



		}while (!doAction.equals("4"));

		context.close();
		SpringApplication.run(BankSystemSpringPsqlApplication.class, args);
	}

}
