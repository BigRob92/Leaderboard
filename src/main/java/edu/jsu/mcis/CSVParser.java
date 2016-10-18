package edu.jsu.mcis;
import java.io.*;
import java.lang.*;
import java.util.*;
import au.com.bytecode.opencsv.*;

public class CSVParser {
    //Student s = new Student("", "", "", "");
    //Course c = new Course("", "", "", "");
	private List<Student> students;
	private List<Course> courses;
	private List<Course> courseIds;
	private List<Student> studentIds;
	
	public CSVParser() {
		students = new ArrayList<>();
		studentIds = new ArrayList<>();
		courses = new ArrayList<>();
		
		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/students.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				Student stud = new Student (nextLine[0]);
				students.add(s);
				studentIds.add(stud);
			}
			
			
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
		try {
			CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/courses.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Course c = new Course(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				courses.add(c);
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
    public Student getStudentIds(){
		for(int i = 0; i < studentIds.size(); i++){
			Student stud = studentIds.get(i);
			return stud;
		}
		return null;
    }

    public void courseParser() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:/Users/cody/Desktop/Leaderboard-development/src/main/resources/courses.csv"), ',', '\'', 1);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0].replaceAll("\"", ""));
        }
    }

    public Student getStudent(String studentId) {
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(studentId.equals(s.getId())) return s;
		}
		return null;
    }
	
    public Course getCourse(String courseId) {
		for(int i = 0; i < courses.size(); i++){
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c;
		}
		return null;
    }
}