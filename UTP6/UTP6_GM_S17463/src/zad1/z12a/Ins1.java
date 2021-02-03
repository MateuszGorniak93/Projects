package zad1.z12a;

import java.sql.*;

public class Ins1 {

    static public void main(String[] args) {
        new Ins1();
    }

    Statement stmt;

    Ins1()  {
        Connection con = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://127.0.0.1:1527/ksidb");
            stmt = con.createStatement();
        } catch (Exception exc)  {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = new String[wyd.length]; // ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...
        for (int i = 0; i < ins.length; i++) {
            ins[i] = "INSERT INTO WYDAWCA (WYDID, NAME) VALUES (" + beginKey++ + ", '" + wyd[i] + "')";
        }
        int insCount = 0;   // ile rekordów wpisano
        try {
            for (int i = 0; i < ins.length; i++) {
                stmt.executeUpdate(ins[i]);
                insCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}