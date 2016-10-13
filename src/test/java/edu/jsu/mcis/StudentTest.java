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
        s = new Student("","","","");
    }

    @Test
    public void testStudentIdFromFile() {
        s.setStudentId("111111");
        assertEquals(s.getStudentId(), "111111");
    }
    
    @Test
    public void testStudentFirstNameFromFIle() {
        s.setStudentFirstName("Jerrod");
        assertEquals(s.getStudentFirstName(), "Jerrod");
    }

    @Test
    public void testStudentLastNameFromFile(){
        s.setStudentLastName("Shields");
        assertEquals(s.getStudentLastName(), "Shields");
    }

    @Test
    public void testStudentEmailFile(){
        s.setStudentEmail("jshields@stu.jsu.edu");
        assertEquals(s.getStudentEmail(), "jshields@stu.jsu.edu");
    }

}
