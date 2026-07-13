package Bit_Manuplation;

import java.util.Scanner;

public class OddOrEven {
    public static void OddOrEven(int n){
        int bitMask = 1;
        if ((n & bitMask) == 0){
            //even number
            System.out.println("Even number");
        }else{
            System.out.println("Odd number");
        }
    }
    public static void main (String[] args){
        OddOrEven(2);
        OddOrEven(3);
        OddOrEven(11);

    }
}
