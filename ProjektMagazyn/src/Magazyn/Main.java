package Magazyn;

import Interfejs.Glowny;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Dane.wczytajDane();
        new Thread(() -> {
            while(true){
                try{
                    Thread.sleep(10000);
                    Dane.clean();
                    Dane.appDate = Dane.appDate.plusDays(1);
                    System.out.println(Dane.appDate.toString());
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        SwingUtilities.invokeLater(()-> new Glowny());
    }
    
}
