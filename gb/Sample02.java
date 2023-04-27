package org.gb;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Sample02 {

    private static Random random = new Random();

    /**
     * TODO: 2. generateEmployee должен создавать различных сотрудников (Worker, Freelancer)
     * @return
     */
    static Employee
    generateEmployee(){
        String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман" };
        String[] surnames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов" };

        int salary = random.nextInt(200, 300);
        int index = random.nextInt(30, 50);
        int indexWork = random.nextInt(0, 2);
        int hours = random.nextInt(0, 160);
        int age = random.nextInt(18, 66);
        if (indexWork == 0) {

            return new Worker(names[random.nextInt(10)], surnames[random.nextInt(10)], salary*index, age);
        }
        else {
            return new Freelancer(names[random.nextInt(10)], surnames[random.nextInt(10)], salary, age,  hours);
        }
        }



    public static void main(String[] args) {


        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++)
            employees[i] = generateEmployee();

        for (Employee employee : employees){
            System.out.println(employee);
        }

        System.out.println("Какой тип сортировки необходимо применить:\n1 - по имени\n2 - по ЗП\n3 - по возрасту\n");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        System.out.println(answer);
        if (answer == 1){

            Arrays.sort(employees, new NameComparator());
        }
        if (answer == 2){

            Arrays.sort(employees, new SalaryComparator());
        }
        if (answer == 3){

            Arrays.sort(employees, new AgeComparator());
        }



        //Arrays.sort(employees);

        System.out.printf("\n*** Отсортированный массив сотрудников ***\n\n");

        for (Employee employee : employees){
            System.out.println(employee);
        }

    }

}

class SalaryComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {

        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        return Double.compare( o2.calculateSalary(), o1.calculateSalary());
    }
}

class NameComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        int res = o1.name.compareTo(o2.name);
        if (res == 0){
            res = Double.compare( o2.calculateSalary(), o1.calculateSalary());
        }
        return res;
    }
}
class AgeComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {

        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        return Integer.compare(o1.age, o2.age);
    }
}

abstract class Employee{

    protected String name;
    protected String surName;
    protected double salary;
    protected int age;

    public Employee(String name, String surName, double salary, int age) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
        this.age = age;
    }

    public abstract  double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Среднемесячная заработная плата: %.2f", name, surName, salary);
    }


}

class Worker extends Employee{

    public Worker(String name, String surName, double salary, int age) {
        super(name, surName, salary, age);
    }

    @Override
    public double calculateSalary() {
        return salary ;
        //TODO: Для фрилансера - return 20 * 5 * salary
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; возраст: %d; Заработная плата (фиксированная месячная оплата): %.2f (руб.)", name, surName, age, salary);
    }
}

/**
 * TODO: 1. Доработать самостоятельно в рамках домашней работы
 */
class Freelancer extends Employee{

    private int workingHours;
    private double salaryOfMontch;


    public Freelancer(String name, String surName, double salary, int age,  int workingHours) {
        super(name, surName, salary, age);
        this.workingHours = workingHours;
        salaryOfMontch = salary * workingHours;
    }

    @Override
    public double calculateSalary() {

        return salaryOfMontch;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер; возраст: %d; Заработная плата (почасовая оплата): %.2f (руб.)", name, surName, age, salaryOfMontch);
    }
}