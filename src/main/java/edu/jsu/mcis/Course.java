package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class Course {
    String courseId = "";
    String courseTerm = "";
    String courseYear = "";
    String courseSize = "";

    public Course(String courseId, String courseTerm, String courseYear, String courseSize) {

    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }
    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseSize(String courseSize) {
        this.courseSize = courseSize;
    }
    public String getCourseSize() {
        return courseSize;
    }

}
