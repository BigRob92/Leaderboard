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
	public List<Course> courseIds;
	public List<Student> studentIds;
	
	public CSVParser() {
		students = new ArrayList<>();
		studentIds = new ArrayList<>();
		courses = new ArrayList<>();
		courseIds = new ArrayList<>();
		
		try {
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/students.csv"), ',', '\"', 1);
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
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses.csv"), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Course c = new Course(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				Course cour = new Course(nextLine[0]);
				courses.add(c);
				courseIds.add(cour);
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
    public List <Student> getStudentIds(){
		return studentIds;
    }

    public List <Course> getCourseIds() {
		return courseIds;
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