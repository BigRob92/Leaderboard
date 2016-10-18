package edu.jsu.mcis;

public class Course {
    private String id;
    private String term;
    private String year;
    private String size;
	private String ids;

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
			return id.equals(c.getId()) && term.equals(c.getTerm()) && year.equals(c.getYear()) && size.equals(c.getSize()); 
		}
		else return false;
	}

}