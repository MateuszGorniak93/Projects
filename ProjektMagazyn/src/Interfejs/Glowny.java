package Interfejs;

import Magazyn.Dane;
import Obiekty.Osoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Glowny extends JFrame{
    
    public Glowny(){
        this.setPreferredSize(new Dimension(400,600));
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.getContentPane().setLayout(new BorderLayout());
        wybierzOsoba();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Dane.zapiszDane();
            }
        });
    }

    private void wybierzOsoba(){
        this.setTitle("Wybierz osobę");

        DefaultListModel<Osoba> modelOsoba = new DefaultListModel<>();
        JList<Osoba> ludzie = new JList<>(modelOsoba);
        for (Osoba o : Dane.OSOBA){
            modelOsoba.addElement(o);
        }
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(ludzie);
        JScrollPane scrollPane = new JScrollPane(listPanel);

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());
        JButton btn = new JButton("Ustaw osobę");
        panel.add(btn);

        btn.addActionListener(e -> {
            if (ludzie.getSelectedValue() != null){
                new Poboczny(ludzie.getSelectedValue());
            }
        });

        this.getContentPane().add(panel, BorderLayout.PAGE_START);
    }
}

