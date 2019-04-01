import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static int bankers(int[][] allocation,int[][] maximum,int[] available,ArrayList<Integer> process) {

        // Step 1: Calculate need matrix..  need = max - allocation

        int[][] need = new int[5][3];

        for (int i = 0;i<5;i++) {
            for (int j = 0; j < 3; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }

        // Step 2. If need <= available ,.. then execute else skip..

        boolean[] finished = new boolean[5];
        for (int i = 0;i<5;i++){
            finished[i] = false;
        }

        ArrayList<Integer> sequence = new ArrayList<>();
        int count = 0;
        int flag = 1;
        while(count < 5 && flag == 1) {
            flag = 0;
            for(int k = 0;k<process.size();k++) {
                if(!finished[k]) {

                    int cnt = 0;
                    for (int i = 0; i < 3; i++) {
                        if (need[k][i] <= available[i]) {
                            cnt++;
                        }
                    }
                    if (cnt == 3) {

                        for (int i = 0; i < 3; i++) {
                            available[i] = available[i] + allocation[k][i];
                        }
                        sequence.add(process.get(k));
                        finished[k] = true;
                        count++;
                        flag = 1;

                    }
                }
            }
        }
        if(count == 5){
            System.out.print("\nSafe Allocation possible\nSequence :");
            System.out.println(Arrays.asList(sequence));
        }else{
            System.out.println("Safe allocation not possible..");
        }


        return 0;



    }

    public static void main(String[] args) {


        int[][] allocation = {  {0, 1, 0},
                                {2, 0, 0},
                                {3, 0, 2},
                                {2, 1, 1},
                                {0, 0, 2}};
        int[][] maximun = { {7, 5, 3},
                            {3, 2, 2},
                            {9, 0, 2},
                            {2, 2, 2},
                            {4, 3, 3}};
        int[] available = {3, 3, 2};

        ArrayList<Integer> processes = new ArrayList<>();

        for (int i = 0 ;i<5;i++){
            processes.add(i);
        }

        bankers(allocation,maximun,available,processes);
    }
}
