package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selected;
	private float selected2;
	private String nameSelected;
	private String nameId;
	private String emailSelected;
    public LeaderboardEvent(String id, float grade) {
        selected = id;
		selected2 = grade;
		nameId = nameId;
		nameSelected = nameSelected;
    }
    public String getSelected() { return selected; }
	
	public float getSelectedGrade() {return selected2; }
	
}