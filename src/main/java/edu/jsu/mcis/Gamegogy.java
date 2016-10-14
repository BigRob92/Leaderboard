package edu.jsu.mcis;
import java.io.*;
import java.util.*;
//import com.opencsv.*;
//import au.com.bytecode.opencsv.*;

public class Gamegogy {
    public Gamegogy() {
        DataSource d = new DataSource();
        Student s = new Student("","","","");
        Course c = new Course("","","","");
    }

    public static void main(String[] args) throws Exception {
        CSVParser parser = new CSVParser();
        for (String arg : args) {
            if (arg.equals("studentids")) {
                parser.studentParser();
            }
            else if (arg.equals("courseids")) {
                parser.courseParser();
            }/*
            else if (arg.equals("student")) {
                foo = s.setStudentId(arg[1]);
                parser.studentLine(foo);

            }
            else if (arg.equals("course")) {
                bar = c.setCourseId(arg[1]);
                parser.courseLine(bar);
            }
            else {System.out.println("Error");}
            */
        //for (String s: args) {
        //    if (s.equals("studentids")) 
        }
    }
}
