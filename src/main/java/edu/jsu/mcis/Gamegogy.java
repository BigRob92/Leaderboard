/*package edu.jsu.mcis;

import java.io.*;
import java.util.*;
//import com.opencsv.*;
import au.com.bytecode.opencsv.CSVReader;

public class Gamegogy {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("data/students.csv"), ',', '"', 1);
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }
}
*/

import java.io.*;
import java.util.*;

public class Gamegogy {
    
    //public static String studentId;
    String type;
    String studentId;
    String studentData;
    String courseId;
    String courseData;
    
    
    /*public static void main(String[] args) throws Exception {
        FileReader file = new FileReader(new File("data/students.csv"));
        BufferedReader reader = new BufferedReader(file); 
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            //System.out.println(line.split(",")[0]);
        }
    }
}*/


    
    public static void yankLine(String studentId) throws Exception {
        FileReader file = new FileReader(new File("data/students.csv"));
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null && (line.split(",")[0].equals(studentId))) {
            System.out.println(
                "[" + line.split(",")[0] + "]" + " " + line.split(",")[1] + " "
                + line.split(",")[2] + " " + line.split(",")[3] + "@stu.jsu.edu");

        }
    }

    public static void main(String[] args) throws Exception {
        //String studentId;
        //studentId = "111111";
        yankLine("111111");
    }

}


