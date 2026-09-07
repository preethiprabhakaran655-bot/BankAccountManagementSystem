package org.anudip.bams.service;

import java.util.List;

import org.anudip.bams.model.BankAccount;

public interface BankAccountService {

    void createAccount(BankAccount account);

    BankAccount findAccount(long accountNumber);

    List<BankAccount> getAllAccounts();

    void updateAccount(BankAccount account);

    void deleteAccount(long accountNumber);

    void deposit(long accountNumber, double amount);

    void withdraw(long accountNumber, double amount);
}
