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
        DataSource d = new DataSource(); 
    }

    @Test
    public void testReadStudentInfoIntoList() {
        // do work
        assertTrue(false);
    }

    @Test
    public void testReadCourseInfoIntoList() {
        // do work
        assertTrue(false);
    }
}
