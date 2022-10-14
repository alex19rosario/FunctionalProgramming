package loops;

public class Employee {

    private final String name;
    private final int age;
    private final double height;
    private final double salary;

    public Employee(String name, int age, double height, double salary) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getSalary() {
        return salary;
    }
}
