package Recursion;

public class TilingProblem {

    public static  int tilingProblem(int n){ // 2 * n size
        if ( n == 0 || n == 1){
            return 1;
        }
        //Vertical Tiles
        int fnm1 = tilingProblem(n - 1);

        //Horizontal Tiles
        int fnm2 = tilingProblem(n - 2);
        int totWays = fnm1 + fnm2;
        return totWays;
    }
    public static void main(String[] args) {
        System.out.println(tilingProblem(2));
    }
}
