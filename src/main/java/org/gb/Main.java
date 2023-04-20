package org.gb;

public class Main {
    public static void main(String[] args) {
                Cat[] cats = {new Cat("Barsik", 78),
                        new Cat("Semen", 2),
                        new Cat("Bamboleilo", 3435),
                        new Cat("Begemot", 54),
                        new Cat("Bonni", 345),
                        new Cat("Arkadii", 100),
                        new Cat("Bomg", 44),
                        new Cat("Tamagavk", 467),
                };

                Plate plate = new Plate(1000);
                plate.info();
                for(Cat cat: cats) {


                    cat.eat(plate.getFood() - cat.getAppetite());
                    plate.setFood(plate.getFood() - cat.getAppetite());
                    plate.info();
                }
            }
        }
