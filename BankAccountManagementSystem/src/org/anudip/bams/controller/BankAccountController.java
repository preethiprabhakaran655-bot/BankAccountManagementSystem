
package org.anudip.bams.controller;

import java.util.List;

import org.anudip.bams.exception.AccountNotFoundException;
import org.anudip.bams.exception.InsufficientBalanceException;
import org.anudip.bams.model.BankAccount;
import org.anudip.bams.service.BankAccountService;
import org.anudip.bams.util.InputUtil;

public class BankAccountController {

    private BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    public void start() {

        while (true) {

            System.out.println("\n================================");
            System.out.println(" BANK ACCOUNT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. View All Accounts");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Update Account");
            System.out.println("7. Delete Account");
            System.out.println("8. Exit");
            System.out.println("================================");

            int choice = InputUtil.getInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        viewAccount();
                        break;

                    case 3:
                        viewAllAccounts();
                        break;

                    case 4:
                        deposit();
                        break;

                    case 5:
                        withdraw();
                        break;

                    case 6:
                        updateAccount();
                        break;

                    case 7:
                        deleteAccount();
                        break;

                    case 8:
                        System.out.println("Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice.");

                }

            } catch (AccountNotFoundException |
                     InsufficientBalanceException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Something went wrong.");
                e.printStackTrace();
            }
        }
    }

    private void createAccount() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        String name =
                InputUtil.getString("Enter account holder name: ");

        String type =
                InputUtil.getString("Enter account type: ");

        double balance =
                InputUtil.getDouble("Enter initial balance: ");

        BankAccount account =
                new BankAccount(
                        accountNumber,
                        name,
                        type,
                        balance
                );

        service.createAccount(account);

        System.out.println("Account created successfully.");
    }

    private void viewAccount() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        BankAccount account =
                service.findAccount(accountNumber);

        System.out.println("\n" + account);
    }

    private void viewAllAccounts() {

        List<BankAccount> accounts =
                service.getAllAccounts();

        if (accounts.isEmpty()) {

            System.out.println("No accounts found.");

        } else {

            for (BankAccount account : accounts) {

                System.out.println("\n------------------------");
                System.out.println(account);
            }
        }
    }

    private void deposit() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        double amount =
                InputUtil.getDouble("Enter deposit amount: ");

        service.deposit(accountNumber, amount);

        System.out.println("Money deposited successfully.");
    }

    private void withdraw() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        double amount =
                InputUtil.getDouble("Enter withdrawal amount: ");

        service.withdraw(accountNumber, amount);

        System.out.println("Money withdrawn successfully.");
    }

    private void updateAccount() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        service.findAccount(accountNumber);

        String name =
                InputUtil.getString("Enter new name: ");

        String type =
                InputUtil.getString("Enter new account type: ");

        double balance =
                InputUtil.getDouble("Enter new balance: ");

        BankAccount account =
                new BankAccount(
                        accountNumber,
                        name,
                        type,
                        balance
                );

        service.updateAccount(account);

        System.out.println("Account updated successfully.");
    }

    private void deleteAccount() {

        long accountNumber =
                InputUtil.getLong("Enter account number: ");

        service.deleteAccount(accountNumber);

        System.out.println("Account deleted successfully.");
    }
}