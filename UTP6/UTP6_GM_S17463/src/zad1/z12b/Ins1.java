package zad1.z12b;

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
            stmt = con.prepareStatement("DELETE FROM WYDAWCA WHERE NAME = ? OR WYDID = ?");
        } catch (Exception exc)  {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = new String[wyd.length]; // ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...

        int insCount = 0;   // ile rekordów wpisano
        try {
            for (int i = 0; i < ins.length; i++) {
                ((PreparedStatement)stmt).setString(1, wyd[i]);
                ((PreparedStatement)stmt).setInt(2, beginKey+insCount);
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