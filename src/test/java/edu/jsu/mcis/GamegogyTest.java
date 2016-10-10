/*
package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GamegogyTest {
    private String student;
    private String studentData;
    int counter = 0;

// Dr. Garrett's advice
// DataSource d = new DataSource();
//    Student s = d.getStudent("111111");
//  assertEquals("111111", s.getId);
    //@Before

  
    @Test
    public void testStudentIdFromFile() {
        try {
        String studentID = "111111";
        FileReader student_file = new FileReader(new File("/home/ben/Development/cs310/Leaderboard/data/students.csv"));
        BufferedReader reader = new BufferedReader(student_file);
        
        //studentID = "111111";
        //this.studentID = "111111";
        String line = null;
        while((line = reader.readLine()) != null) {
            counter++;
            if(counter == 2) {
                assertEquals(studentID, line.split(",")[0]);
            }
        }
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + student_file + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + student_file + "'");
        }
        finally {
            if (student_file != null) {
                reader.close();
            }
        }
    }

    @Test
    public void testStudentFirstNameFromFile() {
        //this.studentFirstName = ("Jerrod");
        assertTrue(false);
    }

    @Test
    public void testStudentLastNameFromFile() {
        //this.studentLastName = ("Shields");
        assertTrue(false);
    }

    @Test
    public void testStudentEmailFromFile() {
        //this.studentEmail = ("jshields");
        assertTrue(false);
    }

}
*/