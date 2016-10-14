package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import au.com.bytecode.opencsv.*;

public class CSVParser {
    //public CSVParser(String arg){
    //}

    public void studentParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("students.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
        }
    }

    public void courseParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("courses.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
        }
    }

    public void studentLine(String studentId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("students.csv"));
        String [] nextLine;
        String foo = studentId;
        while ((nextLine = reader.readNext()) != null) {
            if (studentId.equals(nextLine[0])) {
            System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
            }
            else {System.out.println("Student not found.");}
        }
    }

    public void courseLine(String courseId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("courses.csv"));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (courseId.equals(nextLine[0])) {
                System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
            }

            } System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
        }

}