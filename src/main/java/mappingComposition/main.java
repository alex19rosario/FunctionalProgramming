package mappingComposition;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static loops.main.append;
import static mappingComposition.Map.map;

public class main {

    public static <T, U> T foldLeft(List<U> list, T identity, Function<T, Function<U, T>> f){
        T result = identity;
        for(U value: list)
            result = f.apply(result).apply(value);
        return result;
    }
    public static <T> List<T> newList(T... t){
        return new ArrayList<T>(Arrays.asList(Arrays.copyOf(t, t.length)));
    }

    public static <T> void forEach(Collection<T> ts, Consumer<T> effect){
        for(T t: ts)
            effect.accept(t);
    }

    public static List<Integer> rangeOfIntegers(int start, int end){
        List<Integer> list = new ArrayList<>();
        Function<Integer, Integer> f = x -> x+1;
        Predicate<Integer> predicate = x -> (x <= end);
        for(int i = start; predicate.test(i); i = f.apply(i))
            list = append(list, i);
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> range(T seed, Function<T, T> f, Predicate<T> p){
        List<T> list = new ArrayList<>();
        for(T i = seed; p.test(i); i = f.apply(i))
            list = append(list, i);
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> unfold(T seed, Function<T, T> f, Predicate<T> p){
        List<T> list = new ArrayList<>();
        T temp = seed;
        while(p.test(temp)){
            list = append(list, temp);
            temp = f.apply(temp);
        }
        return Collections.unmodifiableList(list);
    }

    public static void main(String[] args){

        ArrayList<Double> salaries = (ArrayList<Double>) newList(650.0, 500.0, 850.0, 630.0, 750.0, 950.0, 1500.0, 2000.0, 1350.0, 1300.0);
        Function<Double, Double> addDouble = s -> s*2;
        Function<Double, Double> addBono = s -> s*1.45;
        Function<Double, Double> removeTaxes = s -> s*0.83;

        //WITHOUT USING MAPPING COMPOSITION
        List<Double> salariesWithDouble = map(salaries, addDouble);
        List<Double> salariesWithBono = map(salariesWithDouble, addBono);
        List<Double> salariesWithTaxes = map(salariesWithBono, removeTaxes);

        //WITH MAPPING COMPOSITION
        List<Double> finalSalaries = map(salaries, addDouble.andThen(addBono).andThen(removeTaxes));

        System.out.println(salariesWithTaxes);
        System.out.println(finalSalaries);

        //APPLYING EFFECTS TO LISTS
        Consumer<Double> printWith2Decimals = x -> {
            System.out.printf("%.2f", x);
            System.out.println();
        };

        forEach(finalSalaries, printWith2Decimals);

        //WITH THE FOR_EACH METHOD, ONE SINGLE EFFECT IS APPLIED TO EACH ELEMENT OF THE LIST.
        //BUT WE CAN GO FURTHER COMPOSING THESE EFFECTS INTO A SINGLE ONE, HERE'S HOW

        Function<Executable, Function<Executable, Executable>> compose = x -> y -> () -> {
            x.exec();
            y.exec();
        };

        Executable ez = () -> {};

        Executable program = foldLeft(finalSalaries, ez, e -> d -> compose.apply(e).apply(() -> printWith2Decimals.accept(d)));

        program.exec();

        System.out.println(rangeOfIntegers(0,10));

        System.out.println(range(0, x -> x+1, x -> x <= 15));

        System.out.println(unfold(5, x -> x+5, x -> x<=25));

        range(15, x -> x-1, x -> x >= 0 ).forEach(System.out::println);

    }
}
