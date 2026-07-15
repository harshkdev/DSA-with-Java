package OOPS;
import java.util.*;

public class AbstractClass {
    public static void main(String[] args) {
        Mustang myHorse = new Mustang();
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