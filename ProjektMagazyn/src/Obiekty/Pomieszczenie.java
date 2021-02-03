package Obiekty;

import java.time.LocalDate;

public class Pomieszczenie {
    
    private static int nastepnyId = 0;
    private int id;
    private float powierzchnia;
    private boolean zajety;
    private LocalDate wynajem;
    private String powod;
    
    
    public Pomieszczenie(float podstawa, float szerokosc, float wysokosc, boolean zajety, LocalDate wynajem, String powod)
    {
        this.id = ++nastepnyId;
        this.powierzchnia = podstawa*szerokosc*wysokosc;
        this.zajety = zajety;
        this.wynajem = wynajem;
        this.powod = powod;
    }
    
    public Pomieszczenie(int id, float powierzchnia, boolean zajety, LocalDate wynajem, String powod)
    {
        nastepnyId = id > nastepnyId ? id : nastepnyId;
        this.id = id;
        this.powierzchnia = powierzchnia;
        this.zajety = zajety;
        this.wynajem = wynajem;
        this.powod = powod;
    }
    
    
    public int getId()
    {
        return id;
    }
    
    public float getPowierzchnia()
    {
        return powierzchnia;
    }
    
    public boolean isZajety()
    {
        return zajety;
    }
    
    public LocalDate getWynajem()
    {
        return wynajem;
    }
    
    public void setZajety(boolean zajety)
    {
        this.zajety = zajety;
    }
    
    public void setWynajem(LocalDate wynajem)
    {
        this.wynajem = wynajem;
    }
    
    public void setPowod(String powod)
    {
        this.powod = powod;
    }
    
    public String getPowod()
    {
        return powod;
    }
    
    public String toText()
    {
        String tmp = wynajem == null ? "null" : wynajem.toString();
        String isZajety = zajety ? "Tak" : "Nie";
        String tmp1 = powod == "" ? "Brak" : powod;
        return id + ";" + powierzchnia + ";" + isZajety + ";" + tmp + ";" + tmp1;
    }
    
    @Override
    public String toString()
    {
        return "Id: " + id + " Powierzchnia: " + powierzchnia;
    }
}
