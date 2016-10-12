package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest {
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private Student s;


    @Before
    public void setup() {
        s = new Student();
    }

    /*
    @Test
    public void testStudentIdFromFile() {
        assertEquals(s.getStudentId(), "111111");
    }
    */
    
    @Test
    public void testStudentFirstNameFromFIle() {
        assertEquals(s.getStudentFirstName(), "Jerrod");
    }

    @Test
    public void testStudentLastNameFromFile(){
        assertEquals(s.getStudentLastName(), "Shields");
    }

    @Test
    public void testStudentEmailFile(){
        assertEquals(s.getStudentEmail(), "jshields@stu.jsu.edu");
    }

}
