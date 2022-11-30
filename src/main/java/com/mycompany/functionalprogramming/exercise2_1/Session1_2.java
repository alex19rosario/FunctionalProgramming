package com.mycompany.functionalprogramming.exercise2_1;

public class Session1_2 {



    public static void main(String[] args){
        Function<Integer, Integer> fact = x -> {
            int counter = 1;
            for(int i = 2; i <= x; i++) {
                counter = counter * i;
                System.out.println(counter);
            }
            return counter;
        };

        Function<Integer, Integer> cube = x -> x*x*x;
        Function<Integer, Integer> sqr = x -> x*x;
        Function<Integer, Integer> f = x -> cube.apply(x) + 2*sqr.apply(x) - 13*x + 10;
        System.out.println(f.apply(1));
        System.out.println(f.apply(-1));
        System.out.println(f.apply(2));
        System.out.println(f.apply(-2));
        System.out.println(f.apply(5));
        System.out.println(f.apply(-5));
        System.out.println(f.apply(10));
        System.out.println(f.apply(-10));

        


    }
}
