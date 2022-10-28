package practice;

import com.mycompany.functionalprogramming.exercise2_1.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static loops.CollectionUtilities.list;
import static loops.main.append;

public class Practice {

    public static <T, U> T foldLeft(List<U> list, T identity, Function<T, Function<U, T>> f){
        T result = identity;
        for(U value: list)
            result = f.apply(result).apply(value);
        return result;
    }

    public static <T, U> T foldRight(List<U> list, T identity, Function<T, Function<U, T>> f){
        T result = identity;
        for(int i = list.size()-1; i >= 0; i--)
            result = f.apply(result).apply(list.get(i));
        return result;
    }

    public static <T> List<T> newList(T... t){
        return new ArrayList<T>(Arrays.asList(Arrays.copyOf(t, t.length)));
    }

    public static void main(String[] args){

        ArrayList<Double> list = (ArrayList<Double>) newList(12.0,23.0,45.0,56.0,45.0,63.0,12.0);
        Function<Double, Function<Double, Double>> sum = x -> y -> x+y;

        Function<String, List<String>> split = s -> new ArrayList<String>(Arrays.asList(s.split("")));
        Function<List<String>, List<String>> reverse = l -> foldRight(l, list(), x -> y -> append(x,y));
        Function<List<String>, String> join = l -> foldLeft(l, "", s -> e -> s+e);
        Function<String, String> backwards = s -> join.apply(reverse.apply(split.apply(s)));


        System.out.println(foldLeft(list,0.0,x -> y -> x+y));
        System.out.println(foldRight(list,0.0, sum));
        System.out.println(foldLeft(list, 0.0, x -> y -> (x + (y/list.size()))));
        System.out.println(backwards.apply("I love Java"));


    }
}
