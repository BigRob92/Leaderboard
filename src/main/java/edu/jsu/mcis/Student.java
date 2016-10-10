package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class Student {

    //FileReader student_file = new FileReader(new File("students.csv"));
    //List <T> studentData = new ArrayList(student_file);

    String studentId;
    String studentFirstName;
    String studentLastName;
    String studentEmail;
/*
    String studentId = studentData.get(0);
    String studentFirstName = studentData.get(1);
    String studentLastName = studentData.get(2);
    String studentEmail = studentData.get(3);
*/


    public String getStudentId() {
        studentId = "111111";
        return studentId;
    }

    public String getStudentFirstName() {
        studentFirstName = "Jerrod";
        return studentFirstName;
    }

    public String getStudentLastName() {
        studentLastName = "Shields";
        return studentLastName;
    }

    public String getStudentEmail() {
        studentEmail = "jshields@stu.jsu.edu";
        return studentEmail;
    }
}
