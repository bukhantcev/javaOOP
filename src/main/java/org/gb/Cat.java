package org.gb;

public class Cat {
    private String name;
    private int appetite;

    private int satiety;

    public int getAppetite() {
        return appetite;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    public void eat(int satiety) {
        this.satiety = satiety;
        if(satiety < 0){
            System.out.printf("%s: 'Маловато будет, не буду есть!'\n", name);
        }
        else {
            System.out.printf("%s: 'Ништяк, поел!'\n", name);
        }
    }
}
