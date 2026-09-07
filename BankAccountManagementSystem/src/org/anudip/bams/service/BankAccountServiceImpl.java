package org.anudip.bams.service;

import java.util.List;

import org.anudip.bams.exception.AccountNotFoundException;
import org.anudip.bams.exception.InsufficientBalanceException;
import org.anudip.bams.model.BankAccount;
import org.anudip.bams.repository.BankAccountRepository;

public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAccount(BankAccount account) {
        repository.addAccount(account);
    }

    @Override
    public BankAccount findAccount(long accountNumber) {

        BankAccount account = repository.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found: " + accountNumber);
        }

        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return repository.getAllAccounts();
    }

    @Override
    public void updateAccount(BankAccount account) {
        findAccount(account.getAccountNumber());
        repository.updateAccount(account);
    }

    @Override
    public void deleteAccount(long accountNumber) {
        findAccount(accountNumber);
        repository.deleteAccount(accountNumber);
    }

    @Override
    public void deposit(long accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero.");
        }

        account.setBalance(account.getBalance() + amount);

        repository.updateAccount(account);
    }

    @Override
    public void withdraw(long accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance.");
        }

        account.setBalance(account.getBalance() - amount);

        repository.updateAccount(account);
    }
}