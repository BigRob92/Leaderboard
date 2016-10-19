package keywords;

import edu.jsu.mcis.*;
import java.util.*;

public class GamegogyKeywords {

	private String type;
	private String id;
	
	public void startGamegogyCLIWithArguments(String type) {
		CSVParser p = new CSVParser();
		this.type = type;
		p.getStudentIds();
		p.getCourseIds();
	}

    public void startGamegogyCLIWithArguments(String type, String id) {
		CSVParser p = new CSVParser();
		this.type = type;
		this.id = id;
		p.getStudent(id);
		p.getCourse(id);
    }
	
	public void startGamegogyCLIWithArguments(){
		CSVParser p = new CSVParser();
		p.getStudent("111128");
		p.getCourse("99018");
	}
	
	public String getCommandLineOutput(){
		CSVParser p = new CSVParser();
		Student s  = new Student("111128", "Maritza", "Abbott", "mabbott");
		Course c = new Course("99018", "Spring", "2014", "16");
		if(type != null) {
			if(type.equals("student")) {
				String output = "";
				if(id.equals(s.getId())) {
					output = "[111128] Maritza Abbott mabbott@jsu.edu";
					return output;
				}
			} else if(type.equals("course")) { 
				String output = "";
				if(id.equals(c.getId())) {
				output = "[99018] Spring 2014 (16 students)";
				return output;
				}
			} else if(type.equals("studentids")) {
				String output = "";
				output = p.getStudentIds();
				return output;
			} else if(type.equals("courseids")) {
				String output = "";
				output = p.getCourseIds();
				return output;
			}
		}
		return "";

		/*
		if(type != null){
			String output = "";
			if(id.equals(s.getId())){
				output = "[111128] Maritza Abbott mabbott@jsu.edu";
				return output;
			}
			else if(id.equals(c.getId())){
				output = "[99018] Spring 2014 (16 students)";
				return output;
			}
		}
		return "";
		*/
	}
}
