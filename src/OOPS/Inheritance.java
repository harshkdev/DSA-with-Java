package OOPS;

import org.w3c.dom.ls.LSOutput;

public class Inheritance {

    public static void main(String[] args) {
//        Dog dobby = new Dog();
//        dobby.eat();
//        dobby.legs = 4;
//        System.out.println(dobby.legs);

    }
}

//Base class
class Animal {
    String color;

    void eat(){
        System.out.println("eats");
    }

    void breathe(){
        System.out.println("breathe");
    }
}

class Mammals extends Animal {
    void walk(){
        System.out.println("walks");
    };
}

class Fish extends Animal{
    void swim(){
        System.out.println("swim");
    }
}

class Birds extends Animal{
    void walk(){
        System.out.println("Walk");
    }
}

//class Dog extends Mammals {
//    String breed;
//}

//Derived class
//class Fish extends Animal {
//    int fins;
//
//    void swims(){
//        System.out.println("swims");
//    }
//}
