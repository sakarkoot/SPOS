import java.util.ArrayList;
import java.util.HashMap;
public class SJF_Preemtive {
    static void solve (int[] A_T ,int[] B_T){
        HashMap<Integer,Integer> C_T = new HashMap<>();
        ArrayList<Integer> sequence = new ArrayList<>();
        int time = 0;
        while(true) {
            int shortest_B_T = 9999;
            int shortest_procss = -1;
            for (int i =0 ;i< A_T.length;i++) {
                if(A_T[i] <= time && shortest_B_T >= B_T[i] && B_T[i] > 0){
                    shortest_B_T = B_T[i];
                    shortest_procss = i;
                }
            }
            if(shortest_procss == -1)
                break;
            sequence.add(shortest_procss);
            time = time +1;
            B_T[shortest_procss] = B_T[shortest_procss] - 1;
            if(B_T[shortest_procss] == 0)
                C_T.put(shortest_procss,time);
        }
        System.out.print(C_T);
    }
    public static void main(String[] args) {

        int[] A_T= {0,2,4,5};
        int[] B_T =  {6,1,4,3};

        solve(A_T,B_T);

    }

}
