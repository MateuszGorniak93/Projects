package Obiekty;

public class Przedmiot {
    
    private static int nastepnyId = 0;
    private int id;
    private String nazwa;
    private float powierzchnia;
    private int pomieszczenieId;
    
    
    public Przedmiot(String nazwa, float podstawa, float szerokosc, float wysokosc, int pomieszczenieId)
    {
        this.id = ++nastepnyId;
        this.nazwa = nazwa;
        this.powierzchnia = podstawa*szerokosc*wysokosc;
        this.pomieszczenieId = pomieszczenieId;
    }
    
    public Przedmiot(int id, String nazwa, float powierzchnia, int pomieszczenieId)
    {
        nastepnyId = id > nastepnyId ? id : nastepnyId;
        this.id = id;
        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;
        this.pomieszczenieId = pomieszczenieId;
    }
    
    
    public int getId()
    {
        return id;
    }
    
    public String getNazwa()
    {
        return nazwa;
    }
    
    public float getPowierzchnia()
    {
        return powierzchnia;
    }
    
    public int getPomieszczenieId()
    {
        return pomieszczenieId;
    }
    
    public void setPomieszczenieId(int pomieszczenieId)
    {
        this.pomieszczenieId = pomieszczenieId;
    }
    
    public String toText()
    {
        return id + ";" + nazwa + ";" + powierzchnia + ";" + pomieszczenieId;
    }
    
    @Override
    public String toString()
    {
        return "Id: " + id + " " + nazwa + " Powierzchnia: " + powierzchnia;
    }  
}
