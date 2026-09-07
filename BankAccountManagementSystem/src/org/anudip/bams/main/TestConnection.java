package org.anudip.bams.main;

import java.sql.Connection;

import org.anudip.bams.database.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.println("MySQL Connected Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}