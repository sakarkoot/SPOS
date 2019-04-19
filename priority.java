import java.util.ArrayList;
import java.util.HashMap;

public class priority {

    static void solve(int[] A_T,int[] B_T ,int[] priority) {
        HashMap<Integer,Integer> sequence = new HashMap<>();
        int time = 0;
        ArrayList<Integer> C_T = new ArrayList<>();
        while(true) {
            int process_to_execute=-1;
            int max_priority = 0;
            for (int i = 0;i< A_T.length;i++) {
                if(A_T[i] <= time && priority[i] >= max_priority && priority[i] != -1){
                    process_to_execute = i;
                    max_priority = priority[i];
                }
            }
            if(process_to_execute == -1)
                break;
            time = time + B_T[process_to_execute];
            sequence.put(process_to_execute,time);
            priority[process_to_execute]  = -1;
        }
        System.out.println(sequence);
    }

    public static void main(String args[]) {

        int[] A_T = {0,1,2,3,4,5,6};
        int[] B_T = {4,2,3,5,1,4,6};
        int[] priority = {2,4,6,10,8,12,9};
        solve(A_T,B_T,priority);


    }
}

