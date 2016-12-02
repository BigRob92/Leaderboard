package edu.jsu.mcis;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;


public class CSVParser {

	private List<Student> students;
	private List<String> studentIds;
	private String studentName;
	private String studentEmail;
	private List<Course> courses;
	private List<String> courseIds;
	private String courseTerm;
	private String enrollment;

	public CSVParser(String studentId) {
		students = new ArrayList<>();
		studentIds = new ArrayList<>();
		courses = new ArrayList<>();
		courseIds = new ArrayList<>();

		try {
			InputStream resource = getClass().getClassLoader().getResourceAsStream("students.csv");
			CSVReader reader = new CSVReader(new InputStreamReader(resource), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				students.add(s);
				studentIds.add(nextLine[0]);
				if (nextLine[0].equals(studentId)){
					String firstName = nextLine[1].toString();
					String lastName = nextLine[2].toString();
					studentName = firstName + " " + lastName;
					String email = nextLine[3].toString();
					studentEmail = email + "@jsu.edu"; 
				}
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}

		try {
			InputStream resource = getClass().getClassLoader().getResourceAsStream("courses.csv");
			CSVReader reader = new CSVReader(new InputStreamReader(resource), ',', '\"', 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Course c = new Course(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				String term = nextLine[1].toString();
				String year = nextLine[2].toString();
				String enrollment = nextLine[3].toString();
				courseTerm = term + " " + year;
				courses.add(c);
				courseIds.add(nextLine[0]);

			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	public String getEnrollment(String courseId) {
		for(int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c.getSize();
		}
		return null;
	}

	public String getCourseTerm(String courseId){
		for(int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c.getTermPlusYear();
		}
		return null;
	}
	
	public List<String> getStudentIdsAsList(){
		return studentIds;
	}

	public String getStudentIds() {
		return String.join("\n", studentIds);
	}

	public List<String> getCourseIdsAsList() {
		return courseIds;
	}
	public String getCourseIds() {
		return String.join("\n", courseIds);
	}

	public Student getStudent(String studentId) {
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(studentId.equals(s.getId())) return s;
		}
		return null;
    }
	
	public String getStudentName() {
		return studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

    public Course getCourse(String courseId) {
		for(int i = 0; i < courses.size(); i++){
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c;
		}
		return null;
    }
	
}
