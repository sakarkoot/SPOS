import pack.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pass1 {

    public static void main(String[] args)
    {
        try {
            HashMap<String, String> Imperative_stmnt = new HashMap<>();
            HashMap<String, String> Declarative_stmnt = new HashMap<>();
            HashMap<String, String> Assembler_directives = new HashMap<>();

            // Imperative Statements
            Imperative_stmnt.put("STOP", "00");
            Imperative_stmnt.put("ADD", "01");
            Imperative_stmnt.put("SUB", "02");
            Imperative_stmnt.put("MULT", "03");
            Imperative_stmnt.put("MOVER", "04");
            Imperative_stmnt.put("MOVEM", "05");
            Imperative_stmnt.put("COMP", "06");
            Imperative_stmnt.put("BC", "07");
            Imperative_stmnt.put("DIV", "08");
            Imperative_stmnt.put("READ", "09");
            Imperative_stmnt.put("PRINT", "10");


            // Assembler Directives
            Assembler_directives.put("END", "02");
            Assembler_directives.put("START", "01");
            Assembler_directives.put("ORIGIN", "03");
            Assembler_directives.put("EQU", "04");
            Assembler_directives.put("LTORG", "05");


            //Declarative
            Declarative_stmnt.put("DC", "01");
            Declarative_stmnt.put("DS", "02");


            // DataStructures(Tables)
            HashMap<String, String> symtab = new HashMap<>();
            HashMap<String, String> litab = new HashMap<>();
            ArrayList<Integer> pooltab = new ArrayList<>();

            // Main Logic
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            String current_line;
            int location_pointer = 0;
            int literal_table_pointer = 1;
            int symbol_table_pointer = 1;
            int pooltab_pointer = 1;
            int j = 1;
            while((current_line = br.readLine())!=null)
            {
                String[] line = current_line.split(" ");


                for (int i=0; i<line.length; i++)
                {

                //STEP 1. Check IF its is "Start":

                        line[i] = line[i].replace(",","");
                        if (line[i].equals("START"))
                        {
                            bw.write("AD\t01\t");
                            bw.write("C\t" + line[i+1] + "\n");
                            location_pointer = Integer.parseInt(line[i+1]);
                            break;
                        }


                //STEP 2. Check if it is symbol table ..

                        int symbol_flag = 0;
                        for (Map.Entry m : symtab.entrySet())
                        {
                            if (line[i].equals(m.getKey()) && !line[i].equals(""))
                            {
                                m.setValue(String.valueOf(location_pointer));
                                symbol_flag = 1;
                                //System.out.println(line[i]);
                                bw.write("S\t" + j++ + "\t");


                            }
                        }

                        if(symbol_flag == 0) {


                            //Imperative Statements...
                            int is_flag = 0;
                            for (Map.Entry m : Imperative_stmnt.entrySet()) {
                                if (line[i].equals(m.getKey())) {
                                    bw.write("IS\t" + m.getValue() + "\t");
                                    is_flag = 1;
                                }
                            }
                            //Assembler Directives Statements...

                            int ad_flag = 0;
                            for (Map.Entry m : Assembler_directives.entrySet()) {
                                if (line[i].equals(m.getKey())) {
                                    //bw.write("AD\t" + m.getValue() + "\t");
                                    ad_flag = 1;
                                }
                            }
                            int dl_flag = 0;
                            //Declarative Statements...
                            for (Map.Entry m : Declarative_stmnt.entrySet()) {
                                if (line[i].equals(m.getKey())) {
                                    bw.write("DL\t" + m.getValue() + "\t");
                                    bw.write("C\t" + line[i + 1] + "\t");
                                    dl_flag = 1;
                                }
                            }
                            if(dl_flag == 1)
                            {
                                break;
                            }

                            //Literals..
                            int lit_flag = 0;
                            if (line[i].contains("=")) {
                                litab.put(line[i], "");
                                bw.write("L\t" + literal_table_pointer + "\t");
                                literal_table_pointer++;
                                lit_flag = 1;
                            }


                            //Miscelenious

                            int miscelenious = 0;

                            if (line[i].equals("LTORG")) {
                                pooltab.add(pooltab_pointer);
                                for (Map.Entry m : litab.entrySet()) {
                                    if (m.getValue().equals("")) {
                                        m.setValue(location_pointer);
                                        bw.write("DL\t01\t");
                                        bw.write("C\t"+m.getKey()+"\n");
                                        location_pointer++;
                                        pooltab_pointer++;
                                    }
                                }
                                miscelenious = 1;
                            }
                            if (line[i].equals(("END"))) {
                                pooltab.add(pooltab_pointer);
                                for (Map.Entry m : litab.entrySet()) {
                                    if (m.getValue().equals("")) {
                                        m.setValue(location_pointer);
                                        location_pointer++;
                                    }
                                }
                                miscelenious = 1;
                            }
                            if (line[i].equals("EQU")) {
                                symtab.put("equ", String.valueOf(location_pointer));
                                miscelenious = 1;
                            }

                            //REG
                            if (line[i].equals("AREG")) {
                                bw.write("RG\t1\t");
                            } else if (line[i].equals("BREG")) {
                                bw.write("RG\t2\t");
                            } else if (line[i].equals("CREG")) {
                                bw.write("RG\t3\t");
                            } else if (line[i].equals("DREG")) {
                                bw.write("RG\t4\t");
                            } else if (dl_flag + is_flag + ad_flag == 0 && miscelenious == 0 && lit_flag == 0 && i == 0) {
                                symtab.put(line[i], String.valueOf(location_pointer));
                                bw.write("S\t" + symbol_table_pointer + "\t");
                                symbol_table_pointer++;

                            } else if (dl_flag + is_flag + ad_flag == 0 && i >= 2 && miscelenious == 0 && lit_flag == 0) {
                                symtab.put(line[i], "");
                                bw.write("S\t" + symbol_table_pointer + "\t");
                                symbol_table_pointer++;
                            }
                        }
                }
                bw.write("\n");
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
