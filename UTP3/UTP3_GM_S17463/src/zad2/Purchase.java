/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;


import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class Purchase{

    private String id;
    private String nazwisko;
    private String imie;
    private String towar;
    private double cena;
    private double ilosc;
    private String rekord;

    public Purchase(String id, String nazwisko, String towar, double cena, double ilosc, String rekord) {
        this.id = id;
        this.nazwisko = nazwisko.split(" ")[0];
        this.imie = nazwisko.split(" ")[1];
        this.towar = towar;
        this.cena = cena;
        this.ilosc = ilosc;
        this.rekord = rekord;
    }

    public static Comparator sortNazwiska(){
        return new Comparator<Purchase>() {
            @Override
            public int compare(Purchase p1, Purchase p2) {
                Collator c = Collator.getInstance(new Locale("pl", "PL"));
                if (p1.getNazwisko().compareTo(p2.getNazwisko()) == 0)
                    return p1.getId().compareTo(p2.getId());
                else
                    return c.compare(p1.getNazwisko(),p2.getNazwisko());
            }
        };
    }

    public static Comparator sortKoszta(){
        return new Comparator<Purchase>() {
            @Override
            public int compare(Purchase p1, Purchase p2) {
                if (p1.getIlosc()*p1.getCena() > p2.getIlosc()*p2.getCena()){
                    return -1;
                }
                if (p1.getIlosc()*p1.getCena() < p2.getIlosc()*p2.getCena()){
                    return 1;
                }
                return p1.getId().compareTo(p2.getId());
            }
        };
    }


    public String getRekord() {
        return rekord;
    }

    public void setRekord(String rekord) {
        this.rekord = rekord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getTowar() {
        return towar;
    }

    public void setTowar(String towar) {
        this.towar = towar;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }
}
