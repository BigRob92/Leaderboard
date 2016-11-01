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
	private File f;
	public List<File> files;

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
				String firstName = nextLine[1].toString();
				String lastName = nextLine[2].toString();
				String email = nextLine[3].toString();
				studentName = firstName + " " + lastName;
				studentEmail = email + "@jsu.edu"; 
				students.add(s);
				studentIds.add(nextLine[0]);
			}
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}

		try {
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses.csv"), ',', '\"', 1);
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

	public String getStudentIds() {
		return String.join("\n", studentIds);
	}

	public List<String> getStudentIdsAsList() {
		return studentIds;
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
	
	//this needs to be fixed
	public String getStudentName(String studentId) {
		try{
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/students.csv"), ',', '\"', 1);
			String nextLine[];
			while ((nextLine = reader.readNext()) != null){
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				if(nextLine[0].equals(studentId)){
					String firstName = nextLine[1];
					String lastName = nextLine[2];
					String name = firstName + " "+ lastName;	
					return name;
				}
			}
			
		}
		catch(IOException e){}
		return "";
	}

	public String getStudentEmail(String studentId) {
		try {
			CSVReader reader = new CSVReader(new FileReader("/home/ben/Development/cs310/Leaderboard/src/main/resources/students.csv"), ',', '\"', 1);
			String nextLine[];
			while ((nextLine = reader.readNext()) != null) {
				Student s = new Student(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				if(nextLine[0].equals(studentId)) {
					String studentEmail = nextLine[3];
					String email = studentEmail + "@jsu.edu";
					return email;
				}
			}
		}
		catch (IOException e) {}
		return "";
	}


    public Course getCourse(String courseId) {
		for(int i = 0; i < courses.size(); i++){
			Course c = courses.get(i);
			if(courseId.equals(c.getId())) return c;
		}
		return null;
    }
	
}
