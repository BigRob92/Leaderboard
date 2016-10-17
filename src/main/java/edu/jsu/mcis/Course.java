package edu.jsu.mcis;

public class Course {
    private String id;
    private String term;
    private String year;
    private String size;

    public Course(String id, String term, String year, String size) {
		this.id = id;
		this.term = term;
		this.year = year;
		this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public String getYear() {
        return year;
    }

    public String getSize() {
        return size;
    }

	@Override
    public String toString(){
        return "[" + id + "] " + term + " " + year + " " +  "(" + size + " students)";
    }
	@Override
	public boolean equals(Object o) {
		if(o instanceof Course) {
			Course c = (Course)o;
			return id.equals(c.id) && term.equals(c.term) && year.equals(c.year) && size.equals(c.size); 
		}
		else return false;
	}

}