package Obiekty;

import java.time.LocalDate;

public class Osoba {
    
    private static int nastepnyId = 0;
    private int id;
    private String imie;
    private String nazwisko;
    private int pesel;
    private String dataUrodzenia;
    private String adress;
    private LocalDate pierwszeWypozyczenie;
    
    
    public Osoba(String imie, String nazwisko, int pesel, String dataUrodzenia, String adress, LocalDate pierwszeWypozycznie)
    {
        this.id = ++nastepnyId;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.adress = adress;
        this.pierwszeWypozyczenie = pierwszeWypozyczenie;
    }
    
    public Osoba(int id, String imie, String nazwisko, int pesel, String dataUrodzenia, String adress,LocalDate pierwszeWypozycznie)
    {
        nastepnyId = id > nastepnyId ? id : nastepnyId;
        this.id = ++nastepnyId;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.adress = adress;
        this.pierwszeWypozyczenie = pierwszeWypozyczenie;
    }
    
    
    public int getId()
    {
        return id;
    }
    
    public void setPierwszeWypozyczenie(LocalDate pierwszeWypozyczenie)
    {
        this.pierwszeWypozyczenie = pierwszeWypozyczenie;
    }
    
    public LocalDate getPierwszeWypozyczenie()
    {
        return pierwszeWypozyczenie;
    }
    
    public String toText()
    {
        String tmp = pierwszeWypozyczenie == null ? "null" : pierwszeWypozyczenie.toString();
        return id + ";" + imie + ":" + nazwisko + ":" + pesel + ":" + dataUrodzenia + ";" + adress + ";" + tmp;
    }
    
    @Override
    public String toString()
    {
        return imie + " " + nazwisko;
    }
}
