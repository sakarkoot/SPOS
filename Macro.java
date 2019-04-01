
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Macro {
    public static void main(String args[]){
        try{

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw_mnt = new BufferedWriter(new FileWriter("MNT.txt"));
            BufferedWriter bw_MDT = new BufferedWriter(new FileWriter("MDT.txt"));
            String current_line = "";

            // Datastructures
            HashMap<String, Integer> MNT = new HashMap<>();
            HashMap<Integer, String> MDT = new HashMap<>();
            int mntp = 1, mdtp = 1, ala_index = 0;


            while((current_line = br.readLine()) != null)
            {
                        String parts[] = current_line.split("\\s+");
                        if(parts[0].equalsIgnoreCase("Macro"))
                        {
                            current_line = br.readLine();
                            parts = current_line.split("\\s+");
                            MNT.put(parts[0], mdtp);
                            ala_index = 0;
                            while(current_line != null )
                            {
                                MDT.put(mdtp, current_line);
                                mdtp++;
                                if (current_line.equalsIgnoreCase("MEND")) {
                                    break;
                                }
                                current_line = br.readLine();

                                while(current_line.contains("&"))
                                {

                                    current_line = current_line.replace("&",Integer.toString(ala_index++)+"#");
                                }
                            }
                            mntp++;
                        }
            }
            //System.out.println(Arrays.asList(MDT));
            //System.out.println(Arrays.asList(MNT));

            for (String x: MNT.keySet()) {
                System.out.println(x);
                bw_mnt.write(x+"\t");
                bw_mnt.write( (MNT.get(x).toString())+"\n");

            }
            for (Integer x: MDT.keySet()) {
                System.out.println(x);
                bw_MDT.write(x.toString()+"\t");
                bw_MDT.write(MDT.get(x));
                bw_MDT.write("\n");
            }
            bw_MDT.close();
            bw_mnt.close();

            System.out.println("Cntinue to Pass 2 ? [y/n]");
            Scanner s = new Scanner(System.in);

            String ch = s.next();
            if(ch.equals("Y") || ch.equals("y"))
                new pass2macro().pass2(MNT,MDT);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
