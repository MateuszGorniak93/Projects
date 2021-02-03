package Obiekty;

import java.time.LocalDate;

public class Wynajem {
    
    private static int nastepnyId = 0;
    private int id;
    private int osobaId;
    private int pomieszczenieId;
    private LocalDate wynajemOd;
    private int czasWynajmu;
    private boolean isSkonczony;
    
    
    public Wynajem(int osobaId, int pomieszczenieId, LocalDate wynajemOd, int czasWynajmu, boolean isSkonczony)
    {
        this.id = ++nastepnyId;
        this.osobaId = osobaId;
        this.pomieszczenieId = pomieszczenieId;
        this.wynajemOd = wynajemOd;
        this.czasWynajmu = czasWynajmu;
        this.isSkonczony = isSkonczony;
    }
    
    public Wynajem(int id, int osobaId, int pomieszczenieId, LocalDate wynajemOd, int czasWynajmu, boolean isSkonczony)
    {
        nastepnyId = id > nastepnyId ? id : nastepnyId;
        this.id = ++nastepnyId;
        this.osobaId = osobaId;
        this.pomieszczenieId = pomieszczenieId;
        this.wynajemOd = wynajemOd;
        this.czasWynajmu = czasWynajmu;
        this.isSkonczony = isSkonczony;
    }
    
    
    public int getOsobaId()
    {
        return osobaId;
    }
    
    public int getPomieszczenieId()
    {
        return pomieszczenieId;
    }
    
    public LocalDate getWynajemOd()
    {
        return wynajemOd;
    }
    
    public int getCzasWynajmu()
    {
        return czasWynajmu;
    }
    
    public String toText()
    {
        int tmp = isSkonczony ? 1 : 0;
        return id + ":" + osobaId + ":" + pomieszczenieId + ":" + wynajemOd.toString() + ";" + czasWynajmu + ":" + tmp;
    }
}
