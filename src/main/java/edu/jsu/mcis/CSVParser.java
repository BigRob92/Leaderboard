package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import au.com.bytecode.opencsv.*;

public class CSVParser {
    //Student s = new Student("", "", "", "");
    Course c = new Course("", "", "", "");

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
        Student s = new Student("","","","");
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].equals(studentId)) {
                s.setStudentId(nextLine[0]);
                s.setStudentFirstName(nextLine[1]);
                s.setStudentLastName(nextLine[2]);
                s.setStudentEmail(nextLine[3]);
                s.toString();
                System.out.println("Hello");
            }
            //else {System.out.println("Student not found.");}
        }
    }

    public void courseLine(String courseId) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (courseId.equals(nextLine[0])) {
                System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]);
            }
            //else {System.out.println("Course not found.");}
        }
    }
}
