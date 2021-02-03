/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Anagrams {

    String tmp;

    public Anagrams(String tmp) {
        this.tmp = tmp;
    }

    public List<String>[] getSortedByAnQty() {
        List<List<String>> listArray  = new ArrayList<>();
        List<String> anagrams;
        List<String> lines = new ArrayList<>();
        File file = new File(tmp);
        if(file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(tmp));
                String current;
                while ((current = br.readLine()) != null){
                    for (int i = 0; i < current.split(" ").length; i++) {
                        lines.add(current.split(" ")[i]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String> usedWords = new ArrayList<>();

            for (int i = 0; i < lines.size(); i++) {
                String word = lines.get(i);
                if (usedWords.contains(word)){
                    continue;
                }
                usedWords.add(word);
                anagrams = new ArrayList<>();
                anagrams.add(word);
                for (int j = 0; j < lines.size(); j++) {
                    String word2 = lines.get(j);
                    if (word.equals(word2) || usedWords.contains(word2)){
                        continue;
                    }

                    boolean isOk = true;
                    for (int k = 0; k < word2.length(); k++) {
                        if (word.indexOf(word2.charAt(k))  == -1){
                            isOk = false;
                        }
                    }

                    if (isOk){
                        anagrams.add(word2);
                        usedWords.add(word2);
                    }
                }
                if (anagrams.size() > 1){
                    listArray.add(anagrams);
                }
            }
        }
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
        List<String>[] finall = new ArrayList[listArray.size()];
        for (int i = 0; i < listArray.size(); i++) {
            finall[i] = listArray.get(i);
        }
        return finall;
    }

    public String getAnagramsFor(String slowo) {
        List<String> anagrams = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        File file = new File(tmp);
        if(file.exists() || new File(slowo).exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(tmp));
                String current;
                while ((current = br.readLine()) != null){
                    for (int i = 0; i < current.split(" ").length; i++) {
                        lines.add(current.split(" ")[i]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                for (int j = 0; j < lines.size(); j++) {
                    String word2 = lines.get(j);
                    if (slowo.equals(word2)){
                        continue;
                    }

                    boolean isOk = true;
                    for (int k = 0; k < word2.length(); k++) {
                        if (slowo.indexOf(word2.charAt(k)) == -1){
                            isOk = false;
                        }
                    }

                    if (isOk){
                        anagrams.add(word2);
                    }
                }
            }

        String ret = slowo + ": [";
        for (int i = 0; i < anagrams.size(); i++) {
            ret += anagrams.get(i);

            if (i < anagrams.size()-1){
                ret += ", ";
            }
        }
        ret += "]";

        return ret;
    }
}  
