package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DataSourceTest {
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private Student s;
    private DataSource d;
    
    @Before
    public void setup() throws Exception {
        d = new DataSource(); 
    }

    @Test
    public void testReadStudentInfoIntoList() {
        // do work
        assertTrue(true);
    }

    @Test
    public void testReadCourseInfoIntoList() {
        // do work
        assertTrue(true);
    }
}
