package polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Polynomial {
    private final static Function<Double, List<Double>> constantFactors = x -> {
        List<Double> factors = new ArrayList<>();
        Double n = x > 0? x: -x;
        for(int i = 1; i <= n; i++) {
            if (n % i == 0)
                factors.add((double) i);
        }
        return Collections.unmodifiableList(factors);
    };

    private final static Function<Double, List<Double>> leadingFactors = x -> {
        List<Double> factors = new ArrayList<>();
        Double n = x > 0? x: -x;
        for(int i = 1; i <= n; i++) {
            if (x % n == 0) {
                factors.add((double) i);
                factors.add((double) -i);
            }
        }
        return Collections.unmodifiableList(factors);
    };

    private final static Function<List<Double>, Function<List<Double>, List<Double>>> findPosibleZeros = P -> Q -> {
        List<Double> posibleZeros = new ArrayList<>();
        for(double p: P){
            for(double q: Q){
                if(!posibleZeros.contains(p/q))
                    posibleZeros.add(p/q);
            }
        }
        return Collections.unmodifiableList(posibleZeros);
    };

    private final static Function<Double, Function<List<Double>, List<Double>>> syntheticDivision = divisor -> dividend -> {
        List<Double> resultSet = new ArrayList<>();
        for(int i = 0; i < dividend.size(); i++)
            resultSet.add(i == 0? dividend.get(i): dividend.get(i) + (divisor * resultSet.get(i - 1)));
        return Collections.unmodifiableList(resultSet);
    };

    private final static Function<List<Double>, List<Double>> findQuotient = l -> {
        List<Double> quotient = new ArrayList<>(l);
        quotient.remove(quotient.size() - 1);
        return Collections.unmodifiableList(quotient);
    };

    private final static Function<List<Double>, Double> findRemainder = l -> l.get(l.size() - 1);

    private final static Function<Double, Function<Double, Function<Double, Double>>> findDiscriminant = a -> b -> c -> Math.sqrt((b*b) - 4*a*c);

    private final static Function<Double, Function<Double, Function<Double, Double>>> findX1 = a -> b -> c -> ((-b) + findDiscriminant.apply(a).apply(b).apply(c))/(2*a);

    private final static Function<Double, Function<Double, Function<Double, Double>>> findX2 = a -> b -> c -> ((-b) - findDiscriminant.apply(a).apply(b).apply(c))/(2*a);

    private final static Function<List<Double>, Function<List<Double>, List<Double>>> findRealZeros = polynomial -> realZeros -> {

        if(polynomial.size() == 3){
            Double x1 = findX1.apply(polynomial.get(0)).apply(polynomial.get(1)).apply(polynomial.get(2));
            Double x2 = findX2.apply(polynomial.get(0)).apply(polynomial.get(1)).apply(polynomial.get(2));
            realZeros.add(x1);
            realZeros.add(x2);
        }

        else{
            for(double possibleZero: findPosibleZeros.apply(constantFactors.apply(polynomial.get(polynomial.size() - 1))).apply(leadingFactors.apply(polynomial.get(0)))){
                if (findRemainder.apply(syntheticDivision.apply(possibleZero).apply(polynomial)) == 0) {
                    realZeros.add(possibleZero);
                    Polynomial.findRealZeros.apply(findQuotient.apply(syntheticDivision.apply(possibleZero).apply(polynomial))).apply(realZeros);
                    break;
                }
            }
        }

        return Collections.unmodifiableList(realZeros);
    };

    public static List<Double> findFactors(Double... d){
        List<Double> polynomial = Arrays.asList(Arrays.copyOf(d, d.length));
        List<Double> realZeros = new ArrayList<>();
        return findRealZeros.apply(polynomial).apply(realZeros);
    }
}
