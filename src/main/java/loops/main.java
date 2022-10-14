package loops;

import com.mycompany.functionalprogramming.exercise2_1.Function;

import java.util.ArrayList;
import java.util.List;

import static loops.Map.map;

public class main <T> {

    public static <T> Function<List<T>, T> getHead(){
        return list -> list.get(0);
    }

    public T getHead(List<T> list){
        return list.get(0);
    }

    public List<T> getTail(List<T> list){
        List<T> newList = new ArrayList<>(list);
        newList.remove(0);
        return newList;
    }

    public static <T, U> Function<List<T>, Function<Function<T, U>, List<U>>> mapfunc(){
        return list -> (f -> map(list, f));
    }

    public static void main(String[] args){

        Employee e1 = new Employee("Carlos Rosario", 25, 1.73, 1000);
        Employee e2 = new Employee("Adriel Rosario", 22, 1.78, 1000);
        Employee e3 = new Employee("Yudelka Sanchez", 46, 1.53, 600);
        Employee e4 = new Employee("Adonis Sanchez", 18, 1.80, 300);
        Employee e5 = new Employee("Carlos Diaz", 51, 1.56, 1500);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);

        Function<Employee, String> extractName = Employee::getName;
        Function<Employee, Double> extractHeight = Employee::getHeight;
        Function<Employee, Double> extractSalary = Employee::getSalary;
        Function<Employee, Character> extractFirstChar = e -> e.getName().charAt(0);
        Function<Employee, EmployeeDTO> extractEmployeeDTO = e -> new EmployeeDTO(e.getName(), e.getAge());


        List<String> names = map(employeeList, extractName);
        List<Double> heights = map(employeeList, extractHeight);
        List<Double> salaries = map(employeeList, extractSalary);
        List<Character> characters = map(employeeList, extractFirstChar);
        List<EmployeeDTO> employeeDTOS = map(employeeList, extractEmployeeDTO);



        System.out.println(names);
        System.out.println(heights);
        System.out.println(salaries);
        System.out.println(characters);
        System.out.println(employeeDTOS);
        System.out.println(main.<String>getHead().apply(names));
    }
}
