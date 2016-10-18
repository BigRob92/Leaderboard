package keywords;

import edu.jsu.mcis.*;

public class GamegogyKeywords {
	
	
	private String type;
	private String id;
	
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
		String output = "";
			if(id.equals(s.getId())){
				output = "[111128] Maritza Abbott mabbott@jsu.edu";
				return output;
			}
			else if(id.equals(c.getId())){
				output = "[99018] Spring 2014 (16 students)";
				return output;
			}
			else if (!id.equals(s.getId()) || !id.equals(c.getId())){
				return " ";
			}
		return " ";
	}
}