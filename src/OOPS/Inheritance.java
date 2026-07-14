package OOPS;

public class Inheritance {

    public static void main(String[] args) {
        Dog dobby = new Dog();
        dobby.eat();
        dobby.legs = 4;
        System.out.println(dobby.legs);

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
    int legs;
}

class Dog extends Mammals {
    String breed;
}

//Derived class
//class Fish extends Animal {
//    int fins;
//
//    void swims(){
//        System.out.println("swims");
//    }
//}
