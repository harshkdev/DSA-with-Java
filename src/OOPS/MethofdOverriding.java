package OOPS;

public class MethodsOverriding {
    public static void main(String[] args) {
        Deer d = new Deer();
        d.eat();
    }
}

class Animals{
    void eat(){
        System.out.println("eats anything");
    }
}

class Deer extends Animals{
    @Override
    void eat() {
        System.out.println("eats grass");
    }
}
