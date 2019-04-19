import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Round_Robin {

    static void solve(int[] A_T,int[] B_T,int T_Q) {
        Queue<Integer> RR = new LinkedList<>();
        int[] Bt_C = new int[B_T.length];
        for (int i = 0 ;i<B_T.length;i++)
            Bt_C[i] = B_T[i];

        int time = 0;
        RR.add(0);
        int[] C_T = {0,0,0,0,0,0};
        ArrayList<Integer> sequence = new ArrayList<>();
        while(true) {
            int i = RR.remove();
            sequence.add(i);
            if(B_T[i] <= T_Q && B_T[i] != 0) {
                time = time+B_T[i];
                C_T[i] = time;
                B_T[i] = 0;
                for (int j = 0 ;j < A_T.length ;j++) {
                    if(A_T[j] <= time && i!=j && !RR.contains(j)) {
                        RR.add(j);
                    }
                }
            }
            else if (B_T[i] > T_Q) {
                time = time +T_Q;
                B_T[i] = B_T [i] - T_Q;
                for (int j = 0 ;j < A_T.length ;j++) {
                    if(A_T[j] <= time && i!=j && !RR.contains(j)) {
                        RR.add(j);
                    }
                }
                RR.add(i);
            }
            if(RR.isEmpty())
                break;
        }
        System.out.println("Completion time :");
        for (Integer x: C_T) {
            System.out.println(x);
        }

        System.out.println("TAT : ");
        for (int i =0 ;i < A_T.length ;i++ ) {

            System.out.println(C_T[i] - A_T[i]);
        }

        System.out.println("WT : ");
        for (int i =0 ;i < A_T.length ;i++ ) {

            int y = C_T[i] - A_T[i];
            System.out.println(y-Bt_C[i]);
        }
    }

    public static void main(String args[]) {

        int[] A_T = {0,1,2,3,4};
        int[] B_T = {5,3,1,2,3};
        int T_Q = 2;
        solve(A_T,B_T,T_Q);


    }
}
