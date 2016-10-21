package edu.jsu.mcis;
import java.io.*;


public class Gamegogy {

    public static void main(String[] args) throws Exception {
        CSVParser parser = new CSVParser();
        String studentId = "";
        String courseId = "";

        for (String arg : args) {
            if (arg.equals("studentids")) {
                System.out.println(parser.getStudentIds());
            }
            else if (arg.equals("courseids")) {
                System.out.println(parser.getCourseIds());
            }
            else if (arg.equals("student")) {
                studentId = args[1];
				System.out.println(parser.getStudent(studentId));
            }
           else if (arg.equals("course")) {
                courseId = args[1];
                System.out.println(parser.getCourse(courseId));
            }
            else {System.exit(0);}
        }
    }
    
}
