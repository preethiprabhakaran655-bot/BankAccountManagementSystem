package org.anudip.bams.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.anudip.bams.database.DBConnection;
import org.anudip.bams.model.BankAccount;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    @Override
    public void addAccount(BankAccount account) {

        String sql = "INSERT INTO bank_account " +
                     "(account_number, account_holder_name, account_type, balance) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, account.getAccountNumber());
            ps.setString(2, account.getAccountHolderName());
            ps.setString(3, account.getAccountType());
            ps.setDouble(4, account.getBalance());

            ps.executeUpdate();

            System.out.println("Account created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankAccount getAccount(long accountNumber) {

        String sql = "SELECT * FROM bank_account WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new BankAccount(
                    rs.getLong("account_number"),
                    rs.getString("account_holder_name"),
                    rs.getString("account_type"),
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<BankAccount> getAllAccounts() {

        List<BankAccount> accounts = new ArrayList<>();

        String sql = "SELECT * FROM bank_account";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                BankAccount account = new BankAccount(
                    rs.getLong("account_number"),
                    rs.getString("account_holder_name"),
                    rs.getString("account_type"),
                    rs.getDouble("balance")
                );

                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public void updateAccount(BankAccount account) {

        String sql = "UPDATE bank_account SET " +
                     "account_holder_name = ?, " +
                     "account_type = ?, " +
                     "balance = ? " +
                     "WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, account.getAccountHolderName());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());
            ps.setLong(4, account.getAccountNumber());

            ps.executeUpdate();

            System.out.println("Account updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(long accountNumber) {

        String sql = "DELETE FROM bank_account WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountNumber);

            ps.executeUpdate();

            System.out.println("Account deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}