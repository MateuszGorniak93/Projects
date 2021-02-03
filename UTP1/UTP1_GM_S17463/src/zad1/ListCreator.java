/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<F,T> {

    private List<F> arrayList;

    public ListCreator(List<F> arrayList){
        this.arrayList = arrayList;
    }

    public static <F,T> ListCreator collectFrom(List<F> arrayList){
        return new ListCreator<F,T>(arrayList);
    }

    public ListCreator when(Selector<F> selector){
        ArrayList<F> arrayList = new ArrayList<F>();

        for (int i = 0; i < this.arrayList.size(); i++) {
            if (selector.select(this.arrayList.get(i))){
                arrayList.add(this.arrayList.get(i));
            }
        }
        this.arrayList = arrayList;
        return this;
    }

    public ArrayList<T> mapEvery(Mapper<F,T> mapper){
        ArrayList<T> arrayList = new ArrayList<T>();
        for (int i = 0; i < this.arrayList.size(); i++) {
            arrayList.add(mapper.map(this.arrayList.get(i)));
        }
        return arrayList;
    }
}
