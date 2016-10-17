/*package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import java.lang.Object;
import au.com.bytecode.opencsv.*;


public class DataSource {
    ArrayList<Student> studentList;
    ArrayList<Course> courseList;
	
    public void DataSource() throws Exception {
        studentList = new ArrayList<Student>();
        courseList = new ArrayList<Course>();
    }

    public List <String> parseStudentFile(String id) throws FileNotFoundException {
		try{
			List<Student> studentInfo = new ArrayList<>();
			CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/students.csv"));
			String [] nextLine;
			while((nextLine = reader.readNext()) != null){
				
			String [] nextLine = reader.split(",");
			
			}
		}
		catch(IOException ie){};
		return studentInfo;
    }

    public void parseCourseFile() {

    }
}*/