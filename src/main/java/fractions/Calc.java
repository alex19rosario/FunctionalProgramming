package fractions;

import conditions.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Calc {

    private final static Predicate<Integer> isPrime = x -> {
        int c = 0;
        for(int i = x; i > 0; i--){
            if(x%i == 0)
                c++;
        }
        if(c == 2)
            return true;
        else
            return false;
    };

    private final static Function<Integer, Integer> findPrime = n -> {
        int i = 0, c = 2;
        while (n > i){
            if(isPrime.test(c)){
                i++;
                if(n > i)
                    c++;
            }
            else
                c++;
        }
        return c;
    };

    private final static Predicate<List<Integer>> isOver = l -> {
        boolean isOver = true;
        for(int e: l){
            if(e != 1)
                isOver = false;
        }
        return isOver;
    };

    private final static Predicate<Tuple<List<Integer>, Integer>> isDivisible = t -> {
        boolean value = false;
        for(int e: t._1){
            if(e%t._2 == 0)
                value = true;
        }
        return value;
    };

    private final static Function<List<Integer>, Function<Integer, List<Integer>>> newDenominators = l -> p -> {
        List<Integer> newList = new ArrayList<>();
        for(int e: l) {
            if (e % p == 0)
                newList.add(e / p);
            else
                newList.add(e);
        }
        return newList;
    };

    private final static Function<List<Integer>, Function<Integer, Function<List<Integer>, List<Integer>>>> findResultSet = denominatorList -> indexPrime -> resultSet -> {
        if(isOver.test(denominatorList))
            return resultSet;
        else{
            int p = findPrime.apply(indexPrime);
            if(isDivisible.test(new Tuple<>(denominatorList, p))){
                resultSet.add(p);
                return Calc.findResultSet.apply(newDenominators.apply(denominatorList).apply(p)).apply(indexPrime).apply(resultSet);
            }
            else{
                int newIndexPrime = indexPrime+1;
                return Calc.findResultSet.apply(denominatorList).apply(newIndexPrime).apply(resultSet);
            }
        }
    };

    private final static Function<Integer, Function<Integer, Integer>> f = a -> b -> a*b;
    private static <T, U> T foldLeft(List<U> list, T identity, Function<T, Function<U, T>> f){
        T result = identity;
        for(U value: list)
            result = f.apply(result).apply(value);
        return result;
    }

    public static int findLCD(Integer... denominators){
        List<Integer> denominatorList = Arrays.asList(Arrays.copyOf(denominators, denominators.length));
        List<Integer> resulSet = new ArrayList<>();
        return foldLeft(findResultSet.apply(denominatorList).apply(1).apply(resulSet), 1, f);
    }

}
