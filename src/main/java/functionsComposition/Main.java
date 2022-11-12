package functionsComposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args){

        //COMPOSITION WITH PREDICATES
        Predicate<Integer> isPositive = x -> x > 0, isEven = x -> x%2 == 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        Predicate<Integer> isNegative = isPositive.negate();

        System.out.println(isPositiveAndEven.test(2));
        System.out.println(isNegative.test(-5));

        //COMPOSITION WITH CONSUMERS
        Consumer<Double> saveHeight = x -> System.out.println(x + " has been registered"), sendHeight = x -> System.out.println(x + " has been sent");
        Consumer<Double> saveAndSend = saveHeight.andThen(sendHeight);

        saveAndSend.accept(1.74);

        //COMPOSITION WITH FUNCTIONS
        Function<Double, Double> sqr = x -> x*x, half = x -> x/2;
        Function<Double, Double> sqrAndHalf = sqr.andThen(half);

        System.out.println(sqrAndHalf.apply(5.0));

        //COMPOSITION WITH COMPARATORS

        Employee e1 = new Employee("Carlos", 55000.0);
        Employee e2 = new Employee("Alexander", 42000.0);
        Employee e3 = new Employee("Yudelka", 35500.0);
        Employee e4 = new Employee("Pamela", 41000.0);
        Employee e5 = new Employee("Juan", 17000.0);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);

        employeeList.forEach(e -> System.out.println(e.getName()));
        System.out.println();

        Comparator<Employee> byName = Comparator.comparing(Employee::getName);
        Comparator<Employee> bySalary = Comparator.comparing(Employee::getSalary);
        Comparator<Employee> both = byName.thenComparing(bySalary);

        //THIS METHOD IS NOTHING FUNCTIONAL, BUT IT SHOWS HOW WE CAN USE THE COMPARATORS
        Collections.sort(employeeList, both);
        employeeList.forEach(e -> System.out.println(e.getName()));
        System.out.println();

        //THIS IS WHAT THE STREAMS ARE USED LIKE. PRINTING THE NAMES OF THOSE EMPLOYEES WHO EARN MORE THAN 30,000
        employeeList.stream().filter(e -> e.getSalary() > 30000.0).map(Employee::getName).forEach(System.out::println);

        //BUILD A FUNCTION OR A METHOD THAT RECEIVES A TEXT AND A LETTER AS ARGUMENT, IN ORDER TO RETURN ALL THE WORDS THAT START WITH SUCH LETTER











    }
}
