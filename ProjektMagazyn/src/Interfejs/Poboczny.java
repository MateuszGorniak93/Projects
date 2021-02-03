package Interfejs;

import Magazyn.Dane;
import Wyjatki.NeverRentException;
import Obiekty.Osoba;
import Obiekty.Pomieszczenie;
import Obiekty.Przedmiot;
import Obiekty.Wynajem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;


public class Poboczny extends JFrame{
    
    private Osoba taOsoba = null;

    public Poboczny(Osoba taOsoba){
        this.setPreferredSize(new Dimension(800,600));
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.pack();

        this.taOsoba = taOsoba;
        osoba();
    }


    private void osoba(){
        clear();
        this.setTitle(taOsoba.toString());

        JPanel panel = new JPanel(new FlowLayout());
        JButton wolnePomieszczenie = new JButton("Wolne pomieszczenia");
        JButton wybierzPomieszczenie = new JButton("Wybierz pomieszczenie");

        panel.add(wolnePomieszczenie);
        panel.add(wybierzPomieszczenie);
        this.getContentPane().add(panel, BorderLayout.PAGE_START);

        String[] cols = {"ID", "Powierzchnia", "Data od", "Czas wynajmu"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0);

        Dane.wczytajWynajem(taOsoba,dtm);
        JTable table = new JTable(dtm);
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);


        wolnePomieszczenie.addActionListener(e -> {
            wolnePomieszczenia();
        });

        wybierzPomieszczenie.addActionListener(e -> {
            if (table.getSelectedRow() > -1){
                int pomieszczenieId = Integer.parseInt(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                pomieszczenie(pomieszczenieId);
            }
        });

        this.revalidate();

        if (taOsoba.getPierwszeWypozyczenie() == null){
            try {
                throw new NeverRentException();
            } catch (NeverRentException e) {
                e.printStackTrace();
            }
        }
    }

    private void pomieszczenie(int pomieszczenieId) {
        clear();
        Pomieszczenie pomieszczenie = Dane.getPomieszczeniePoId(pomieszczenieId);
        this.setTitle(taOsoba.toString() + " Pomieszczenie nr. " + pomieszczenie.getId());

        JPanel tPanel = new JPanel(new GridLayout(2,4));
        JPanel bPanel = new JPanel();

        String[] cols = {"ID", "Nazwa", "Rozmiar"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0);
        Dane.wczytajPrzedmiot(dtm, pomieszczenieId);
        JTable table = new JTable(dtm);
        bPanel.add(new JScrollPane(table));

        JButton back = new JButton("Powrót");
        JButton add = new JButton("Dodaj");
        JButton remove = new JButton("Usuń");

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(back);
        buttons.add(add);
        buttons.add(remove);

        JLabel Naz = new JLabel("Nazwa");
        JLabel Pod = new JLabel("Podstawa");
        JLabel Sze = new JLabel("Szerokość");
        JLabel Wys = new JLabel("Wysokość");

        JTextField nazwa = new JTextField();
        JTextField podstawa = new JTextField();
        JTextField szerokosc = new JTextField();
        JTextField wysokosc = new JTextField();

        tPanel.add(Naz);
        tPanel.add(Pod);
        tPanel.add(Sze);
        tPanel.add(Wys);
        tPanel.add(nazwa);
        tPanel.add(podstawa);
        tPanel.add(szerokosc);
        tPanel.add(wysokosc);

        
        back.addActionListener(e -> {
            osoba();
        });

        add.addActionListener(e -> {
            float a = Float.parseFloat(podstawa.getText());
            float b = Float.parseFloat(szerokosc.getText());
            float h = Float.parseFloat(wysokosc.getText());

            if(a*b*h > 0){
                Dane.dodajPrzedmiot(new Przedmiot(nazwa.getText(), a, b, h, pomieszczenieId));
                pomieszczenie(pomieszczenieId);
            }
        });

        remove.addActionListener( e -> {
            if (table.getSelectedRow() > -1){
                int przedmiotId = Integer.parseInt(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                Dane.usunPrzedmiot(przedmiotId);
                pomieszczenie(pomieszczenieId);
            }
        });

        this.getContentPane().add(buttons, BorderLayout.PAGE_START);
        this.getContentPane().add(tPanel, BorderLayout.CENTER);
        this.getContentPane().add(bPanel, BorderLayout.PAGE_END);
        this.revalidate();
    }

    private void wolnePomieszczenia() {
        clear();
        this.setTitle(taOsoba.toString() + " Wolne pomieszczenie");

        JButton back = new JButton("Powrót");
        JButton rent = new JButton("Wynajmij");

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(back);
        buttons.add(rent);

        this.getContentPane().add(buttons, BorderLayout.PAGE_START);

        String[] cols = {"ID", "Powierzchnia", "Zablokowany", "Opis"};
        DefaultTableModel dtm = new DefaultTableModel(cols, 0);

        Dane.wczytajPomieszczenie(dtm);
        JTable table = new JTable(dtm);
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        back.addActionListener(e -> {
            osoba();
        });

        rent.addActionListener(e ->{
            if (table.getSelectedRow() > -1){
                int pomieszczenieId = Integer.parseInt(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                boolean zajety = Boolean.parseBoolean(dtm.getValueAt(table.getSelectedRow(), 2).toString());
                if (!zajety){
                    pomieszczenie(pomieszczenieId);
                }
            }
        });

        this.revalidate();
    }

    private void wynajem(int pomieszczenieId) {
        clear();
        this.setTitle(taOsoba.toString() + " Wynajmij");
        JButton back = new JButton("Powrót");
        JButton rent = new JButton("Wynajmij");
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(back);
        buttons.add(rent);
        this.getContentPane().add(buttons, BorderLayout.PAGE_START);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Czas wynajmu w dniach: "));
        JTextField days = new JTextField();
        days.setColumns(5);
        panel.add(days);

        back.addActionListener(e -> {
            wolnePomieszczenia();
        });

        rent.addActionListener(e ->{
            int day = Integer.parseInt(days.getText());

            if (day > 0){
                Dane.WYNAJEM.add(new Wynajem(taOsoba.getId(), pomieszczenieId, LocalDate.now(), day, false));

                if (taOsoba.getPierwszeWypozyczenie()== null){
                    taOsoba.setPierwszeWypozyczenie(LocalDate.now());
                }

                osoba();
            }
        });
        this.getContentPane().add(panel, BorderLayout.CENTER);

        this.revalidate();
    }

    private void clear(){
        this.getContentPane().removeAll();
    }

}
