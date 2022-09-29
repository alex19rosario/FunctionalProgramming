/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.functionalprogramming.exercise2_1;

/**
 *
 * @author carlos
 */
public class Excercise2_1 {
    
    final static Function<Double, Function <Double, Double>> add = a -> (b -> a+b);
    final static Function<Double, Function <Double, Double>> substract = a -> (b -> a-b);
    final static BinaryOperator mult = a -> (b -> a*b);
    final static BinaryOperator div = a -> (b -> a/b);
    final static Function<Double, Double> sqrt = x -> Math.sqrt(x);

    //====================SQUARE ROOT OF A NUMBER BY NEWTON'S METHOD=========================================================================================
    final static Function<Double, Double> abs = Math::abs;
    final static Function<Double, Double> square = a -> Math.pow(a, 2);
    final static Function<Double, Function<Double, Boolean>> good_enough = guess -> (x -> (abs.apply(square.apply(guess) - x) < 0.0001? true: false));
    final static Function<Double, Function<Double, Double>> avg = a -> (b -> (a+b)/2);
    final static Function<Double, Function<Double, Double>> improve = guess -> (x -> avg.apply(guess).apply(x/guess));

    final static Function<Double, Function<Double, Double>> improve_cube = guess -> (x -> ((x/square.apply(guess)) + 2.0*guess)/3.0);
    final static Function<Double, Function<Double, Boolean>> imp_good_enough = guess -> (x -> (abs.apply(improve.apply(guess).apply(x) - guess) < 0.0001? true: false));
    public final static  Function<Double, Function<Double, Double>> sqrt_iter = guess -> (x -> good_enough.apply(guess).apply(x) == true? guess: Excercise2_1.sqrt_iter.apply(improve.apply(guess).apply(x)).apply(x));
    public final static Function<Double, Function<Double, Double>> imp_sqrt_iter = guess -> (x -> imp_good_enough.apply(guess).apply(x) == true? guess: Excercise2_1.imp_sqrt_iter.apply(improve.apply(guess).apply(x)).apply(x));
    public final static Function<Double, Double> sqrt_newton = x -> sqrt_iter.apply(1.0).apply(x);
    public final static Function<Double, Double> imp_sqrt_newton = x -> imp_sqrt_iter.apply(1.0).apply(x);
    //=========================================================================================================================================================

    public static final Function<Integer, Integer> fact = n -> n == 0? 1: n * Excercise2_1.fact.apply(n-1);
    static Function<Integer, Integer> compose(final Function<Integer, Integer> f1, final Function<Integer, Integer> f2){
        
        return new Function<Integer, Integer>(){
            @Override
            public Integer apply(Integer arg){
                return f1.apply(f2.apply(arg));
            }
        };     
    }
    
    public void add(Double a, Double b){
        Function <Double, Function<Double, Double>> add = x -> (y -> (x+y));
        System.out.println(add.apply(a).apply(b));
    }
    
//    static Function<Integer, Integer> comp(final Function<Integer, Integer> f1, final Function<Integer, Integer> f2){
//        return (Integer x) -> f1.apply(f2.apply(x));
//    }
//    
//    static Function<Double, Double> compD(final Function<Double, Double> f1, final Function<Double, Double> f2){
//        return x -> f1.apply(f2.apply(x));
//    }
//    
//    static Double quadraticForm(Double a, Double b, Double c){
//        return div.apply(add.apply(sqrt.apply(substract.apply(square.apply(b)).apply(mult.apply(4.0).apply(a*c)))).apply(b*(-1))).apply(2*a);
//    }
    
    public static <T,U,V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherComp(){
        return f -> (g -> (x -> (f.apply(g.apply(x)))));
    }

    static <T> Function<Function<T, T>, Function<Function<T, T>, Function<T, T>>> higherComp2(){
        return f -> (g -> (x -> f.apply(g.apply(x))));
    }
    
    static <X, Y, Z> Function<Y, Z> partial(X x, Function<X, Function<Y, Z>> g){
        return g.apply(x);
    }
    
    static <X, Y, Z> Function<X, Z> partial2(Y y, Function<X, Function<Y, Z>> g){
        return x -> (g.apply(x).apply(y));
    }
    
    static <A, B, C, D> String func(A a, B b, C c, D d){
        return String.format("%s, %s, %s, %s", a, b, c, d);
    }
    
    static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> curriedFunc(){
        return a -> (b -> (c -> (d -> String.format("%s, %s, %s, %s", a, b, c, d))));
    }

    public static int factorial(int x){
        return x == 0 ? 1 : x * factorial(x - 1);
    }


    
    public static Double greatest(Double x, Double y, Double z){
        if(x > y && x > z)
            return x;
        if(y > x && y > z)
            return y;
        else return z;
    }

    public static Double secondGreatest(Double x, Double y, Double z){
        if((x > y && x < z) || (x > z && x < y))
            return x;
        if((y > x && y < z) || (y > z && y < x))
            return y;
        else return z;
    }


    
    
    public static void main(String[] args){
        
        Function<Double, Double> methodreference1 = Math::sqrt;
        Function<Double, Double> mf2 = Math::abs;
        
        Function<Integer, Integer> triple = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg*3; 
            }
        };
        
        
        Function<Integer, Integer> sqr = new Function<>(){
            @Override
            public Integer apply(Integer arg){
                return arg*arg;
            }
        };
        
        
        
        
        Function<Integer, Integer> timesTwo = x -> x*2;
 
        System.out.println(compose(triple, sqr).apply(3));
        System.out.println(compose(sqr, triple).apply(3));
//        System.out.println(comp(triple, sqr).apply(3));
//        System.out.println(comp(sqr, triple).apply(3));
//        System.out.println(comp(sqr, timesTwo).apply(2));
//        
        
        //--------------------------------------------------------------
        
        
        Function<Double, Double> half = x -> x/2;
        Function<Double, Double> oneThird = (Double x) -> x/3;
        Function<Double, Double> oneFourth = x -> x/4;
        Function<Double, Double> thirdPower = x -> x*x*x;
        //Function<Double, Double> sqrtOfHalf = x -> compD(sqrt, half).apply(x);
        
        Function<Double, Function<Double, Double>> add2 = new Function<>() {
            @Override
            public Function<Double, Double> apply(Double a) {
                return new Function<>() {
                    @Override
                    public Double apply(Double b) {
                        return a+b;
                    }
                };
            }
        };
        Function <Double, Function<Double, Function<Double,Double>>> x1 = a -> (b -> (c -> (-b+Math.sqrt(Math.pow(b, 2)-(4*a*c)))/(2*a)));
        Function <Double, Function<Double, Function<Double,Double>>> x2 = a -> (b -> (c -> (-b-Math.sqrt(Math.pow(b, 2)-(4*a*c)))/(2*a)));

        Function <Double, Function<Double, Function<Double, Double>>> discriminant = a -> (b -> (c -> (Math.pow(b, 2) - (4*a*c))));
        Function<Double, Function<Double, Function<Double, Function<Double, Double>>>> position = t -> (x0 -> (v0 -> (a -> x0 + (v0*t)+(a*t*t)/2)));
        
        Function<Function<Double, Double>, Function<Double, Double>> compose2 = new Function<>() {
            @Override
            public Function<Double, Double> apply(Function<Double, Double> f) {
                return new Function<>() {
                    @Override
                    public Double apply(Double g) {
                        return f.apply(g);
                    }
                };
            }
        };

        Function<Function<Double, Double>, Function<Function<Double, Double>, Function<Double,Double>>> comp = f -> (g -> (x -> f.apply(g.apply(x))));

//        System.out.println(compD(sqrt, half).apply(8.0));
//        System.out.println(compD(half, sqrtOfHalf).apply(8.0));
//        System.out.println(add.apply(5.0).apply(5.0));
//        System.out.println(add2.apply(5.0).apply(5.0));
//        System.out.println(quadraticForm(3.0, -5.0, -1.0));

        BinaryOperator squareArea = b -> (h -> (b*h));
        Function<Double, Function<Double, Function<Double, Double>>> cubicVolume = b -> (h -> (d -> squareArea.apply(b).apply(h)*d));
        
        
        Function<Double, Function<Double, Double>> rectangleArea = h -> (b -> h*b);
        Function<Double, Double> rectangleTen = rectangleArea.apply(10.0);
        Double example = rectangleTen.apply(5.0);
        Function<Double, Function<Double, Function<Double, Function<Double, Double>>>> sqrDistance = x11 -> (y1 -> (x22 -> (y2 -> Math.pow(x22-x11,2) + Math.pow(y2-y1,2))));
        Function<Double, Function<Double, Function<Double, Double>>> sumSqrOfTwoGreatest = a -> (b -> (c -> Math.pow(greatest(a, b, c), 2) + Math.pow(secondGreatest(a, b, c),2)));





        
        System.out.println(x1.apply(3.0).apply(-5.0).apply(-1.0));
        System.out.println(x2.apply(3.0).apply(-5.0).apply(-1.0));
        System.out.println(discriminant.apply(1.0).apply(4.0).apply(-1.0));
        System.out.println(comp.apply(half).apply(thirdPower).apply(5.0));
        System.out.println(comp.apply(x -> x*x).apply(x -> x/2).apply(25.0));
        System.out.println(position.apply(2.0).apply(5.0).apply(15.0).apply(4.0));
        System.out.println(Excercise2_1.<Double, Double, Double>higherComp().apply(half).apply(thirdPower).apply(5.0));
        System.out.println(Excercise2_1.<Double>higherComp2().apply(half).apply(thirdPower).apply(5.0));
        System.out.println(cubicVolume.apply(3.0).apply(3.0).apply(3.0));
        System.out.println(example);
        System.out.println(Excercise2_1.<Double, Integer, Boolean, Double>func(25.0, 5, true, 3.0));
        System.out.println(Excercise2_1.<Double, Integer, Boolean, Double>curriedFunc().apply(25.0).apply(5).apply(true).apply(3.0));
        System.out.println(factorial(5));
        System.out.println(fact.apply(5));
        System.out.println(sqrDistance.apply(0.0).apply(0.0).apply(0.0).apply(5.0));
        System.out.println(sumSqrOfTwoGreatest.apply(3.0).apply(2.0).apply(1.0));
        System.out.println(sqrt_newton.apply(25.0));
        System.out.println(square.apply(sqrt_newton.apply(0.00005)));
        System.out.println(square.apply(imp_sqrt_newton.apply(0.00005)));
    }
}
