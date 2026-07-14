package OOPS;
import java.util.*;

public class OOPS {

    public static void main(String[] args) {
        Pen p1 = new Pen(); // Create a pen object p1
        p1.setColor("blue");
        System.out.println(p1.getColor());
        p1.setTip(5);

        System.out.println(p1.getTip());


        p1.setColor("Yellow");
        System.out.println(p1.getColor());


    }
}

class BankAccount{
    private String username;
    private String password;
    public void setPassword(String pwd){
        password = pwd;
    }
}

class Pen{
    String color;
    int tip;

    //Getters

    String getColor(){
        return this.color;
    }

    int getTip(){
        return this.tip;
    }

    //Setters

    void setColor(String newColor){
        this.color = newColor;
    }


    void setTip(int tip){
        this.tip = tip;
    }
}








