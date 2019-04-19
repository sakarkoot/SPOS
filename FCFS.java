import java.util.Arrays;
import java.util.HashMap;

public class FCFS {
    static void solve(int[] B_T) {
        int time = 0;
        HashMap<Integer,Integer> C_T = new HashMap<>();
        for(int i =0 ;i<B_T.length ;i++) {
            time += B_T[i];
            C_T.put(i,time);
        }
        System.out.println(C_T);
        int[] W_T = new int[3];

        W_T[0] = 0;

        for (int i = 1;i<3;i++)
            W_T[i] = B_T[i-1] + W_T[i-1];

        for (Integer x: W_T) {
            System.out.println(x);
        }
    }
    public static void main(String[] args) {
        int B_T[] = {24,3,4};

        solve(B_T);

    }
}
