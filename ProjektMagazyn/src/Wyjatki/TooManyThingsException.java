package Wyjatki;

import javax.swing.*;

public class TooManyThingsException extends Exception{
    
    public TooManyThingsException()
    {
        JOptionPane.showMessageDialog(null, "Nie ma już miejsca w tym pokoju.");
    } 
}
