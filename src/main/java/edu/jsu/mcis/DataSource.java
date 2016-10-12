package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class DataSource {
    private String studentData = "";
    private String courseData = "";
    private DataSource d;
    private Student s;
    private Course c;

    public DataSource() throws Exception {
        Scanner s_scan = new Scanner(new File("/home/ben/Development/cs310/Leaderboard/data/students.csv"));
        s_scan.useDelimiter(",");
        List<String> studentData = new ArrayList<String>();
        while (s_scan.hasNextLine()) {
            studentData.add(s_scan.nextLine());
        }
        s_scan.close();
        
        Scanner c_scan = new Scanner(new File("/home/ben/Development/cs310/Leaderboard/data/course.csv")); 
        c_scan.useDelimiter(",");
        List<String> courseData = new ArrayList<String>();
        while (c_scan.hasNextLine()) {
            courseData.add(c_scan.nextLine());
        }
        c_scan.close();
    }

    public String getAllStudents() throws Exception {
        d = new DataSource();
        return d.studentData;

    }

    public String getallCourses() throws Exception {
        d = new DataSource();
        return d.courseData;
    }

    public String getStudent(String studentId) {
        //return "";
        //String studentId = s.getStudentId();
        s = new Student();
        String studentFirstName = s.getStudentFirstName();
        String studentLastName = s.getStudentLastName();
        String studentEmail = s.getStudentEmail();
        String studentData = studentId + " " + studentFirstName + " "
            + studentLastName + " " + studentEmail;
        return studentData; 
    }

    public String getCourse(String courseId) {
        c = new Course();
        //String courseId = c.getCourseId();
        String courseTerm = c.getCourseTerm();
        String courseYear = c.getCourseYear();
        String courseSize = c.getCourseSize();
        String courseData = courseId + " " + courseTerm + " "
            + courseYear + " " + courseSize;
        return courseData;
    }
}

