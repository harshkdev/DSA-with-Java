package Recursion;

public class FirstOccurrence {

    public static int Occur(int arr[], int key , int i){
        if (i == arr.length){
            return -1;
        }
        if (arr[i] == key){
            return i;
        }
        return Occur( arr , key,i + 1);
    }

    public static void main(String[] args) {
        int arr[] = { 2, 3, 5, 67, 3, 5};
        int key= 67;
        System.out.println(Occur(arr , key , 0));
    }
}
