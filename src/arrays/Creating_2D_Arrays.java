import java.util.*;
public class Creating_2D_Arrays{
    public static void main(String[] args) {

        //Matrix
        int matrix[][] = new int[3][3];
        int n = matrix.length; int m = matrix[0].length;

        //Scanner Class
        Scanner sc = new Scanner(System.in);

        //Input data in matrix
        System.out.println("Enter the element of matrix: ");
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = sc.nextInt();
            }
        }

        //print matrix
        for (int i = 0; i < matrix.length;i++){
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        System.out.print("Enter the number to search in matrix: ");
        int key = sc.nextInt();

        //Search
        boolean found = false;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (key == matrix[i][j]){
                    System.out.println("Found at ("+i+","+j+")");
                    found = true;
                }
            }
        }
        if (!found){
            System.out.println("Element not found...");
        }
    }
}