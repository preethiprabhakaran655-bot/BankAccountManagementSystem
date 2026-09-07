package org.anudip.bams.model;

public class BankAccount {

    private long accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(long accountNumber, String accountHolderName,
                       String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolderName +
               "\nAccount Type: " + accountType +
               "\nBalance: " + balance;
    }
}