package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class Student {
    String studentId;
    String studentFirstName;
    String studentLastName;
    String studentEmail;

    public Student(String studentId, String studentFirstName, String studentLastName, String studentEmail) {
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }
    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
}