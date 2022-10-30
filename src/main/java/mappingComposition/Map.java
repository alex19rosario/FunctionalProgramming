package mappingComposition;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Map {
    public static <T, U> List<U> map(List<T> list, Function<T, U> f){
        List<U> newList = new ArrayList<>();
        for(T value: list)
            newList.add(f.apply(value));
        return newList;
    }

}
