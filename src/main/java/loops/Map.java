package loops;

import com.mycompany.functionalprogramming.exercise2_1.Function;

import java.util.ArrayList;
import java.util.List;

public class Map <T, U> {

    public static <T, U> List<U> map(List<T> list, Function<T, U> f){
        List<U> newList = new ArrayList<>();
        for(T value: list)
            newList.add(f.apply(value));
        return newList;
    }


}
