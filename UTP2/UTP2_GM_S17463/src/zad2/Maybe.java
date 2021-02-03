package zad2;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    T object;


    public Maybe(T object) {
        this.object = object;
    }

    public static <T>Maybe<T> of(T object){
        return  new Maybe(object);
    }

    public void ifPresent(Consumer cons){
        if (object != null){
            cons.accept(object);
        }
    }

    public <X>Maybe<X> map(Function<T,X> func){
        if (object != null){
            X z = func.apply(object);
            Maybe<X> t = Maybe.of(z);
            return t;
        }else{
            return new Maybe<X>(null);
        }
    }

    public T get(){
        if (object != null){
            return object;
        }else{
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent(){
        if (object != null){
            return true;
        }else{
            return false;
        }
    }

    public T orElse(T defVal){
        if (object != null){
            return object;
        }else {
            return defVal;
        }
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(object != null){
            if (pred.test(object)){
                return this;
            }else{
                return new Maybe(null);
            }
        }else{
            return this;
        }
    }

    @Override
    public String toString() {
        String msg = "";

        if (object != null){
            msg = "Maybe has value " + object;
        }else {
            msg = "Maybe is empty";
        }

        return msg;
    }
}
