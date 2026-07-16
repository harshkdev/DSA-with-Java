package Recursion;

public class PrintNumber {

    public static void printDec(int n){
        //Base value
        if (n == 1){
            System.out.println(n);
            return;
        }
        System.out.print(n+" ");
        //Recursive function
        printDec(n-1);

    }
    public static void main(String[] args) {
        int n = 10;
        printDec(n);
    }
}
