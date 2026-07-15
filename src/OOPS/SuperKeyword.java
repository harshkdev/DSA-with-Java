package OOPS;

public class SuperKeyword {
    public static void main(String[] args) {
        Horse1 h = new Horse1();
        System.out.println(h.color);
    }
}

class Animal1{
    String color;
    Animal1(){
        System.out.println("Animal Constructor is called...");
    }
}

class Horse1 extends Animal1{
    Horse1(){
        super.color = "brown";
        System.out.println("Horse constructor is called");
    }
}
