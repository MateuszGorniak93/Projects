package Magazyn;

import Obiekty.Osoba;
import Obiekty.Pomieszczenie;
import Obiekty.Przedmiot;
import Obiekty.Wynajem;
import Wyjatki.TooManyThingsException;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;


public class Dane {
    
    public static LinkedList<Osoba> OSOBA = new LinkedList<>();
    public static LinkedList<Pomieszczenie> POMIESZCZENIE = new LinkedList<>();
    public static LinkedList<Przedmiot> PRZEDMIOT = new LinkedList<>();
    public static LinkedList<Wynajem> WYNAJEM = new LinkedList<>();
    
    public static LocalDate appDate = LocalDate.now();
    
    private static String sciezka = "plik.txt";
    
    private Dane(){}
    
    public static void wczytajDane(){
        try{
            FileReader fileReader = new FileReader(sciezka);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                String[] tab = line.split(";");

                if (tab.length == 4){

                    int id = Integer.parseInt(tab[0]);
                    String nazwa = tab[1];
                    float powierzchnia = Float.parseFloat(tab[2]);
                    int pomieszczenieId = Integer.parseInt(tab[3]);

                    PRZEDMIOT.add( new Przedmiot(id, nazwa, powierzchnia, pomieszczenieId));

                } else if (tab.length == 5){

                    int id = Integer.parseInt(tab[0]);
                    float powierzchnia = Float.parseFloat(tab[1]);
                    boolean zajety = tab[2].equals("Tak");
                    LocalDate wynajem = tab[3].equals("null") ? null : LocalDate.parse(tab[3]);
                    String powod = tab[4];

                    POMIESZCZENIE.add(new Pomieszczenie(id, powierzchnia, zajety, wynajem, powod));

                }else if (tab.length == 6){

                    int id = Integer.parseInt(tab[0]);
                    int osobaId = Integer.parseInt(tab[1]);
                    int pomieszczenieId = Integer.parseInt(tab[2]);
                    LocalDate wynajemOd = LocalDate.parse(tab[3]);
                    int czasWynajmu = Integer.parseInt(tab[4]);
                    boolean isSkonczony = tab[5].equals("Tak");

                    WYNAJEM.add(new Wynajem(id, osobaId, pomieszczenieId, wynajemOd, czasWynajmu, isSkonczony));

                } else if (tab.length == 7){

                    int id = Integer.parseInt(tab[0]);
                    String imie = tab[1];
                    String nazwisko = tab[2];
                    int pesel = Integer.parseInt(tab[3]);
                    String dataUrodzenia = tab[4];
                    String address = tab[5];
                    LocalDate pierwszeWypozyczenie = tab[6].equals("null") ? null : LocalDate.parse(tab[6]);

                    OSOBA.add(new Osoba(id, imie, nazwisko, pesel, dataUrodzenia, address, pierwszeWypozyczenie));

                }
            }
            bufferedReader.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void zapiszDane(){
        try{
            FileWriter fileWriter = new FileWriter(sciezka);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



            POMIESZCZENIE.sort((o1, o2) -> {
                return (int)(o1.getPowierzchnia()-o2.getPowierzchnia());
            });

            PRZEDMIOT.sort((o1, o2) -> {
                return o1.getPowierzchnia() == o2.getPowierzchnia() ? o1.getNazwa().compareToIgnoreCase(o2.getNazwa()) : (int)(o1.getPowierzchnia()-o2.getPowierzchnia());
            });

            for (Osoba o : OSOBA){
                bufferedWriter.write(o.toText());
                bufferedWriter.write("\n");
            }
            for (Pomieszczenie p : POMIESZCZENIE){
                bufferedWriter.write(p.toText());
                bufferedWriter.write("\n");
            }
            for (Przedmiot pr : PRZEDMIOT){
                bufferedWriter.write(pr.toText());
                bufferedWriter.write("\n");
            }
            for (Wynajem w : WYNAJEM){
                bufferedWriter.write(w.toText());
                bufferedWriter.write("\n");
            }



            bufferedWriter.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void wczytajWynajem(Osoba taOsoba, DefaultTableModel dtm){
         for (Wynajem w : WYNAJEM){
            if (w.getOsobaId() == taOsoba.getId() &&
                    w.getWynajemOd().plusDays(w.getCzasWynajmu()).isAfter(appDate.minusDays(1))){
                int pomieszczenieId = w.getPomieszczenieId();
                float powierzchnia = 0;
                LocalDate data = w.getWynajemOd();
                int dni = w.getCzasWynajmu();
                for (Pomieszczenie pp : POMIESZCZENIE){
                    if (pp.getId() == pomieszczenieId){
                        powierzchnia = pp.getPowierzchnia();
                    }
                }
                Object[] ob = {pomieszczenieId, powierzchnia, data, dni};
                dtm.addRow(ob);
            }
        }
    }

    public static Pomieszczenie getPomieszczeniePoId(int pomieszczenieId) {
        Pomieszczenie pomieszczenie = null;
        for (Pomieszczenie p : POMIESZCZENIE){
            if (p.getId() == pomieszczenieId)
                pomieszczenie = p;
        }
        return pomieszczenie;
    }

    public static void wczytajPrzedmiot(DefaultTableModel dtm, int pomieszczenieId) {
        for (Przedmiot pr : PRZEDMIOT){
            if (pr.getPomieszczenieId() == pomieszczenieId){
                Object[] ob = {pr.getId(), pr.getNazwa(), pr.getPowierzchnia()};
                dtm.addRow(ob);
            }
        }
    }

    public static void usunPrzedmiot(int przedmiotId) {
        Przedmiot przedmiot = null;
        for (Przedmiot pr : PRZEDMIOT){
            if (pr.getId() == przedmiotId){
                przedmiot = pr;
            }
        }
        PRZEDMIOT.remove(przedmiot);
    }

    public static void dodajPrzedmiot(Przedmiot przedmiot) {
        float powierzchnia = 0;

        for (Przedmiot pr : PRZEDMIOT){
            if (pr.getPomieszczenieId() == przedmiot.getPomieszczenieId()){
                powierzchnia += pr.getPowierzchnia();
            }
        }
        Pomieszczenie pomieszczenie = null;
        for (Pomieszczenie p : POMIESZCZENIE){
            if (p.getId() == przedmiot.getPomieszczenieId()){
                pomieszczenie = p;
            }
        }

        if (pomieszczenie.getPowierzchnia() - powierzchnia >= przedmiot.getPowierzchnia()){
            PRZEDMIOT.add(przedmiot);
        }else {
            try {
                throw new TooManyThingsException();
            } catch (TooManyThingsException e) {
                e.printStackTrace();
            }
        }
    }

    public static void wczytajPomieszczenie(DefaultTableModel dtm) {
        LinkedList<Pomieszczenie> wolnePomieszczenia = new LinkedList<>(POMIESZCZENIE);
        for (Wynajem w : WYNAJEM){
            if (w.getWynajemOd().plusDays(w.getCzasWynajmu()).isAfter(appDate.plusDays(1))){
                for (Pomieszczenie pp : POMIESZCZENIE){
                    if (w.getPomieszczenieId() == pp.getId()){
                        wolnePomieszczenia.remove(pp);
                    }
                }
            }
        }

        for (Pomieszczenie p : wolnePomieszczenia){
            Object[] ob = {p.getId(), p.getPowierzchnia(), p.isZajety(), p.getWynajem()};
            dtm.addRow(ob);
        }
    }

    public static void clean(){
        for (Osoba o : OSOBA){
            LinkedList<Pomieszczenie> pomieszczenieToClean = new LinkedList<>();
            LinkedList<Pomieszczenie> osobaPomieszczenie = new LinkedList<>();

            for (Wynajem w : WYNAJEM) {
                if (w.getOsobaId() == o.getId()){
                    if (w.getWynajemOd().plusDays(w.getCzasWynajmu()).isAfter(appDate)) {
                        for (Pomieszczenie pp : POMIESZCZENIE) {
                            if (pp.getId() == w.getPomieszczenieId()) {
                                osobaPomieszczenie.add(pp);
                            }
                        }
                    }else if (w.getWynajemOd().plusDays(w.getCzasWynajmu()).isEqual(appDate)){
                        for (Pomieszczenie pp : POMIESZCZENIE) {
                            if (pp.getId() == w.getPomieszczenieId()) {
                                pomieszczenieToClean.add(pp);
                            }
                        }
                    }
                }
            }
            LinkedList<Przedmiot> przedmiotToMove = itemsToMove(pomieszczenieToClean);
            moveItems(osobaPomieszczenie, przedmiotToMove);
            removeItems(pomieszczenieToClean, przedmiotToMove);
            lock(pomieszczenieToClean);
        }
        unlock();
    }

    private static LinkedList<Przedmiot> itemsToMove(LinkedList<Pomieszczenie> pomieszczenieToClean){
        LinkedList<Przedmiot> thingsToMove = new LinkedList<>();
        for (Pomieszczenie p : pomieszczenieToClean){
            for (Przedmiot pr : PRZEDMIOT){
                if (pr.getPomieszczenieId() == p.getId()){
                    thingsToMove.add(pr);
                }
            }
        }
        return thingsToMove;
    }

    private static void moveItems(LinkedList<Pomieszczenie> pomieszczenie, LinkedList<Przedmiot> przedmiot){
        for (Pomieszczenie p : pomieszczenie){
            for (Przedmiot pr : przedmiot){
                if (enoughSpace(pr, p)){
                    pr.setPomieszczenieId(p.getId());
                }
            }
        }
    }

    private static void removeItems(LinkedList<Pomieszczenie> pomieszczenie, LinkedList<Przedmiot> przedmiot){
        for (Pomieszczenie p : pomieszczenie){
            for (Przedmiot pr : przedmiot){
                if (p.getId() == pr.getPomieszczenieId()){
                    PRZEDMIOT.remove(pr);
                }
            }
        }
    }

    private static void unlock(){
        for (Pomieszczenie p : POMIESZCZENIE){
            if (p.getWynajem() != null){
                if (p.getWynajem().isEqual(appDate.plusDays(1))){
                    p.setPowod("Brak");
                    p.setWynajem(null);
                    p.setZajety(false);}
            }
        }
    }

    private static void lock(LinkedList<Pomieszczenie> pomieszczenie){
        for (Pomieszczenie p : pomieszczenie){
            for (Pomieszczenie pp : POMIESZCZENIE){
                if (p.getId() == pp.getId()){
                    pp.setZajety(true);
                    pp.setWynajem(appDate.plusDays(5));
                    pp.setPowod("Remont");
                }
            }
        }
    }

    private static boolean enoughSpace(Przedmiot pr , Pomieszczenie p){
        float powierzchnia = 0;

        for (Przedmiot prz : PRZEDMIOT){
            if (prz.getPomieszczenieId() == p.getId()){
                powierzchnia += prz.getPowierzchnia();
            }
        }

        if (p.getPowierzchnia() - powierzchnia >= pr.getPowierzchnia()){
            return true;
        }else {
            return false;
        }
    }
}
