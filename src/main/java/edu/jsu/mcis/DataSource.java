package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class DataSource {
    ArrayList<Student> studentList;
    ArrayList<Course> courseList;
    Student s;
    Course c;


    public void DataSource() throws Exception {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        parseStudentFile();
        parseCourseFile();
    }

    public void parseStudentFile() {

    }

    public void parseCourseFile() {
        
    }
}
