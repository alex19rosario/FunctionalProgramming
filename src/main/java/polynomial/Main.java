package polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Main {

    final static Function<Double, List<Double>> constantFactors = x -> {
        List<Double> factors = new ArrayList<>();
        for(int i = 1; i <= x; i++) {
            if (x % i == 0)
                factors.add((double) i);
        }
        return Collections.unmodifiableList(factors);
    };

    final static Function<Double, List<Double>> leadingFactors = x -> {
        List<Double> factors = new ArrayList<>();
        for(int i = 1; i <= x; i++) {
            if (x % i == 0) {
                factors.add((double) i);
                factors.add((double) -i);
            }
        }
        return Collections.unmodifiableList(factors);
    };

    final static Function<List<Double>, Function<List<Double>, List<Double>>> findPosibleZeros = P -> Q -> {
        List<Double> posibleZeros = new ArrayList<>();
        for(double p: P){
            for(double q: Q){
                if(!posibleZeros.contains(p/q))
                    posibleZeros.add(p/q);
            }
        }
        return Collections.unmodifiableList(posibleZeros);
    };

    final static Function<Double, Function<List<Double>, List<Double>>> syntheticDivision = divisor -> dividend -> {
        List<Double> resultSet = new ArrayList<>();
        for(int i = 0; i < dividend.size(); i++)
            resultSet.add(i == 0? dividend.get(i): dividend.get(i) + (divisor * resultSet.get(i - 1)));
        return Collections.unmodifiableList(resultSet);
    };

    final static Function<List<Double>, List<Double>> findQuotient = l -> {
        List<Double> quotient = new ArrayList<>(l);
        quotient.remove(quotient.size() - 1);
        return Collections.unmodifiableList(quotient);
    };

    final static Function<List<Double>, Double> findRemainder = l -> l.get(l.size() - 1);

    final static Function<Double, Function<Double, Function<Double, Double>>> findDiscriminant = a -> b -> c -> Math.sqrt((b*b) - 4*a*c);
    final static Function<Double, Function<Double, Function<Double, Double>>> findX1 = a -> b -> c -> ((-b) + findDiscriminant.apply(a).apply(b).apply(c))/(2*a);
    final static Function<Double, Function<Double, Function<Double, Double>>> findX2 = a -> b -> c -> ((-b) - findDiscriminant.apply(a).apply(b).apply(c))/(2*a);
    final static Function<List<Double>, Function<List<Double>, List<Double>>> findRealZeros = polynomial -> realZeros -> {

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
                    System.out.println("A zero was added");
                    Main.findRealZeros.apply(findQuotient.apply(syntheticDivision.apply(possibleZero).apply(polynomial))).apply(realZeros);
                    break;
                }
            }
        }

        return Collections.unmodifiableList(realZeros);
    };



    public static void main(String[] args){

        System.out.println(constantFactors.apply(6.0));
        System.out.println(leadingFactors.apply(2.0));
        System.out.println(findPosibleZeros.apply(constantFactors.apply(6.0)).apply(leadingFactors.apply(2.0)));
        List<Double> dividend = new ArrayList<>();
        dividend.add(2.0);
        dividend.add(-7.0);
        dividend.add(0.0);
        dividend.add(5.0);
        System.out.println(syntheticDivision.apply(3.0).apply(dividend));
        System.out.println(findQuotient.apply(syntheticDivision.apply(3.0).apply(dividend)));
        System.out.println(findRemainder.apply(syntheticDivision.apply(3.0).apply(dividend)));



        List<Double> polynomial = new ArrayList<>();
        polynomial.add(2.0);
        polynomial.add(1.0);
        polynomial.add(-13.0);
        polynomial.add(6.0);
        List<Double> realZeros = new ArrayList<>();
        System.out.println(findRealZeros.apply(polynomial).apply(realZeros));
        System.out.println(findRemainder.apply(syntheticDivision.apply(5.0).apply(polynomial)));

    }
}
