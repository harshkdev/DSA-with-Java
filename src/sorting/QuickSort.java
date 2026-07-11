package sorting;

import java.util.Scanner;

public class QuickSort {

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                // Swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        i++;

        // Place pivot in correct position
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pidx = partition(arr, low, high);

            quickSort(arr, low, pidx - 1);
            quickSort(arr, pidx + 1, high);
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // Input size
        int n = sc.nextInt();

        int arr[] = new int[n];

        // Input elements
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Quick Sort
        quickSort(arr, 0, n - 1);

        // Output
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}