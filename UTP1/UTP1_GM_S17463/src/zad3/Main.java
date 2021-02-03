/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad3;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import static java.util.stream.Collectors.toList;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter((zn) -> {
    	String regex = "^WAW ";
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(zn);
    	
    	while(matcher.find()) {
    		return true;
    	}
    	return false;
    	
    }).map((zn) -> {
    	
    	String point = "";
    	double price = 0;
    	String regexDestination = "(?<=(^WAW ))\\w+";
    	String regexPrice = "\\d+";
    	Pattern pattern = Pattern.compile(regexDestination);
    	Matcher matcher = pattern.matcher(zn);
    	
    	while(matcher.find()) {
    		point = matcher.group();
    	}
    	
    	pattern = Pattern.compile(regexPrice);
    	matcher = pattern.matcher(zn);
    	
    	while(matcher.find()) {
    		price = Double.parseDouble(matcher.group());
    	}
    	
    	double convertedPrice = price*ratePLNvsEUR;
    	DecimalFormat df = new DecimalFormat("0.#");
    	
    	return "to " + point + " - price in PLN:  " + df.format(convertedPrice);
    	
    }).collect(toList());
    
    for (String r : result) System.out.println(r);
  }
}