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

    public static void main(String[] args) {
        int arr[] = {8, 3, 6, 9, 5, 10, 2, 5, 3};
        System.out.println(power(2 , 4));
    }
}
