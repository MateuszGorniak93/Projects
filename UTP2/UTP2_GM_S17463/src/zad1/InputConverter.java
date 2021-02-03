package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    T path;

    public InputConverter(T t){
        this.path = t;
    }


    public <X> X convertBy(Function... f){
        if (f.length == 1){
            return (X) f[0].apply(path);
        }else if (f.length == 2){
            return (X) f[1].apply(f[0].apply(path));
        }else if (f.length == 3){
            return (X) f[2].apply(f[1].apply(f[0].apply(path)));
        }else if (f.length == 4){
            return (X) f[3].apply(f[2].apply(f[1].apply(f[0].apply(path))));
        }else{
            return null;
        }
    }

}

