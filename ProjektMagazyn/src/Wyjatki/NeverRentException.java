package Wyjatki;

import javax.swing.*;

public class NeverRentException extends Exception{
    
    public NeverRentException()
    {
        JOptionPane.showMessageDialog(null, "Osoba jeszcze nic nie wynajęła.");
    }  
}
