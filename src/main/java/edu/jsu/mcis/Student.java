package edu.jsu.mcis;


public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public Student(String id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

	@Override
    public String toString() {
        return "[" + id + "] " + firstName + " " + lastName + " " + email + "@jsu.edu";
    }

	@Override
	public boolean equals(Object o) {
		if(o instanceof Student) {
			Student s = (Student)o;
			return id.equals(s.getId()) && firstName.equals(s.getFirstName()) && lastName.equals(s.getLastName()) && email.equals(s.getEmail()); 
		}
		else return false;
	}

}
