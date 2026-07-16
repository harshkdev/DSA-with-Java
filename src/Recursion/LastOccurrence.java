package Recursion;

public class LastOccurrence {
    public static int lastOccurrence (int arr[], int key , int i){
        if (i == arr.length){
            return -1;
        }
        int isFound = lastOccurrence(arr, key , i + 1);
        if (isFound == -1 && arr[i] == key){
            return i;
        }

        return isFound;
    }

    public static int power(int x , int n){
        if (n == 0){
            return 1;
        }
//        int xnm1 = power(x , n-1);
//        int xn = x * xnm1;
//        return xn;

        return x * power(x , n-1);
    }

    public static int optimizedPower(int a, int n) {

        if (n == 0) {
            return 1;
        }

        int halfPower = optimizedPower(a, n / 2);
        int halfPowerSq = halfPower * halfPower;

        if (n % 2 != 0) {
            halfPowerSq = a * halfPowerSq;
        }

        return halfPowerSq;
    }

    public static void main(String[] args) {
        int arr[] = {8, 3, 6, 9, 5, 10, 2, 5, 3};
        System.out.println(optimizedPower(2 , 10));
    }
}
