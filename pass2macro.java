import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
public class pass2macro {
    public void pass2(HashMap<String, Integer> MNT ,HashMap<Integer, String> MDT)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("expandedcode.txt"));
            HashMap<Integer, String> ala = new HashMap<>();
            String line = "";
            int flag = 0;
            while ((line = br.readLine()) != null) {
                String[] contents = line.split("\\s+");

                if(contents[0].equalsIgnoreCase("start")){
                    flag = 1;
                }
                if(MNT.containsKey(contents[0]) && flag == 1) {
                    for (int i = 1;i<contents.length;i++){
                        ala.put(i-1,contents[i].replaceAll("[,]",""));

                    }
                    Integer MDT_INDEX = MNT.get(contents[0]);
                    Integer index = 0;
                    String code = MDT.get(++MDT_INDEX);
                    for(;;) {
                        String[] code_contents = code.split(" ");
                        for(String x : code_contents){
                            if(x.contains("#")) {
                                x = ala.get(index);
                                index++;
                            }
                            bw.write(x+" ");
                            System.out.print(x+" ");
                        }
                        bw.write("\n");
                        System.out.print("\n");
                        code = MDT.get(++MDT_INDEX);
                        if(code.equals("MEND"))
                            break;
                    }
                }else if(!MNT.containsKey(contents[0]) && flag == 1){
                    System.out.println(line);

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
