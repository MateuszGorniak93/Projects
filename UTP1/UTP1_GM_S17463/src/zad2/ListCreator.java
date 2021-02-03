package zad2;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator {

    private List<String> arrayList;

    public ListCreator(List<String> arrayList){
        this.arrayList = arrayList;
    }

    public static ListCreator collectFrom(List<String> arrayList){
        return new ListCreator(arrayList);
    }

    public ListCreator when(Predicate<String> s){
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < this.arrayList.size(); i++) {
            if (s.test(this.arrayList.get(i))){
                arrayList.add(this.arrayList.get(i));
            }
        }
        this.arrayList = arrayList;
        return this;
    }

    public ArrayList<String> mapEvery(Function<String,String> f){
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < this.arrayList.size(); i++) {
            arrayList.add(f.apply(this.arrayList.get(i)));
        }
        return arrayList;
    }
}
