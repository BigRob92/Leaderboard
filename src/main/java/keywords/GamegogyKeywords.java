package keywords;
import edu.jsu.mcis.*;


public class GamegogyKeywords {

	private String type;
	private String id;
	
	public void startGamegogyCLIWithArguments(String type) {
		CSVParser p = new CSVParser();
		this.type = type;
	}

    public void startGamegogyCLIWithArguments(String type, String id) {
		CSVParser p = new CSVParser();
		this.type = type;
		this.id = id;
    }
	
	public void startGamegogyCLIWithArguments(){
		CSVParser p = new CSVParser();
		p.getStudent("111128");
		p.getCourse("99018");
	}
	
	public String getCommandLineOutput(){
		CSVParser p = new CSVParser();
		Student s = new Student("","","","");
		Course c = new Course("","","","");
		if(type != null) {
			if(type.equals("student")) {
				return p.getStudent("111128").toString();
			
			} else if(type.equals("course")) { 
				return p.getCourse("99018").toString();
			
			} else if(type.equals("studentids")) {
				return String.join("\n", p.getStudentIds().toString());
			
			} else if(type.equals("courseids")) {
				return String.join("\n", p.getCourseIds().toString());
			}
		}
		return "";
	}

}
