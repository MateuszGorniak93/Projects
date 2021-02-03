/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomersPurchaseSortFind {

    private List<Purchase> customers;


    public void readFile(String fname) {
        customers = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                customers.add(new Purchase(data[0],data[1],data[2],new Double(data[3]), new Double(data[4]), line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String by) {
        switch (by){
            case "Nazwiska":
                customers.sort(Purchase.sortNazwiska());
                System.out.println("Nazwiska");
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println(customers.get(i).getRekord());
                }
                break;
            case "Koszty":
                customers.sort(Purchase.sortKoszta());

                System.out.println("Koszty");
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println(customers.get(i).getRekord() +
                            " (koszt: " + customers.get(i).getIlosc()*customers.get(i).getCena() + ")");
                }
                break;
        }
    }


    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(id))
                System.out.println(customers.get(i).getRekord());
        }
    }
}
