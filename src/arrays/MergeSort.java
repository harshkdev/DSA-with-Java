import java.util.*;

public class MergeSort {

    public static void conquer(int arr[], int low, int mid, int high) {

        int temp[] = new int[high - low + 1];

        int left = low;          // starting index of left part
        int right = mid + 1;     // starting index of right part
        int index = 0;           // index for temp array

        while (left <= mid && right <= high) {

            if (arr[left] < arr[right]) {
                temp[index] = arr[left];
                left++;
            } else {
                temp[index] = arr[right];
                right++;
            }
            index++;
        }

        // Copy remaining elements of left subarray
        while (left <= mid) {
            temp[index] = arr[left];
            left++;
            index++;
        }

        // Copy remaining elements of right subarray
        while (right <= high) {
            temp[index] = arr[right];
            right++;
            index++;
        }

        // Copy sorted elements back to original array
        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }

    public static void mergeSort(int arr[], int low, int high) {

        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        mergeSort(arr, low, mid);       // sort left half
        mergeSort(arr, mid + 1, high);  // sort right half

        conquer(arr, low, mid, high);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, 0, n - 1);


        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            if (arr[i] < min){
                min = arr[i];
            }

        }
        System.out.println(min);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++){
            if (arr[i] > max){
                max = arr[i];
            }


        }
        System.out.println(max);

        System.out.println(max - min);

        sc.close();
    }
}