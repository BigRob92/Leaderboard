//package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class Gamegogy {
    String type;
    String studentId;
    String studentData;
    String courseId;
    String courseData;
    

    public static void yankLine(String studentId) throws Exception {
        FileReader file = new FileReader(new File("students.csv"));
        BufferedReader reader = new BufferedReader(file);
        String line;
        while ((line = reader.readLine()) != null && (line.split(",")[0].equals(studentId))) {
            System.out.println(
                "[" + line.split(",")[0] + "]" + " " + line.split(",")[1] + " "
                + line.split(",")[2] + " " + line.split(",")[3] + "@stu.jsu.edu");
        }
    }


    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        StringBuffer csvContents = new StringBuffer();
        
        
        
        for (String s: args) {
            if (s.equals("studentids")) {
                // THE BELOW NEEDS FULL PATH TO FILE. MUST FIX THIS TO TAKE RELATIVE PATH.
                FileReader file = new FileReader(new File("students.csv"));
                
                /*CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/data/students.csv"));
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(nextLine[0] + nextLine[1]);
                }
                */
                BufferedReader reader = new BufferedReader(file);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.split(",")[0]);
                }
            }
            else if (s.equals("courseids")) {
                // THE BELOW NEEDS FULL PATH TO FILE. MUST FIX THIS TO TAKE RELATIVE PATH.
                FileReader file = new FileReader(new File("/home/ben/Development/cs310/Leaderboard/data/courses.csv"));
                
                /*
                CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/data/courses.csv"));
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(nextLine[0] + nextLine[1]);
                }
                */
                BufferedReader reader = new BufferedReader(file);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.split(",")[0]);
                }
            }
        }
    }

}


