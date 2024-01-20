package dto;

public class ItEmployee implements  Employee{

    private double salary;
    private int id;
    private String name;

    public ItEmployee() {
    }

    public ItEmployee(double salary, int id, String name) {
        this.salary = salary;
        this.id = id;
        this.name = name;
    }

    @Override
    public void giveHike(double amount) {
            salary += amount;
    }

    @Override
    public void payCut(double amount) {
        if(salary - amount >= 0){
            salary -= amount;
        }
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ItEmployee{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
