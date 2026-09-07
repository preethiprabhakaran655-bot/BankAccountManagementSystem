package org.anudip.bams.repository;

import java.util.List;
import org.anudip.bams.model.BankAccount;

public interface BankAccountRepository {

    void addAccount(BankAccount account);

    BankAccount getAccount(long accountNumber);

    List<BankAccount> getAllAccounts();

    void updateAccount(BankAccount account);

    void deleteAccount(long accountNumber);
}