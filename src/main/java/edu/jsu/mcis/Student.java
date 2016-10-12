package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class Student {
    String studentId;
    String studentFirstName;
    String studentLastName;
    String studentEmail;

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
