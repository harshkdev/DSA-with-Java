package OOPS;

public class AbstractClass {
    public static void main(String[] args) {
        Horse h = new Horse();
        h.eat();
        h.walk();
        System.out.println(h.color);

        Chicken c = new Chicken();
        c.eat();
        c.walk();
        System.out.println(h.color);
    }
}


abstract class animal{
    String color;
    animal(){
        color = "brown";
    }
    void eat(){
        System.out.println("animals eats");
    }
    abstract void walk();
}

class Horse extends animal{
    void changeColor(){
        color = "dark brown";
    }

    class Mustang extends Horse{
        Mustang(){
            System.out.println("Mustang Constructor called...");

        }
    }
    void walk(){
        System.out.println("walks on 4 legs");
    }
}

class Chicken extends animal{
    void walk(){
        System.out.println("walks on 2 legs");
    }
    void changeColor(){
        color = "yellow";
    }
}