/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
      List<String> words = new ArrayList<>();
      List<String> words2 = new ArrayList<>();
      BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/bigList.txt"));
        String current;
        while ((current = br.readLine()) != null){
          words.add(current);
          words2.add(current);
      }

        List<List<String>> listArray  = new ArrayList<>();
        List<String> usedWords = new ArrayList<>();
        words.stream().forEach( s -> {
          if (!usedWords.contains(s)) {
            usedWords.add(s);
            List<String> anagrams = new ArrayList<>();
            words2.stream().forEach(s1 -> {
              if (!usedWords.contains(s1) && s.length() == s1.length()) {
                boolean isOk = true;
                for (int k = 0; k < s1.length(); k++) {
                  if (s.indexOf(s1.charAt(k)) == -1) {
                    isOk = false;
                  }
                }

                if (isOk) {
                  anagrams.add(s1);
                  usedWords.add(s1);
                }

              }
            });
            anagrams.add(s);
            listArray.add(anagrams);
          }
        });

    Collections.sort(listArray, new Comparator<List<String>>() {
      @Override
      public int compare(List<String> s1, List<String> s2) {
        if (s1.size() != s2.size()) {
          return s2.size() > s1.size() ? 1 : -1;
        }else{
          return  s1.get(0).compareTo(s2.get(0));
        }
      }
    });

    int max = listArray.get(0).size();
    listArray.forEach(strings -> {
      if (strings.size() == max){
        System.out.print(strings.get(strings.size()-1) + " [");
        for (int i = 0; i < strings.size()-2; i++) {
          System.out.print(strings.get(i));
          if (i < strings.size()-3)
            System.out.print(", ");
        }
        System.out.print("]");
      }
    });
  }
}
