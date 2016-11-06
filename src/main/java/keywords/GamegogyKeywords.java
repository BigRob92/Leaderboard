package keywords;
import edu.jsu.mcis.*;


public class GamegogyKeywords {

	private String type;
	private String id;
	
	public void startGamegogyCLIWithArguments(String type) {
		CSVParser p = new CSVParser("111111");
		this.type = type;
	}

    public void startGamegogyCLIWithArguments(String type, String id) {
		CSVParser p = new CSVParser("111111");
		this.type = type;
		this.id = id;
    }
	
	public void startGamegogyCLIWithArguments(){
		CSVParser p = new CSVParser("111111");
	}
	
	public String getCommandLineOutput(){
		CSVParser p = new CSVParser("111111");
		if(type != null) {
			if(type.equals("student")) {
				return p.getStudent(id).toString();
			
			} else if(type.equals("course")) { 
				return p.getCourse(id).toString();
			
			} else if(type.equals("studentids")) {
				return String.join("\n", p.getStudentIds().toString());
			
			} else if(type.equals("courseids")) {
				return String.join("\n", p.getCourseIds().toString());
			}
		}
		return "";
	}

}
