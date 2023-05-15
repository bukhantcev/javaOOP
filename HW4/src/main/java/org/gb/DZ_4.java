// a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
// b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
// c. Для хранения фруктов внутри коробки можно использовать ArrayList;
// d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
// вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
// e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
// подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
// Можно сравнивать коробки с яблоками и апельсинами;
// f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
// Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
// Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
// g. Не забываем про метод добавления фрукта в коробку.

package org.gb;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DZ_4 {

    public static Random rand = new Random();

    public static ArrayList<Apple> fillTheAppleBox(int amount) {
        ArrayList<Apple> appleList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            appleList.add(new Apple());
        }
        return appleList;
    }

    public static ArrayList<Orange> fillTheOrangeBox(int amount) {
        ArrayList<Orange> orangeList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            orangeList.add(new Orange());
        }
        return orangeList;
    }

    public static void boxCompare(Box<?> b1, Box<?> b2){
        System.out.printf("\nСравниваем вес коробок №%d-'%s' и №%d-'%s': ", b1.getId(), b1.getName(), b2.getId(), b2.getName());
        if(b1.compareTo(b2))
            System.out.println("вес коробок одинаковый");
        else System.out.println("вес коробок разный");
    }

    public static void shiftFruBox(Box<?> b1, Box<?> b2){
        System.out.printf("\nПробуем пересыпать фрукты из коробки №%d в №%d:\n", b1.getId(), b2.getId());
        if((b1.name).equals(b2.name)){
            b2.setWeight(b1.weight + b2.weight);
            b1.weight = 0;
            System.out.printf("Коробка №%d опустела, вес коробки №%d - %.2f кг\n\n", b1.getId(), b2.getId(), b1.weight + b2.weight);
        }
        else System.out.println("Это коробки с разными фруктами, пересыпать нельзя\n");
    }

    public static void main(String[] args) {

        Box<Apple> box1 = new Box<Apple>(fillTheAppleBox(15));
        Box<Apple> box2 = new Box<Apple>(fillTheAppleBox(26));
        Box<Orange> box3 = new Box<Orange>(fillTheOrangeBox(14));
        Box<Orange> box4 = new Box<Orange>(fillTheOrangeBox(10));

        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);
        System.out.println(box4);

        boxCompare(box1, box4);
        boxCompare(box2, box3);

        shiftFruBox(box1, box4);

        shiftFruBox(box3, box4);

        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);
        System.out.println(box4);

    }

}

abstract class Fruit {
    private final float weight;
    private final String name;

    public Fruit(float weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }


}

class Apple extends Fruit {

    public Apple() {
        super(1.0f, "Яблоки");
    }

}

class Orange extends Fruit {

    public Orange() {
        super(1.5f, "Апельсины");

    }

}

class Box<T extends Fruit> {

    private static final AtomicInteger counter = new AtomicInteger(1);
    ArrayList<T> fruits;
    String name;
    int id;
    float weight;

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
        this.name = fruits.get(0).getName();
        this.id = counter.getAndIncrement();
        this.weight = getWeight();
    }

    public float getWeight() {
        return fruits.get(0).getWeight() * fruits.size();
    }

    public boolean compareTo(Box<?> box) {
        return true ? Float.compare(getWeight(), box.getWeight()) == 0 : false;
    }

    @Override
    public String toString() {

        return "Коробка №" + id + "; Фрукты: " + name + "; вес: " + weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
