package keywords;

import edu.jsu.mcis.*;

public class GamegogyKeywords {

    public Student startGamegogyCLIWithArguments(String student) {
		CSVParser p = new CSVParser();
        return p.getStudent(student);
    }
}
