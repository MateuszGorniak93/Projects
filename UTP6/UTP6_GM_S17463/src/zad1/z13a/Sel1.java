package zad1.z13a;

import java.sql.*;

public class Sel1 {
    static public void main(String[] args) {
        new Sel1();
    }

    Statement stmt;

    Sel1() {
        Connection con = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://127.0.0.1:1527/ksidb");
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }

        String sel = "SELECT a.name, p.tytul, p.rok, p.cena FROM AUTOR a INNER JOIN POZYCJE p on a.AUTID = p.AUTID WHERE p.rok >= 2000 and p.cena > 30";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sel);
            while (rs.next()) {
                String nazwisko = rs.getString("name");
                String tytul = rs.getString("tytul");
                String rok = rs.getString("rok");
                String cena = rs.getString("cena");

                System.out.println("Autor: " + nazwisko);
                System.out.println("Tytuł: " + tytul);
                System.out.println("Rok: " + rok);
                System.out.println("Cena: " + cena);
                System.out.println();
            }
            stmt.close();
            con.close();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }

    }
}