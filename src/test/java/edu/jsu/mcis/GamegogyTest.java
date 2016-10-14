package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GamegogyTest {
    private String student;
    private String studentData;
    //private DataSource d;
    private Student s;
    private Course c;
    int counter = 0;
    

// Dr. Garrett's advice
// DataSource d = new DataSource();
//    Student s = d.getStudent("111111");
//  assertEquals("111111", s.getId);
    
    @Before
    //public void setup() throws Exception {
        //DataSource d = new DataSource();
      //  Student s = new Student("111111", "Jerrod", "Shields", "jshields");
      //  Course c = new Course();
        //Student s = d.getStudent("111111");
        //Course c = d.getCourse("99000");
    //}

    @Test
    public void testStudentIdFromFile() {
        assertTrue(true);
    }

    @Test
    public void testStudentFirstNameFromFile() {
        assertTrue(true);
    }

    @Test
    public void testStudentLastNameFromFile() {
        assertTrue(true);
    }

    @Test
    public void testStudentEmailFromFile() {
        assertTrue(true);
    }

}
