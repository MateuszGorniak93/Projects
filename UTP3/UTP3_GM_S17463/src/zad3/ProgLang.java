package zad3;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private String nazwaPliku;

    public ProgLang(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

    private List<String> readFile(){
        List<String> tmp = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(this.nazwaPliku))) {
            String line;
            while ((line = br.readLine()) != null) {
                tmp.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public Map<String, List<String>> getLangsMap(){
        Map<String, List<String>> mapa = new LinkedHashMap<>();
        List<String> data = readFile();
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i).split("\\t");
            mapa.put(row[0], new ArrayList<>());
            for (int j = 1; j < row.length; j++) {
                if(!mapa.get(row[0]).contains(row[j])){
                    mapa.get(row[0]).add(row[j]);
                }
            }
        }
        return mapa;
    }


    public Map<String, List<String>> getProgsMap() {
        Map<String, List<String>> mapa = new LinkedHashMap<>();
        List<String> data = readFile();
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i).split("\\t");
            for (int j = 1; j < row.length; j++) {
                if (mapa.containsKey(row[j])){
                    if(!mapa.get(row[j]).contains(row[0]))
                        mapa.get(row[j]).add(row[0]);
                }else{
                    mapa.put(row[j], new ArrayList<>());
                    mapa.get(row[j]).add(row[0]);
                }
            }
        }
        return mapa;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        Map<String, List<String>> mapa = getLangsMap();
        return sorted(mapa, (s1, s2) -> {
            if (s1.size() < s2.size()){
                return 1;
            }else if (s1.size() > s2.size()){
                return -1;
            }else {
                return 0;
            }
        });

//                mapa.entrySet().stream().sorted(Map.Entry.comparingByKey((s1, s2) -> {
//            return s1.compareTo(s2);
//        })).sorted(Map.Entry.comparingByValue((s1, s2) -> {
//            if (s1.size() < s2.size()){
//                return 1;
//            }else if (s1.size() > s2.size()){
//                return -1;
//            }else {
//                return 0;
//            }
//        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        Map<String, List<String>> mapa = getProgsMap();
        return sorted(mapa, (s1, s2) -> {
            if (s1.size() < s2.size()){
                return 1;
            }else if (s1.size() > s2.size()){
                return -1;
            }else {
                return 0;
            }
        });

//                mapa.entrySet().stream().sorted(Map.Entry.comparingByKey((s1, s2) -> {
//            return s1.compareTo(s2);
//        })).sorted(Map.Entry.comparingByValue((s1, s2) -> {
//            if (s1.size() < s2.size()){
//                return 1;
//            }else if (s1.size() > s2.size()){
//                return -1;
//            }else {
//                return 0;
//            }
//        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }


    private <T extends Comparable, R>Map<T, R> sorted(Map<T, R> mapa, Comparator<R> comparator){
        LinkedHashMap<T,R> collect = new LinkedHashMap<>();
                mapa.entrySet().stream().sorted(Map.Entry.comparingByKey((s1, s2) -> {
            return s1.compareTo(s2);
        })).sorted(Map.Entry.comparingByValue(comparator)).forEach(o -> {
            collect.put((T)((Map.Entry) o).getKey(), (R)((Map.Entry) o).getValue());
                });
        return collect;
    }

    private <T,R>Map<T, R> filtred(Map<T, R> mapa, Predicate<Map.Entry<T,R>> predicate){
        LinkedHashMap<T,R> collect = mapa.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return collect;
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        Map<String, List<String>> mapa = getProgsMap();
        return filtred(mapa, entry -> {
            if(entry.getValue().size() > i){
                return true;
            }else{
                return false;
            }
        });
    }
}
