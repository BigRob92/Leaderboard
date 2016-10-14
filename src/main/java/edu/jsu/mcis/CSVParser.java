package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import au.com.bytecode.opencsv.*;

public class CSVParser {
    //Student s = new Student("", "", "", "");
    //Course c = new Course("", "", "", "");

    public void studentParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/students.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0].replaceAll("\"", ""));
        }
    }

    public void courseParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0].replaceAll("\"", ""));
        }
    }

    public void studentLine(String studentId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/students.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].equals('"'+studentId+'"')) {
                System.out.println("["+nextLine[0].replaceAll("\"", "")+"]"
                    +" "+nextLine[1].replaceAll("\"", "")
                    +" "+nextLine[2].replaceAll("\"", "")
                    +" "+nextLine[3].replaceAll("\"", "")
                    +"@jsu.edu");
            }
            else {}
        }
        System.exit(0);
    }

    public void courseLine(String courseId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].equals('"'+courseId+'"')) {
                System.out.println("["+nextLine[0].replaceAll("\"", "")+"]"
                +" "+nextLine[1].replaceAll("\"", "")
                +" "+nextLine[2].replaceAll("\"", "")
                +" "+"("+nextLine[3].replaceAll("\"", "")+" students)");
            }
            //else {System.out.println("Course not found.");}
        }
    }
}
