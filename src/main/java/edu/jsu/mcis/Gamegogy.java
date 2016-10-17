package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Gamegogy {

    public static void main(String[] args) throws Exception {
        CSVParser parser = new CSVParser();
        String studentId = "";
        String courseId = "";
        String output = "";

        for (String arg : args) {
            if (arg.equals("studentids")) {
                parser.studentParser();
            }
            else if (arg.equals("courseids")) {
                parser.courseParser();
            }
            else if (arg.equals("student")) {
                studentId = args[1];
				System.out.println(parser.getStudent(studentId));
            }
            else if (arg.equals("course")) {
                courseId = args[1];
                System.out.println(parser.courseLine(courseId));
            }
            else {System.exit(0);}
        }
    }
}
